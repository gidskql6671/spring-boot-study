package com.study.dong.user.repository;

import com.study.dong.user.vo.UserVO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @AfterEach
    public void cleanup(){
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("UserVO 생성 테스트")
    public void testCreateUserVO(){
        UserVO user = UserVO.builder()
                .userId("testId")
                .userPassword("testPassword")
                .email("testEmail")
                .name("testName")
                .nickname("testNickname")
                .build();

        userRepository.save(user);

        assertAll(() -> assertEquals("testId", user.getUserId()),
                () -> assertEquals("testPassword", user.getUserPassword()),
                () -> assertEquals("testEmail", user.getEmail()),
                () -> assertEquals("testName", user.getName()),
                () -> assertEquals("testNickname", user.getNickname()),
                () -> assertEquals(LocalDate.now().toString(), user.getRegDate().toString())
                );
    }
}