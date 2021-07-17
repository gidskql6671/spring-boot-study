package com.study.dong.board.domain;

import com.study.dong.common.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="board")
public class Board extends BaseEntity {
    private String title;
    private String content;
    
    private String reg_nickname;
    private Long reg_member_id;

    @Builder
    public Board(String title, String content, String reg_nickname, Long reg_member_id) {
        this.title = title;
        this.content = content;
        this.reg_nickname = reg_nickname;
        this.reg_member_id = reg_member_id;
    }
}
