package com.study.dong.test.service;

import com.study.dong.test.repository.TestJpaRepository;
import com.study.dong.test.vo.TestJpaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class TestJpaService {
    private final TestJpaRepository testJpaRepository;

    @Autowired
    public TestJpaService(TestJpaRepository testJpaRepository) {
        this.testJpaRepository = testJpaRepository;
    }

    public List<TestJpaVO> findAll(){
        List<TestJpaVO> entities = new ArrayList<>();
        testJpaRepository.findAll().forEach(e -> entities.add(e));
        return entities;
    }

    public Optional<TestJpaVO> findById(Long mbrNo){
        return testJpaRepository.findById(mbrNo);
    }

    public void deleteById(Long mbrNo){
        testJpaRepository.deleteById(mbrNo);
    }

    public TestJpaVO save(TestJpaVO entity){
        testJpaRepository.save(entity);
        return entity;
    }

    public void updateById(Long mbrNo, TestJpaVO entity){
        Optional<TestJpaVO> e = testJpaRepository.findById(mbrNo);

        if (e.isPresent()){
            e.get().setMbrNo(entity.getMbrNo());
            e.get().setId(entity.getId());
            e.get().setName(entity.getName());
            testJpaRepository.save(entity);
        }
    }
}
