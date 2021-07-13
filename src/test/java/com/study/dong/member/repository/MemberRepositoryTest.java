package com.study.dong.member.repository;

import com.study.dong.member.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("UserVO 생성 테스트")
    public void testCreateUserVO(){
        Member member = Member.builder()
                .userId("testId")
                .userPassword("testPassword")
                .email("testEmail")
                .name("testName")
                .nickname("testNickname")
                .build();

        memberRepository.save(member);

        assertAll(() -> assertEquals("testId", member.getUserId()),
                () -> assertEquals("testPassword", member.getUserPassword()),
                () -> assertEquals("testEmail", member.getEmail()),
                () -> assertEquals("testName", member.getName()),
                () -> assertEquals("testNickname", member.getNickname()),
                () -> assertEquals(LocalDate.now().toString(), member.getRegDate().toString())
                );
    }
}