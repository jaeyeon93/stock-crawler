package com.example.demo.bot;

import com.example.demo.domain.Stock;
import com.example.demo.service.StockService;
import me.ramswaroop.jbot.core.common.Controller;
import me.ramswaroop.jbot.core.common.EventType;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.regex.Matcher;

@Component
public class SlackBot extends Bot {
    private static final Logger logger =  LoggerFactory.getLogger(SlackBot.class);

    @Value("${slack.token}")
    private String token;

    @Value("${default.url}")
    private String defaultUrl;

    @Value("${slack.webhook.url}")
    private String webhookUrl;

    @Value("${slack.bot.url}")
    private String botUrl;

    @Autowired
    private StockService stockService;

    @Override
    public String getSlackToken() {
        return token;
    }

    @Override
    public Bot getSlackBot() {
        return this;
    }

    @Controller(events = {EventType.DIRECT_MENTION, EventType.DIRECT_MESSAGE})
    public void onReceiveDM(WebSocketSession session, Event event) {
        reply(session, event, new Message("Hi, I am " + slackService.getCurrentUser().getName()));
    }

    @Controller(events = EventType.MESSAGE)
    public void onReceiveMessage(WebSocketSession session, Event event, Matcher matcher) throws IOException {
        String message = event.getText();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Authorization", "Bearer "+ token);
        String json = "{\n" +
                "\t\"channel\":\"test\",\n" +
                "\t\"text\":\"I hope the tour went well, Mr. Wonka.\",\n" +
                "\t\"attachments\": [\n" +
                "        {\n" +
                "            \"color\": \"#CC0000\",\n" +
                "            \"title\": \"<http://www.naver.com| Naver>\",\n" +
                "            \"mrkdwn_in\": [\n" +
                "                \"title\",\n" +
                "                \"text\",\n" +
                "                \"pretext\",\n" +
                "                \"fields\"\n" +
                "            ],\n" +
                "            \"fields\": [\n" +
                "                {\n" +
                "                    \"title\": \"주가\",\n" +
                "                    \"value\": \"700,000\",\n" +
                "                    \"short\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"title\": \"변동률\",\n" +
                "                    \"value\": \"15%\",\n" +
                "                    \"short\": true\n" +
                "                },\n" +
                "                {\n" +
                "                    \"title\": \"변동가격\",\n" +
                "                    \"value\": \"10,000\",\n" +
                "                    \"short\": true\n" +
                "                }\n" +
                "            ],\n" +
                "            \"footer\": \"MADE BY JIMMY\"\n" +
                "        }\n" +
                "    ]\n" +
                "\n" +
                "}";

        HttpEntity<String> entity = new HttpEntity<>(json ,headers);
        logger.info("입력한 메세지 : {}", message);
        if (message.contains("테스트"))
            restTemplate.postForEntity(webhookUrl, entity, String.class);

        if (message.contains("상위변동률"))
            reply(session, event, new Message("많이 오른 종목 : " + stockService.topRate().toString()));

        if (message.contains("하위변동률"))
            reply(session, event, new Message("많이 떨어진 종목 : " + stockService.lowRate().toString()));
    }
}
