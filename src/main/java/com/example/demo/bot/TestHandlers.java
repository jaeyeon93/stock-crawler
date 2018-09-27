package com.example.demo.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TestHandlers extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("전달받은 메세지 : " + update);
    }

    @Override
    public String getBotUsername() {
        return "jimmyStock_bot";
    }

    @Override
    public String getBotToken() {
        return "628846461:AAE5JfRXVOJaTzrWiTzKaujYfyaaGNT1akE\n";
    }
}
