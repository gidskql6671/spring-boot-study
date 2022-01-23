package com.example.member.repository;

import com.example.member.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    
    @AfterEach
    public void clear(){
        repository.clear();
    }
    
    @Test
    public void save() {
        Member member = new Member();
        member.setName("test");
        repository.save(member);
        
        Member result = repository.findById(member.getId()).orElseThrow();
        assertThat(result).isEqualTo(member);
    }

    @Test
    void findById() {
        Member member1 = new Member();
        member1.setName("test");
        repository.save(member1);
        
        Member member2 = new Member();
        member2.setName("test");
        repository.save(member2);
        
        Member result = repository.findById(member1.getId()).orElseThrow();
        
        assertThat(result).isEqualTo(member1);
        assertThat(result).isNotEqualTo(member2);
    }

    @Test
    void findByName() {
        Member member1 = new Member();
        member1.setName("test");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test");
        repository.save(member2);

        Member result = repository.findByName(member1.getName()).orElseThrow();

        assertThat(result).isEqualTo(member1);
        assertThat(result).isNotEqualTo(member2);
    }

    @Test
    void findAll() {
        Member member1 = new Member();
        member1.setName("test");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("test");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}