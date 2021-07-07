package com.study.dong.user.repository;

import com.study.dong.user.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserVO, Long> {

}
