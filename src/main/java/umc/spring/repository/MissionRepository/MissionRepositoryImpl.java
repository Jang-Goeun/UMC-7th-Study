package umc.spring.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Mission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.domain.QMission;
import umc.spring.domain.QRegion;
import umc.spring.domain.QStore;
import umc.spring.domain.mapping.QMemberMission;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Mission> findMissionsByRegionAndMember(Long regionId, Long memberId, String cursorValue, Pageable pageable) {
        QMission mission = QMission.mission;
        QStore store = QStore.store;
        QRegion region = QRegion.region;
        QMemberMission memberMission = QMemberMission.memberMission;

        // BooleanBuilder to build the dynamic query conditions
        BooleanBuilder predicate = new BooleanBuilder();
        predicate.and(region.id.eq(regionId))
                .and(mission.deadline.after(LocalDate.now()))  // Only missions with deadlines after today
                .and(mission.id.notIn(
                        queryFactory.select(memberMission.mission.id)
                                .from(memberMission)
                                .where(memberMission.member.id.eq(memberId))
                ));

        // Formatting deadline as a string for concatenation
        StringTemplate formattedDeadline = Expressions.stringTemplate(
                "DATE_FORMAT({0}, '%Y%m%d')", mission.deadline
        );

        // Applying cursor-based pagination
        if (cursorValue != null) {
            predicate.and(formattedDeadline.concat(mission.id.stringValue()).lt(cursorValue));
        }

        // Query to fetch the missions
        List<Mission> missions = queryFactory.selectFrom(mission)
                .join(mission.store, store).fetchJoin()
                .join(store.region, region).fetchJoin()
                .where(predicate)
                .orderBy(mission.deadline.asc(), mission.id.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        long total = queryFactory.selectFrom(mission)
                .join(mission.store, store)
                .join(store.region, region)
                .where(predicate)
                .fetchCount();


        return new PageImpl<>(missions, pageable, total);
    }

    @Override
    public Page<Mission> findInProgressMissions(Long memberId, Pageable pageable) {
        QMission mission = QMission.mission;
        QStore store = QStore.store;
        QMemberMission memberMission = QMemberMission.memberMission;

        List<Mission> missions = queryFactory.selectFrom(mission)
                .join(mission.store, store).fetchJoin()
                .join(memberMission).on(memberMission.mission.id.eq(mission.id))
                .where(memberMission.member.id.eq(memberId)
                        .and(memberMission.status.eq(MissionStatus.CHALLENGING)))
                .orderBy(memberMission.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.selectFrom(mission)
                .join(mission.store, store)
                .join(memberMission).on(memberMission.mission.id.eq(mission.id))
                .where(memberMission.member.id.eq(memberId)
                        .and(memberMission.status.eq(MissionStatus.CHALLENGING)))
                .fetchCount();

        return new PageImpl<>(missions, pageable, total);
    }

    @Override
    public Page<Mission> findCompletedMissions(Long memberId, Pageable pageable) {
        QMission mission = QMission.mission;
        QStore store = QStore.store;
        QMemberMission memberMission = QMemberMission.memberMission;

        List<Mission> missions = queryFactory.selectFrom(mission)
                .join(mission.store, store).fetchJoin()
                .join(memberMission).on(memberMission.mission.id.eq(mission.id))
                .where(memberMission.member.id.eq(memberId)
                        .and(memberMission.status.eq(MissionStatus.COMPLETED)))
                .orderBy(memberMission.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory.selectFrom(mission)
                .join(mission.store, store)
                .join(memberMission).on(memberMission.mission.id.eq(mission.id))
                .where(memberMission.member.id.eq(memberId)
                        .and(memberMission.status.eq(MissionStatus.COMPLETED)))
                .fetchCount();

        return new PageImpl<>(missions, pageable, total);
    }
}
