package umc.spring.converter;

import org.springframework.data.domain.Page;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.domain.enums.MissionStatus;
import umc.spring.web.dto.MemberMission.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMission.MemberMissionResponseDTO;
import umc.spring.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    // 내가 진행중인 미션 목록
    public static MemberMissionResponseDTO.ChallengingMissionDTO challengingMissionDTO(MemberMission memberMission){
        return MemberMissionResponseDTO.ChallengingMissionDTO.builder()
                .reward(memberMission.getMission().getReward())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .createdAt(memberMission.getCreatedAt().toLocalDate())
                .build();
    }

    public static MemberMissionResponseDTO.ChallengingMissionListDTO challengingMissionListDTO(Page<MemberMission> memberMissionList){

        // 진행 중인 미션만 필터링
        List<MemberMissionResponseDTO.ChallengingMissionDTO> challengingMissionDTOList = memberMissionList.stream()
                .filter(memberMission -> memberMission.getStatus() == MissionStatus.CHALLENGING) // 필터 조건
                .map(MemberMissionConverter::challengingMissionDTO).collect(Collectors.toList());

        return MemberMissionResponseDTO.ChallengingMissionListDTO.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(challengingMissionDTOList.size())
                .reviewList(challengingMissionDTOList)
                .build();
    }
}
