package com.study.dong.test.vo;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name="testJpa")
public class TestJpaVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mbrNo;

    private String id;

    private String name;

    @Builder
    public TestJpaVO(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
