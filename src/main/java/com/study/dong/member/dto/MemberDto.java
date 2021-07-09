package com.study.dong.member.dto;

import com.study.dong.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter @Setter
@AllArgsConstructor
@Builder
public class MemberDto {
    private String userId;
    private String userPassword;
    private String email;
    private String name;
    private String nickname;
    private Date regDate;


    // Entity 객체로 변환
    public Member toEntity(){
        return Member.builder()
                .userId(userId)
                .userPassword(userPassword)
                .email(email)
                .name(name)
                .nickname(name)
                .build();
    }
}
