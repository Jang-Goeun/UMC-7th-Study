package umc.spring.converter;

import jakarta.validation.Valid;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDateTime;

public class MissionConverter {
    public static MissionResponseDTO.MissionRegisterResultDTO toRegisterResultDTO(Mission mission){
        return MissionResponseDTO.MissionRegisterResultDTO.builder()
                .missionId(mission.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.@Valid MissionRegisterDto request, Store store){
        return Mission.builder()
                .store(store)
                .missionSpec(request.getMissionSpec())
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .build();
    }
}
