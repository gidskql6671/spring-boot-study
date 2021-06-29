package com.study.dong.test.mapper;

import com.study.dong.test.vo.TestVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface TestMapper {
    List<TestVO> selectTest();
}
