package com.example.demo.dao;

import com.example.demo.dto.ChartImageUrl;
import com.example.demo.dto.RealData;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class ResearchTest {
    private static final Logger logger =  LoggerFactory.getLogger(ResearchTest.class);
    private Research research;
    private Document doc;
    private Gson gson = new Gson();

    @Before
    public void setUp() throws Exception {
        research = new Research("http://finance.daum.net/quotes/A005930#home");
        doc = Jsoup.connect("http://finance.daum.net/api/quotes/A000660?summary=false&changeStatistics=true").ignoreContentType(true).get();
    }

    @Test
    public void 시가총액() throws Exception {
        JsonParser parser = new JsonParser();
        String json = doc.body().text();
        logger.info("{}", json);
        JsonElement element = parser.parse(json);
        logger.info("현재가격 {}", element.getAsJsonObject().get("tradePrice").getAsString());
    }

    @Test
    public void 리얼데이터매핑테스트() {
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(doc.body().text());
        logger.info("{}", element);
        JsonObject object = (JsonObject)element;
        logger.info("{}", object);
        RealData realData = gson.fromJson(element, RealData.class);
        logger.info("{}", realData.toString());
    }

    @Test
    public void 차트이미지url() {
        JsonParser parser = new JsonParser();
        JsonObject object = (JsonObject)parser.parse(doc.body().text());
        logger.info("{}",object.get("chartImageUrl"));
        JsonElement urls = object.get("chartImageUrl");
        ChartImageUrl images = gson.fromJson(urls, ChartImageUrl.class);
        logger.info("{}", images.toString());
    }
}
