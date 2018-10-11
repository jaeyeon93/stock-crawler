package com.example.demo.dto;

import com.example.demo.domain.Stock;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Converter extends LinkedHashMap<String, Object> {
    private static final String red = "#CC0000";

    public Converter(Stock stock) {
        put("username", "jimmy");
        put("channel", "test");
        List<Attachment> attachments = new ArrayList<>();
        attachments.add(new Attachment(red, stock));
        put("attachments", attachments);
    }
}
