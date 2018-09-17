package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

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
        Gson gson = new Gson();
        JsonObject object = new JsonObject();
        object.addProperty("code");
        object.addProperty("name");
        object.addProperty("cost");
        object.addProperty("updn");
        object.addProperty("rate");
    }

}
