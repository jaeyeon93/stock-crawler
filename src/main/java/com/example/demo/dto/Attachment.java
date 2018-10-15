package com.example.demo.dto;

import com.example.demo.domain.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.BiConsumer;

public class Attachment extends LinkedHashMap<String, Object> {
    private static final Logger logger =  LoggerFactory.getLogger(Attachment.class);

    public Attachment(Stock stock, String color, boolean custom) {
        put("color", color);
        put("title", "<" + stock.getDetailUrl() + " |" + stock.getName() + "> 주가 : " + stock.getCost() + " 변동률 : " + stock.getRate() + " 변동가격 : " + stock.getUpdn());
        if (custom) {
            List<Field> fields = new ArrayList<>();
            fields.add(new Field("매출", stock.getSalesMoney()));
            fields.add(new Field("영업이익", stock.getProfit()));
            fields.add(new Field("시가총액", stock.getTotalCost()));
            put("fields", fields);
        }
    }
}
