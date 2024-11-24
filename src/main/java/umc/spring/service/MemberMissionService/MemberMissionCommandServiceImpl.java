package umc.spring.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.MemberHandler;
import umc.spring.apiPayload.exception.handler.MissionHandler;
import umc.spring.converter.MemberMissionConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.mapping.MemberMission;
import umc.spring.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.repository.MemberRepository.MemberRepository;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.web.dto.MemberMission.MemberMissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public MemberMission challengingMemberMission(MemberMissionRequestDTO.ChallengingDto request) {
        // 유효한 missionId ID라면 Mission 객체를 설정
        Mission mission = missionRepository.findById(request.getMission())
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        // 유효한 memberId ID라면 Member 객체를 설정
        Member member = memberRepository.findById(request.getMember())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        MemberMission newMemberMission = MemberMissionConverter.toMemberMission(request, member, mission);

        return memberMissionRepository.save(newMemberMission);
    }

    @Override
    public Page<MemberMission> getMemberMissionList(Long MemberId, Integer page){
        Member member = memberRepository.findById(MemberId).get();

        Page<MemberMission> MemberPage = memberMissionRepository.findAllByMember(member, PageRequest.of(page, 10));
        return MemberPage;
    }
}
