package com.study.dong.member.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.dong.member.dto.MemberDto;
import com.study.dong.member.service.MemberService;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.hamcrest.object.HasEqualValues;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = MemberController.class)
class MemberControllerTest {
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private MemberService memberService;

    @BeforeAll
    public static void setup(){

    }

    @Test
    public void memberHome() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/member")
                        .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.view().name("/member/index"));
    }

    @Test
    public void getSignupForm() throws Exception {
        MemberDto memberDto = new MemberDto();
        
//        samePropertyValuesAs를 테스트해보기위해...
        String[] ignoreProperty = {};
//        memberDto.setName("ASD");
//        memberDto.setNickname("DDD");
        
        
        mockMvc.perform(
                    get("/member/signup")
                        .accept(MediaType.TEXT_HTML))
                // member 속성값의 클래스가 MemberDto인지 확인
                .andExpect(model().attribute("member", Matchers.instanceOf(MemberDto.class)))
                // member 속성값의 객체가 memberDto와 동일한 값을 가지는지 확인
                .andExpect(model().attribute("member", Matchers.samePropertyValuesAs(memberDto, ignoreProperty)))
                .andExpect(status().isOk());
    }

    @Test
    public void getLoginForm() throws Exception {
        mockMvc.perform(
                get("/member/login")
                    .accept(MediaType.TEXT_HTML))
                .andExpect(view().name("/member/login"))
                .andExpect(status().isOk());
    }
    
}