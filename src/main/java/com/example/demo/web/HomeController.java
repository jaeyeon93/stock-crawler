package com.example.demo.web;

import com.example.demo.bot.SlackBot;
import com.example.demo.bot.SlackBotRepository;
import me.ramswaroop.jbot.core.common.BotWebSocketHandler;
import me.ramswaroop.jbot.core.slack.SlackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Controller
public class HomeController {
    public static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private SlackService slackService;

    @Autowired
    private SlackBotRepository slackBotRepository;

    @Autowired
    private SlackBot slackBot;

    @Value("${slack.token}")
    private String token;

    @GetMapping("/")
    public String index() {
        return "/index";
    }

    @GetMapping("/connect")
    public String connect() {
        logger.info("컨트롤러에서 connect 요청");
        slackService.startRTM(token);
        if (slackService.getWebSocketUrl() != null) {
            WebSocketConnectionManager manager = new WebSocketConnectionManager(slackBotRepository.client(), slackBotRepository.handler(), slackService.getWebSocketUrl());
            manager.start();
        } 
        return "redirect:/stock";
    }
}
