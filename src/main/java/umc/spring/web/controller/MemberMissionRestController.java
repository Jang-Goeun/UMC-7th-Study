package umc.spring.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.validator.CheckPageValidator;
import umc.spring.web.dto.MemberMission.MemberMissionRequestDTO;
import umc.spring.web.dto.MemberMission.MemberMissionResponseDTO;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/mission")
public class MemberMissionRestController {
    private final MemberMissionCommandService memberMissionCommandService;
    private final CheckPageValidator checkPageValidator;

    @PostMapping("/add-challenge")
    public ApiResponse<MemberMissionResponseDTO.ChallengingResultDTO> join(@RequestBody @Valid MemberMissionRequestDTO.ChallengingDto request){
        MemberMission memberMission = memberMissionCommandService.challengingMemberMission(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toChallengingResultDTO(memberMission));
    }

    // 진행중인 미션 목록 조회
    @GetMapping("/{userId}/challenging")
    @Operation(summary = "진행중인 미션 목록 조회 API",description = "내가 진행중인 미션 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "acess 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "acess 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자의 아이디, path variable 입니다!")
    })
    public ApiResponse<MemberMissionResponseDTO.ChallengingMissionListDTO> getMemberMissionList(@ExistMember @PathVariable(name = "userId") Long MemberId, @CheckPage @RequestParam(name = "page") Integer page){
        Integer validatedPage = checkPageValidator.validateAndTransformPage(page);
        Page<MemberMission> memberMissionList = memberMissionCommandService.getMemberMissionList(MemberId, validatedPage);
        return ApiResponse.onSuccess(MemberMissionConverter.challengingMissionListDTO(memberMissionList));
    }
}
