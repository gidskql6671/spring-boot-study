package com.study.dong.test.service;

import com.study.dong.test.mapper.TestMapper;
import com.study.dong.test.vo.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Autowired
    public TestMapper mapper;

    public List<TestVO> selectTest(){
        return mapper.selectTest();
    }
}
