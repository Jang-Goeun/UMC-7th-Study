package umc.spring.service.MemberService;

import java.util.List;
import umc.spring.domain.Member;
import java.util.Optional;

public interface MemberQueryService {
    //Optional<Member> findMember(Long memberId);
    List<Member> getMemberProfile(Long memberId);
}
