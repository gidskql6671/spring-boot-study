package com.study.dong.member.service;

import com.study.dong.member.repository.MemberRepository;
import com.study.dong.member.domain.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;


    // 회원가입
    @Transactional
    public Long signUp(Member member){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return 1L; // 임시 코드
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
