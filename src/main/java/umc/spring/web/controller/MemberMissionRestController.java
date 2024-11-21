package umc.spring.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.web.dto.MemberMission.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMission.MemberMissionResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mission/challenge")
public class MemberMissionRestController {
    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/")
    public ApiResponse<MemberMissionResponseDTO.ChallengingResultDTO> join(@RequestBody @Valid MemberMissionRequestDTO.ChallengingDto request){
        MemberMission memberMission = memberMissionCommandService.challengingMemberMission(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toChallengingResultDTO(memberMission));
    }
}
