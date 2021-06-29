package com.study.dong.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {

    @GetMapping()
    public ModelAndView home(){
        ModelAndView mav = new ModelAndView("test");

        mav.addObject("name", "dong");

        List<String> testList = new ArrayList<String>();
        testList.add("a");
        testList.add("b");
        testList.add("c");
        mav.addObject("list", testList);

        return mav;
    }
}
