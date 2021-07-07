package com.study.dong.user.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="USER")
public class UserVO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 12, nullable = false, name="user_id")
    private String userId;

    @Column(nullable = false, name="user_password")
    private String userPassword;

    private String email;

    @Column(name="reg_date")
    private Date regDate;

    private String name;

    @Column(nullable = false)
    private String nickname;

    @Builder
    public UserVO(String userId, String userPassword, String email, String name, String nickname) {
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
    }


    @PrePersist
    public void createAt(){
        this.regDate = Date.valueOf(LocalDate.now());
    }
}
