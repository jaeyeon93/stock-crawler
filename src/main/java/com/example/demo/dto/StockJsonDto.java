package com.example.demo.dto;

import com.example.demo.domain.Stock;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StockJsonDto {
    private static final Logger logger =  LoggerFactory.getLogger(StockJsonDto.class);
    private Stock stock;

    public StockJsonDto(Stock stock) {
        this.stock = stock;
    }

    public JsonObject makeJson() {
        JsonObject object = new JsonObject();
        object.addProperty("username", "jimmyBot");
        object.addProperty("channel","test");
        object.add("attachments", attachements());
        return object;
    }

    public JsonArray attachements() {
        JsonArray attachments = new JsonArray();
        JsonObject attach = new JsonObject();
        attach.addProperty("color", "#CC0000");
        attach.addProperty("title", "<" + getStock().getDetailUrl() + " |" + getStock().getName() + ">");
        attach.add("fields", makeFileds());
        attach.addProperty("footer", "MADE BY JIMMY");
        attachments.add(attach);
        return attachments;
    }

    public JsonArray makeFileds() {
        String [] titles = {"주가", "변동률", "변동가격"};
        Double [] values = {(double)getStock().getCost(), getStock().getRate(), (double)getStock().getUpdn()};
        JsonArray fields = new JsonArray();
        for (int i = 0; i < titles.length; i++) {
            JsonObject field = new JsonObject();
            field.addProperty("title", titles[i]);
            field.addProperty("value", values[i]);
            field.addProperty("short", true);
            fields.add(field);
        }
        return fields;
    }

    public Stock getStock() {
        return stock;
    }
}
