package com.example.demo.bot;

import com.example.demo.domain.Stock;
import com.example.demo.dto.Converter;
import com.example.demo.dto.StockJsonDto;
import com.example.demo.service.StockService;
import com.google.gson.Gson;
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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

@Component
public class SlackBot extends Bot {
    private static final Logger logger =  LoggerFactory.getLogger(SlackBot.class);

    @Value("${slack.token}")
    private String token;

    @Value("${slack.bot.url}")
    private String botUrl;

    @Autowired
    private StockService stockService;

    @Autowired
    private Command command;

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
    public ResponseEntity<String> onReceiveMessage(WebSocketSession session, Event event, Matcher matcher) throws IOException {
        String message = event.getText();
        logger.info("메세지 : {}", message);
        Gson gson = new Gson();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Authorization", "Bearer "+ token);

        if (checkCommand(message))
            return restTemplate.postForEntity(botUrl, requestList(gson, headers, command.get(message).runCommand()), String.class);
        Stock stock = stockService.getStockByStockName(message);
        HttpEntity<String> entity = new HttpEntity<>(gson.toJson(new Converter(stock)) , headers);
        return restTemplate.postForEntity(botUrl, entity, String.class);
    }

    public HttpEntity<String> requestList(Gson gson, HttpHeaders headers, List<Stock> stocks) {
        return new HttpEntity<>(gson.toJson(new Converter(stocks)), headers);
    }

    public boolean checkCommand(String message) {
        if (command.containsKey(message))
            return true;
        return false;
    }
}
