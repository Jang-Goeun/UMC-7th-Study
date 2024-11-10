package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.spring.domain.Mission;
import umc.spring.repository.MissionRepository.MissionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public Page<Mission> getMissionsByRegionAndMember(Long regionId, Long memberId, String cursorValue, Pageable pageable) {
        return missionRepository.findMissionsByRegionAndMember(regionId, memberId, cursorValue, pageable);
    }

    @Override
    public Page<Mission> getInProgressMissions(Long memberId, Pageable pageable) {
        return missionRepository.findInProgressMissions(memberId, pageable);
    }

    @Override
    public Page<Mission> getCompletedMissions(Long memberId, Pageable pageable) {
        return missionRepository.findCompletedMissions(memberId, pageable);
    }
}