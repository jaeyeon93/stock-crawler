package com.example.demo.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyboardDto {
    private static final Logger logger =  LoggerFactory.getLogger(KeyboardDto.class);

    private String type;
    private String text;

    public KeyboardDto(){}

    public KeyboardDto(String text) {
        this.type = "buttons";
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    public KeyboardDto setType(String type) {
        this.type = type;
        return this;
    }

    public KeyboardDto setText(String text) {
        this.text = text;
        return this;
    }
}
