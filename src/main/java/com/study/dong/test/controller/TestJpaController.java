package com.study.dong.test.controller;

import com.study.dong.test.service.TestJpaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/test")
@Slf4j
public class TestJpaController {
    private final TestJpaService testJpaService;


    @Autowired
    public TestJpaController(TestJpaService testService) {
        this.testJpaService = testService;
    }


    @GetMapping()
    public ModelAndView home(){
        ModelAndView mav = new ModelAndView("test/jpa");



        return mav;
    }
}
