package com.study.dong.member.domain;

import com.study.dong.common.domain.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;


@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="USER")
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 12, nullable = false, name="user_id")
    private String userId;

    @Column(nullable = false, name="user_password")
    private String userPassword;

    private String email;

    private String name;

    @Column(nullable = false)
    private String nickname;
    
    @Builder
    public Member(String userId, String userPassword, String email, String name, String nickname) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
    }
    
    public void updateInfo(String email, String name, String nickname){
        this.email = email;
        this.name = name;
        this.nickname = nickname;
    }
}
