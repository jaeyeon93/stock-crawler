package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class JacksonTest {
    private static final Logger logger =  LoggerFactory.getLogger(JacksonTest.class);
    private Research research;
    private ObjectMapper mapper;
    private String rawData;

    @Before
    public void setUp() throws IOException {
        research = new Research("http://finance.daum.net/xml/xmlallpanel.daum?stype=P&type=S");
        rawData = research.getBody();
    }

    @Test
    public void test() throws Exception {
        List<Stock> stocks = new ArrayList<>();
        logger.info("{}", rawData);
        String [] result = rawData.split("\\s,\\s");
        long start = System.currentTimeMillis();
        logger.info("배열의 길이는 : {}", result.length);
        for (int i = 0; i < result.length; i++) {
            JsonParser parser = new JsonParser();
            JsonObject object = (JsonObject)parser.parse(result[i]);
            stocks.add(new Stock(object.get("name").getAsString(), object.get("cost").getAsString(), object.get("updn").getAsString(), object.get("rate").getAsString(), getUrl(object.get("code").getAsString())));
        }
        System.out.println(stocks);
        long end = System.currentTimeMillis();
        logger.info("총 걸린 시간 : {}", (end - start)/1000);
    }

    @Test
    public void stock만들기() {
        String [] result = rawData.split("\\s,\\s");
        String stockStr = result[0];
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject)parser.parse(stockStr);
        String name = object.get("name").getAsString();
        String price = object.get("cost").getAsString();
        String chagneMoney = object.get("updn").getAsString();
        String percent = object.get("rate").getAsString();
        String url = getUrl(object.get("code").getAsString());
        Stock stock = new Stock(name, price, chagneMoney, percent, url);
        logger.info("{}", stock.toString());
    }

    @Test
    public void stringTokeneizeTest() {
        StringTokenizer st = new StringTokenizer(rawData, "} ,");
        while (st.hasMoreTokens()) {
//            System.out.println(st.nextToken());
            System.out.println(st.nextToken() + "}");
            System.out.println("다음");
        }
    }

    @Test
    public void jsonParserTest() {
        String test = "{code:\"095570\",name :\"AJ네트웍스\",cost :\"6,800\",updn :\"▲310\",rate :\"+4.78%\"}";
        JsonParser jsonParser = new JsonParser();
        JsonObject object = (JsonObject) jsonParser.parse(test);
        System.out.println(object);
    }

    public String getUrl(String code) {
        return "http://finance.daum.net/item/main.daum?code="+code;
    }
}
