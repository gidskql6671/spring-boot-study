package com.example.member.service;

import com.example.member.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
	Long join(Member member);

	List<Member> findMembers();

	Optional<Member> findMember(Long memberId);
}
