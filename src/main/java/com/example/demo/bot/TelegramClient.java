package com.example.demo.bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramClient {
    public static void main(String[] args) {
        TelegramBotsApi api = new TelegramBotsApi();
        try {
            api.registerBot(new TestHandlers());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
