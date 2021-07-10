package com.study.dong.member.controller;


import com.study.dong.member.dto.MemberDto;
import com.study.dong.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/")
    public String index(){
        return "/member/index";
    }

    @GetMapping("/signup")
    public String signupForm(Model model){
        model.addAttribute("member", new MemberDto());

        return "/member/signup";
    }

    @PostMapping("/signup")
    public String signup(MemberDto memberDto){
        memberService.signUp(memberDto);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        return "/member/login";
    }
}
