package umc.spring.service.MissionService;

import jakarta.validation.Valid;
import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    public Mission registerMission(MissionRequestDTO.@Valid MissionRegisterDto request);
    public boolean doMissionExist(Long missionId);
}
