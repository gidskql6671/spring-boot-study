package com.study.dong.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String getHomepage(Model model){

        model.addAttribute("content", "ㅁㄴㅇ");

        return "index";
    }
}
