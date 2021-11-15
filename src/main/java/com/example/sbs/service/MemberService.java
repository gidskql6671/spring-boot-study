package com.example.sbs.service;

import com.example.sbs.domain.Member;
import com.example.sbs.repository.MemberRepository;
import com.example.sbs.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    
    public Long join(Member member) {
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
        .ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }
    
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    
    public Optional<Member> findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}