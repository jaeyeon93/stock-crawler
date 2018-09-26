package com.example.demo.support.domain;

import com.example.demo.dao.Research;
import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.example.demo.dto.StockDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CommonSearch {
    private static final Logger logger =  LoggerFactory.getLogger(CommonSearch.class);

    public String [] splitBody(String body) {
        return body.split("\\s,\\s");
    }

    public StockDto makeStockDtoByJson(JsonObject object, JsonParser parser) throws Exception {
        Gson gson = new Gson();
        return gson.fromJson(object, StockDto.class);
    }

    public String getUrl(String code) {
        return "http://finance.daum.net/item/main.daum?code="+code;
    }

    public String getTitle(JsonObject object) {
        return object.get("name").getAsString();
    }

    public Stock updateByStockName(Stock original) throws IOException {
        Research research = new Research(original.getDetailUrl());
        try {
            String changePercent = research.getElements().get(2).substring(0, research.getElements().get(2).length()-1);
            original.update(research.getElements().get(0),research.getElements().get(1), changePercent, research.getProfit(), research.getSalesMoney(), research.getTotalCost(), original.getDetailUrl());
        } catch (Exception e) {
            logger.info("에러 발생 {}", e.getMessage());
        }
        return original;
    }

    public Map<String, Stock> getMap(List<Stock> stocks) {
        Map<String, Stock> map = new HashMap<>();
        for (Stock stock : stocks)
            map.put(stock.getName(), stock);
        return map;
    }

    public boolean chekcDB(JsonObject object, Map<String, Stock> map) {
        if (map.containsKey(getTitle(object)))
            return true;
        return false;
    }
}
