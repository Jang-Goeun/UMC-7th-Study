package umc.spring.service.MemberMissionService;

import umc.spring.domain.mapping.MemberMission;
import umc.spring.web.dto.MemberMission.MemberMissionRequestDTO;

public interface MemberMissionCommandService {
    public MemberMission challengingMemberMission(MemberMissionRequestDTO.ChallengingDto request);
}
