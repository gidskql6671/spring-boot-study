package com.study.dong.member.repository;

import com.study.dong.member.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


// Transactional을 붙여주면, 테스트 이후 db가 롤백된다.
@Transactional
@SpringBootTest
@Slf4j
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    public void clearDB(){
        memberRepository.deleteAll();
    }
    
    private void createMember(){
        memberRepository.saveAndFlush(Member.builder()
                .userId("testId")
                .userPassword("testPassword")
                .email("testEmail")
                .name("testName")
                .nickname("testNickname")
                .build()
        );
    }
    
    @Test
    public void CreateMemberTest(){
        // given
        LocalDateTime now = LocalDateTime.of(2000, 1, 1, 0, 0, 0);
        this.createMember();

        // when
        Member member = memberRepository.findByUserId("testId")
                .orElseThrow(EntityNotFoundException::new);
        
        // then
        assertAll(() -> assertEquals("testId", member.getUserId()),
                () -> assertEquals("testPassword", member.getUserPassword()),
                () -> assertEquals("testEmail", member.getEmail()),
                () -> assertEquals("testName", member.getName()),
                () -> assertEquals("testNickname", member.getNickname())
                );
        assertThat(member.getCreateDate()).isAfter(now);
        assertThat(member.getModifiedDate()).isAfter(now);
        
    }
    
    @Test
    public void updateMemberTest() throws InterruptedException {
        // given
        final String email = "testemail2";
        final String name = "testname2";
        final String nickname = "testnickname2";
        
        this.createMember();
        
        Thread.sleep(1000);
        
        Member member = memberRepository.findByUserId("testId")
                .orElseThrow(EntityNotFoundException::new);
        member.updateInfo(email, name, nickname);
        memberRepository.saveAndFlush(member);
        
        // when
        Member modifiedMember = memberRepository.findByUserId("testId")
                .orElseThrow(EntityNotFoundException::new);
        
        // then
        assertAll(
                () -> assertThat(modifiedMember.getName()).isEqualTo(name),
                () -> assertThat(modifiedMember.getNickname()).isEqualTo(nickname),
                () -> assertThat(modifiedMember.getEmail()).isEqualTo(email),
                () -> assertThat(modifiedMember.getModifiedDate()).isAfter(modifiedMember.getCreateDate())
        );
        
        log.info("CreatedDate  : {}", modifiedMember.getCreateDate());
        log.info("ModifiedDate : {}", modifiedMember.getModifiedDate());
    }
    
}