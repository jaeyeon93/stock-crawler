package com.example.demo.dto;

import com.example.demo.domain.Stock;

import java.util.LinkedHashMap;

public class Field extends LinkedHashMap<String, Object> {

    public Field(String title, Object value) {
        put("title", title);
        put("value", value);
        put("short", true);
    }
}
