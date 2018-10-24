package com.example.demo.support.domain;

import com.example.demo.domain.Stock;
import com.example.demo.dto.RealData;
import com.example.demo.dto.StockDto;
import com.google.gson.*;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    public RealData updateByStockName(String updateUrl) throws IOException {
        Gson gson = new GsonBuilder().setLenient().create();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(Jsoup.connect(updateUrl).ignoreContentType(true).get().body().text());
        logger.info("element : {}", element.toString());
        return gson.fromJson(element, RealData.class);
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
