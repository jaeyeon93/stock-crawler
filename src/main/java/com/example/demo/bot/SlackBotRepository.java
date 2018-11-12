package com.example.demo.bot;

import com.example.demo.domain.Stock;
import com.example.demo.dto.Converter;
import com.example.demo.service.StockService;
import com.google.gson.Gson;
import me.ramswaroop.jbot.core.common.BotWebSocketHandler;
import me.ramswaroop.jbot.core.slack.SlackService;
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
import me.ramswaroop.jbot.core.slack.models.Event;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Component
public class SlackBotRepository {
    private static final Logger logger =  LoggerFactory.getLogger(SlackBotRepository.class);
    private RestTemplate restTemplate;
    private HttpHeaders headers;
    private Gson gson;

    @Value("${slack.token}")
    private String token;

    @Value("${slack.bot.url}")
    private String botUrl;

    @Autowired
    private SlackBot bot;

    @Autowired
    private StockService stockService;

    @Autowired
    private SlackService slackService;

    @Autowired
    private Command command;

    @PostConstruct
    public  void init() {
        gson = new Gson();
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Authorization", "Bearer "+ token);
    }

    public void connect() {
        logger.info("connect 메서드 요청");
        slackService.startRTM(token);
        if (slackService.getWebSocketUrl() == null) {
            logger.info("웹소켓의 url이 null");
            WebSocketConnectionManager manager = new WebSocketConnectionManager(client(), handler(), slackService.getWebSocketUrl());
            manager.start();
        }
    }

    public ResponseEntity<String> search(String keyword, Event event) {
        return restTemplate.postForEntity(botUrl, requestList(gson, headers, stockService.searchByStockName(keyword), event.getChannelId()), String.class);
    }

    public ResponseEntity<String> requestCommand(String message, Event event) {
         return restTemplate.postForEntity(botUrl, requestList(gson, headers, command.get(message).runCommand(), event.getChannelId()), String.class);
    }

    public ResponseEntity<String> reqeustCustomStock(String message, Event event) throws Exception {
        HttpEntity<String> entity = new HttpEntity<>(gson.toJson(new Converter(stockService.updateByStockName(message), event.getChannelId())) , headers);
        return restTemplate.postForEntity(botUrl, entity, String.class);
    }

    public HttpEntity<String> requestList(Gson gson, HttpHeaders headers, List<Stock> stocks, String channel) {
        return new HttpEntity<>(gson.toJson(new Converter(stocks, channel)), headers);
    }

    public StandardWebSocketClient client() {
        return new StandardWebSocketClient();
    }

    public BotWebSocketHandler handler() {
        return new BotWebSocketHandler(bot.getSlackBot());
    }
}
