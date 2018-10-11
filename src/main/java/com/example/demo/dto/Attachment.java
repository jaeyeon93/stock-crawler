package com.example.demo.dto;

import com.example.demo.domain.Stock;

import java.util.*;
import java.util.function.BiConsumer;

public class Attachment extends LinkedHashMap<String, Object> {
    public Attachment(String color, Stock stock) {
        put("color", color);
        put("title", "<" + stock.getDetailUrl() + " |" + stock.getName() + ">");
        List<Field> fields = new ArrayList<>();
        fields.add(new Field("주가", stock.getCost()));
        fields.add(new Field("변동률", stock.getRate()));
        fields.add(new Field("변동가격", stock.getUpdn()));
        put("fields", fields);
        put("footer", "Made by Jimmy");
    }
}
