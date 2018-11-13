package com.example.demo.web;

import com.example.demo.bot.SlackBot;
import com.example.demo.bot.SlackBotRepository;
import com.example.demo.social.FrontUserDetail;
import me.ramswaroop.jbot.core.common.BotWebSocketHandler;
import me.ramswaroop.jbot.core.slack.SlackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Controller
public class HomeController {
    public static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private SlackBotRepository repository;

//    @GetMapping("/")
//    public String index() {
//        return "/index";
//    }

    @GetMapping("/")
    public String home(@AuthenticationPrincipal FrontUserDetail userDetail, Model model) {
        model.addAttribute("email", userDetail.getUsername());
        model.addAttribute("name", userDetail.getNickName());
        return "home";
    }

    @GetMapping("/error")
    public String error(@RequestParam String message, Model model) {
        model.addAttribute("message", message);
        return "error";
    }

    
//    @GetMapping("/connect")
//    public String connect() {
//        logger.info("웹소켓이 끊어졌을경우, 수동으로 웹소켓 연결하기");
//        repository.connect();
//        return "redirect:/stock";
//    }
}
