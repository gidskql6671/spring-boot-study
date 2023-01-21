package com.example.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/test")
    public String index(Model model) {
        model.addAttribute("text", "hello22");
        
        return "test";
    }
}
