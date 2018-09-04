package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.example.demo.support.domain.CommonSearch;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class KospiInfo extends CommonSearch {
    private static final Logger logger = LoggerFactory.getLogger(KospiInfo.class);

    @Autowired
    EntityManager entityManager;

    @Resource(name = "stockRepository")
    private StockRepository stockRepository;

    @Value("${kospiUrl}")
    private String kospiUrl;

    @PostConstruct
    public void init() {
        super.init();
    }

    public List<Stock> part() {
        long start = System.currentTimeMillis();
        getStart(kospiUrl);
        List<Stock> stocks = new ArrayList<>();
        try {
            for (int i = 1; i <= 4; i++) {
                List<WebElement> elements = getElements(i);
                List<Stock> originalStocks = stockRepository.findAll();
                for (int j = 0; j < elements.size(); j++)
                    stocks.add(making(elements.get(j), originalStocks));
            }
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            logger.info("message : {}", e.getMessage());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.info("message : {}", e.getMessage());
        }
        long end = System.currentTimeMillis();
        logger.info("총 걸린 시간 : {}초", (end - start) / 1000.0);
        return stocks;
    }

    @Async("threadPoolTaskExecutor")
    public void part2(int partNumber) throws Exception {
        getStart(kospiUrl);
        long start = System.currentTimeMillis();
        List<Stock> stocks = new ArrayList<>();
        try {
            List<WebElement> elements = getElements(partNumber);
            List<Stock> originalStocks = stockRepository.findAll();
            for (int i = 0; i < elements.size(); i++)
                stocks.add(making(elements.get(i), originalStocks));
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            logger.info("message : {}", e.getMessage());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.info("message : {}", e.getMessage());
        }
        long end = System.currentTimeMillis();
        logger.info("총 걸린 시간 : {}초", (end - start) / 1000.0);
        stockRepository.save(stocks);
    }

    public List<Stock> batchInsert() {
        long start = System.currentTimeMillis();
        getStart(kospiUrl);
        final List<Stock> stocks = new ArrayList<>();
        try {
            for (int i = 1; i <= 4; i++) {
                List<WebElement> elements = getElements(i);
                List<Stock> originalStocks = stockRepository.findAll();
                for (int j = 0; j < elements.size(); j++) {
                    stocks.add(making(elements.get(j), originalStocks));
                    if (j % 20 == 0) {
                        entityManager.flush();
                        entityManager.clear();
                    }
                }
            }
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            logger.info("message : {}", e.getMessage());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.info("message : {}", e.getMessage());
        }
        long end = System.currentTimeMillis();
        logger.info("총 걸린 시간 : {}초", (end - start) / 1000.0);
        return stocks;
    }
}
