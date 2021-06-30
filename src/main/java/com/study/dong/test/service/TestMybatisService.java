package com.study.dong.test.service;

import com.study.dong.test.mapper.TestMapper;
import com.study.dong.test.vo.TestMybatisVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestMybatisService implements TestService<TestMybatisVO>{

    private final TestMapper mapper;

    @Autowired
    public TestMybatisService(TestMapper mapper) {
        this.mapper = mapper;
    }


    @Override
    public List<TestMybatisVO> findAll() {
        return mapper.selectTest();
    }
}
