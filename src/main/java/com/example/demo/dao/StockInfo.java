package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.example.demo.support.domain.CommonSearch;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

@Service
public class StockInfo extends CommonSearch {
    private static final Logger logger = LoggerFactory.getLogger(StockInfo.class);
    private JsonParser parser;

    @Resource(name = "stockRepository")
    private StockRepository stockRepository;

    @PersistenceContext
    private EntityManager em;

    @Value("${batch.size}")
    private Integer batchSize;

    @PostConstruct
    public void init() {
        super.init();
    }

    @Async("threadPoolTaskExecutor")
    @Transactional
    public void bulkInsert(int partNumber, String url) {
        getStart(url);
        long start = System.currentTimeMillis();
        try {
            List<WebElement> elements = getElements(partNumber);
            logger.info("elements size : {}", elements.size());
            List<Stock> originalStocks = stockRepository.findAll();
            for (int i = 0; i < elements.size(); i++) {
                em.persist(making(elements.get(i), originalStocks));
                if (i % batchSize == 0) {
                    logger.info("batch i : {}", i);
                    em.flush();
                    em.clear();
                }
            }
            em.flush();
            em.clear();
        } catch (StaleElementReferenceException e) {
            logger.info("staleElement error occur : {}", e.getMessage());
        } catch (Exception e) {
            logger.info("error occur : {}", e.getMessage());
        }
        long end = System.currentTimeMillis();
        logger.info("총 걸린 시간 : {}초", (end - start) / 1000.0);
    }

    public List<Stock> jsonMaking(String url) throws IOException {
        List<Stock> stocks = new ArrayList<>();
        String body = new Research(url).getBody();
        parser = new JsonParser();
        String [] infos = splitBody(body);
        for (int i = 0; i < infos.length; i++)
            stocks.add(makingStockUsingJson(infos[i], parser));
        return stocks;
    }

    public String [] splitBody(String body) {
        return body.split("\\s,\\s");
    }

    public Stock makingStockUsingJson(String info, JsonParser parser) {
        JsonObject object = (JsonObject)parser.parse(info);
        return new Stock(object.get("name").getAsString(), object.get("cost").getAsString(), object.get("updn").getAsString(), object.get("rate").getAsString(), getUrl(object.get("code").getAsString()));
    }

    public String getUrl(String code) {
        return "http://finance.daum.net/item/main.daum?code="+code;
    }
}

