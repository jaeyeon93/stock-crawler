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
