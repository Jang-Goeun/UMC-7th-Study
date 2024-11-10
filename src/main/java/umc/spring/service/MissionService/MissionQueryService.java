package umc.spring.service.MissionService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;

public interface MissionQueryService {
    Page<Mission> getMissionsByRegionAndMember(Long regionId, Long memberId, String cursorValue, Pageable pageable);
    Page<Mission> getInProgressMissions(Long memberId, Pageable pageable);
    Page<Mission> getCompletedMissions(Long memberId, Pageable pageable);
}
