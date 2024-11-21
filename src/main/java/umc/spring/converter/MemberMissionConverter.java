package umc.spring.converter;

import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.web.dto.MemberMission.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMission.MemberMissionResponseDTO;

import java.time.LocalDateTime;

public class MemberMissionConverter {
    public static MemberMissionResponseDTO.ChallengingResultDTO toChallengingResultDTO(MemberMission memberMission){
        return MemberMissionResponseDTO.ChallengingResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static MemberMission toMemberMission(MemberMissionRequestDTO.ChallengingDto request, Member member, Mission mission){
        MissionStatus missionStatus = null;

        switch (request.getStatus()){
            case 1:
                missionStatus = MissionStatus.CHALLENGING;
                break;
            case 2:
                missionStatus = MissionStatus.COMPLETED;
                 break;
        }

        return MemberMission.builder()
                .status(missionStatus)
                .member(member)
                .mission(mission)
                .build();
    }
}
