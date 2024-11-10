package umc.spring.repository.MemberRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.domain.Member;
import umc.spring.domain.QMember;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QMember member = QMember.member;

    @Override
    public List<Member> findMembersWithProfile(Long memberId) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (memberId != null) {
            predicate.and(member.id.eq(memberId));
        }

        return queryFactory
                .selectFrom(member)
                .where(predicate)
                .fetch();
    }
}