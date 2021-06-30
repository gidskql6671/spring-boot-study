package com.study.dong.test.repository;

import com.study.dong.test.vo.TestJpaVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TestJpaRepository extends JpaRepository<TestJpaVO, Long> {
    // JpaRepository<Entity의 Type, P.K 값의 Type>
    // Primary Key가 mbrNo변수이고 Long타입이므로 Long을 넣어줌.
    // Primitive 형식 말고 Wrapper로 넣어줘야함.

    // findBy뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다.
    public List<TestJpaVO> findById(String id);

    public List<TestJpaVO> findByName(String name);


    // like 검색도 가능하다
    public List<TestJpaVO> findByNameLike(String name);
}
