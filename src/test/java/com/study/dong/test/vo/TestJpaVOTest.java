package com.study.dong.test.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestJpaVOTest {

    @Test
    void getId() {
        final TestJpaVO testJpaVO = TestJpaVO.builder()
                .id("test_id")
                .name("test_name")
                .build();
        final String id = testJpaVO.getId();

        assertEquals("test_id", id);
    }

    @Test
    void getName() {
        final TestJpaVO testJpaVO = TestJpaVO.builder()
                .id("test_id")
                .name("test_name")
                .build();
        final String name = testJpaVO.getName();

        assertEquals("test_name", name);
    }
}