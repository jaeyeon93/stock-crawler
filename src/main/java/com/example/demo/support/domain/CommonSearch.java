package com.example.demo.support.domain;

import com.example.demo.dao.Research;
import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public abstract class CommonSearch {
    private static final Logger logger =  LoggerFactory.getLogger(CommonSearch.class);

    @Autowired
    private StockRepository stockRepository;

    public String [] splitBody(String body) {
        return body.split("\\s,\\s");
    }

    public Stock makingStockUsingJson(String info, JsonParser parser, List<Stock> stocks) {
        JsonObject object = (JsonObject)parser.parse(info);
        if (chekcDB(object, stocks)) {
            logger.info("주식 업데이트");
            return stockRepository.findByName(getTitle(object)).realDataUpdate(object.get("name").getAsString(), object.get("cost").getAsString(), object.get("updn").getAsString(), object.get("rate").getAsString(), getUrl(object.get("code").getAsString()));
        }
        return new Stock(object.get("name").getAsString(), object.get("cost").getAsString(), object.get("updn").getAsString(), object.get("rate").getAsString(), getUrl(object.get("code").getAsString()));
    }

    public String getUrl(String code) {
        return "http://finance.daum.net/item/main.daum?code="+code;
    }

    public String getTitle(JsonObject object) {
        return object.get("name").getAsString();
    }

    public boolean chekcDB(JsonObject object, List<Stock> stocks) {
        logger.info("checkDB에 전달받은 title : {}", getTitle(object));
        if (stocks.contains(getTitle(object))) {
            logger.info("db에 {} 존재", getTitle(object));
            return true;
        }
        logger.info("db에 존재안함");
        return false;
    }

    public Stock update(Stock original) throws IOException {
        Research research = new Research(original.getDetailUrl());
        try {
            Double changePercent = Double.valueOf(research.getElements().get(2).substring(0, research.getElements().get(2).length()-1));
            original.update(research.getElements().get(0),research.getElements().get(1), changePercent, research.getProfit(), research.getSalesMoney(), research.getTotalCost());
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return original;
    }

}
