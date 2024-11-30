package umc.spring.repository.MemberRepository;

import umc.spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepositoryCustom {
    List<Member> findMembersWithProfile(Long memberId);
}