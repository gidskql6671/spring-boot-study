package com.study.dong.member.service;

import com.study.dong.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
public class MemberServiceTest {

    @Autowired
    MemberService memberService;


}
