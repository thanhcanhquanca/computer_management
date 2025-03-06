package com.example.computer_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("login")
public class HandlerLoginController {
    @GetMapping("/list")
    public String list() {
            return "login/list";
        }
}
