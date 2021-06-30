package com.study.dong.test.service;

import com.study.dong.test.vo.TestMybatisVO;

import java.util.List;

public interface TestService<T> {
    public List<T> findAll();

}
