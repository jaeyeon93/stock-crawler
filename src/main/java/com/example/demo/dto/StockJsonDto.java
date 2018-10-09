package com.example.demo.dto;

import com.example.demo.domain.Stock;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class StockJsonDto {
    private static final Logger logger =  LoggerFactory.getLogger(StockJsonDto.class);
    private Stock stock;

    public StockJsonDto(Stock stock) {
        this.stock = stock;
    }

//    public String makeJson() {
//        Gson gson = new Gson();
//        JsonObject object = new JsonObject();
//        object.addProperty("username", "jimmyBot");
//        object.addProperty("channel","test");
//        List<Object> obj = new ArrayList<>();
//        object.addProperty("attachments", "obj");
//    }
}
