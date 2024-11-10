package umc.spring.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;

public interface MissionRepositoryCustom {
    Page<Mission> findMissionsByRegionAndMember(Long regionId, Long memberId, String cursorValue, Pageable pageable);
    Page<Mission> findInProgressMissions(Long memberId, Pageable pageable);
    Page<Mission> findCompletedMissions(Long memberId, Pageable pageable);
}
