package com.example.demo.bot;

import me.ramswaroop.jbot.core.slack.Bot;
import org.springframework.beans.factory.annotation.Value;

public class SlackBot extends Bot {
    @Value("${slack.token}")
    private String token;

    @Value("${slack.}")

    @Override
    public String getSlackToken() {
        return null;
    }

    @Override
    public Bot getSlackBot() {
        return null;
    }
}
