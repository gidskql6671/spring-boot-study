package com.study.dong.test.controller;

import com.study.dong.test.service.TestService;
import com.study.dong.test.vo.TestMybatisVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/test")
@Slf4j
public class TestMybatisController {
    private final TestService<TestMybatisVO> testService;


    @Autowired
    public TestMybatisController(TestService<TestMybatisVO> testService) {
        this.testService = testService;
    }


    @GetMapping()
    public ModelAndView home(){
        ModelAndView mav = new ModelAndView("test");

        List<TestMybatisVO> testList = testService.findAll();
        mav.addObject("list", testList);

        log.debug("debug 로그");
        log.info("info 로그");
        log.error("error 로그");

        return mav;
    }
}