package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.example.demo.support.domain.CommonSearch;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StockInfo extends CommonSearch {
    private static final Logger logger = LoggerFactory.getLogger(StockInfo.class);
    private JsonParser parser;

    @Autowired
    private StockRepository stockRepository;

    public List<Stock> jsonMaking(String url) throws IOException {
        List<Stock> stocks = new ArrayList<>();
        Map<String, Stock> stockMap = getMap(stockRepository.findAll());
        String body = new Research(url).getBody();
        parser = new JsonParser();
        String [] infos = splitBody(body);
        for (int i = 0; i < infos.length; i++)
            stocks.add(makingStockUsingJson(infos[i], parser, stockMap).toStock());
        return stocks;
    }
}

