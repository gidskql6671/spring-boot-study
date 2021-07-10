package com.study.dong.member.service;

import com.study.dong.member.domain.Member;
import com.study.dong.member.dto.MemberDto;
import com.study.dong.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class MemberService implements UserDetailsService {
    private MemberRepository memberRepository;


    // 회원가입
    @Transactional
    public Long signUp(MemberDto memberDto){
        // Password를 암호화함
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setUserPassword(passwordEncoder.encode(memberDto.getUserPassword()));

        // DB에 유저 정보를 저장
        return memberRepository.save(memberDto.toEntity()).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // 로그인을 하기 위해 가입된 user 정보를 조회하는 메서드
        Optional<Member> memberWrapper = memberRepository.findByUserId(userId);
        if (!memberWrapper.isPresent())
            throw new UsernameNotFoundException(userId);
        Member member = memberWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();

        if ("admin".equals(userId))
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        else
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));

        return new User(member.getUserId(), member.getUserPassword(), authorities);
    }
}
