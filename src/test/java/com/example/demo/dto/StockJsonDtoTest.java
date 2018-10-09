package com.example.demo.dto;

import com.example.demo.domain.Stock;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.junit.Assert.*;

public class StockJsonDtoTest {
    private static final Logger logger =  LoggerFactory.getLogger(StockJsonDtoTest.class);
    private Stock stock;
    private StockJsonDto stockJsonDto;

    @Before
    public void setUp() {
        stock = new Stock("삼성전자", 50000, 1000, 0.2, "http://www.naver.com");
        stockJsonDto = new StockJsonDto(stock);
    }

    @Test
    public void addProperty() {
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        object.addProperty("username", "jimmy");
        object.addProperty("channel","test");
        logger.info("{}", object.toString());
        JsonArray fields = new JsonArray();
        JsonObject field1 = new JsonObject();
        field1.addProperty("title", "주가");
        field1.addProperty("value", stock.getCost());
        field1.addProperty("short", "true");

        JsonObject field2 = new JsonObject();
        field2.addProperty("title", "변동률");
        field2.addProperty("value", stock.getRate());
        field2.addProperty("short", "true");

        JsonObject field3 = new JsonObject();
        field3.addProperty("title", "변동가격");
        field3.addProperty("value", stock.getUpdn());
        field3.addProperty("short", "true");

        fields.add(field1);
        fields.add(field2);
        fields.add(field3);
        logger.info("{}", fields.toString());
        JsonArray attachments = new JsonArray();
        JsonObject attachObject = new JsonObject();
        attachObject.addProperty("color", "#CC0000");
        attachObject.addProperty("title", "<" + stock.getDetailUrl() + "|" + stock.getName() + ">");
        attachObject.add("fields", fields);
        attachObject.addProperty("footer", "MADE BY JIMMY");
        attachments.add(attachObject);

        object.add("attachments", attachments);

        logger.info("{}", object.toString());
    }
}
