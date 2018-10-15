package com.example.demo.dto;

import com.example.demo.domain.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Converter extends LinkedHashMap<String, Object> {
    private static final Logger logger =  LoggerFactory.getLogger(Converter.class);
    private static final String red = "#CC0000";
    private static final String blue = "#0000FF";

    public Converter(Stock stock, String channel) {
        put("username", "jimmy");
        put("channel", channel);
        List<Attachment> attachments = new ArrayList<>();
        attachments.add(new Attachment(stock, checkColor(stock), true));
        put("attachments", attachments);
    }

    public Converter(List<Stock> stocks, String channel) {
        put("username", "jimmy");
        put("channel", channel);
        List<Attachment> attachments = new ArrayList<>();
        for (Stock stock : stocks)
            attachments.add(new Attachment(stock, checkColor(stock), false));
        put("attachments", attachments);
    }

    public static String checkColor(Stock stock) {
        if (stock.getRate() > 0.0)
            return red;
        return blue;
    }
}
