package com.example.demo.bot;

import me.ramswaroop.jbot.core.common.BotWebSocketHandler;
import me.ramswaroop.jbot.core.common.Controller;
import me.ramswaroop.jbot.core.common.EventType;
import me.ramswaroop.jbot.core.slack.Bot;
import me.ramswaroop.jbot.core.slack.models.Event;
import me.ramswaroop.jbot.core.slack.models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PingMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;

@Component
public class SlackBot extends Bot {
    private static final Logger logger =  LoggerFactory.getLogger(SlackBot.class);

    @Value("${slack.token}")
    private String token;

    @Autowired
    private Command command;

    @Autowired
    private SlackBotRepository slackBotRepository;

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
        logger.info("전달받은 메세지 : {}", event.getText());
        reply(session, event, new Message("Hi, I am " + slackService.getCurrentUser().getName()));
    }

//    @Controller(events = EventType.MESSAGE, pattern = "[^a-zA-Z\\d\\s:]")
    @Controller(events = EventType.MESSAGE, pattern = ".*\\S+.*")
    public ResponseEntity<String> onReceiveMessage(WebSocketSession session, Event event, Matcher matcher) throws Exception {
        String message = event.getText().toUpperCase();
        logger.info("전달받은 메세지 : {}", event.getText());
        if (message.contains("검색"))
            return slackBotRepository.search(message.substring(message.indexOf(":")+1).trim(), event);

        if (command.containsKey(message))
            return slackBotRepository.requestCommand(message, event);

        return slackBotRepository.reqeustCustomStock(message, event);
    }
}
