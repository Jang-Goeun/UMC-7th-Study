package umc.spring.repository.MemberRepository;

import umc.spring.domain.Member;

import java.util.List;

public interface MemberRepositoryCustom {
    List<Member> findMembersWithProfile(Long memberId);
}