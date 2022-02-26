package com.example.member.service;

import com.example.AppConfig;
import com.example.member.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService;
    
    @BeforeEach
    public void beforeEach() {
        AppConfig appconfig = new AppConfig();
        memberService = appconfig.memberService();
    }
    
    @Test
    void join() {
        // given
        Member member = new Member();
        member.setName("철수");
        
        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findMember(saveId).orElseThrow();
        Assertions.assertThat(findMember.getName()).isEqualTo(member.getName());
    }
    
    @Test
    void findMembers() {
        
    }

    @Test
    void findMember() {
    }
}