package com.study.dong.test.controller;

import com.study.dong.test.service.TestJpaService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
@Slf4j
public class TestJpaController {
    private final TestJpaService testJpaService;


    @GetMapping()
    public ModelAndView home(){
        ModelAndView mav = new ModelAndView("test/jpa");



        return mav;
    }
}
