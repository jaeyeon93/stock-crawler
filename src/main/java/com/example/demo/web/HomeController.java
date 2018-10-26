package com.example.demo.web;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    public static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


}
