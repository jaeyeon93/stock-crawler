package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.example.demo.support.domain.CommonSearch;
import com.google.common.util.concurrent.Futures;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.lang.model.util.Elements;
import javax.xml.bind.Element;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class KospiInfo extends CommonSearch {
    private static final Logger logger = LoggerFactory.getLogger(KospiInfo.class);

    @Resource(name = "stockRepository")
    private StockRepository stockRepository;

    @Value("${kospiUrl}")
    private String kospiUrl;

    private WebDriver driver;

    @PostConstruct
    public void init() {
        super.init();
    }

    @Async("threadPoolTaskExecutor")
    public void part(int partNumber) throws Exception {
        getStart(kospiUrl);
        long start = System.currentTimeMillis();
        List<Stock> stocks = new ArrayList<>();
        try {
            for (int i = 1; i <= 20; i++)
                stocks.add(makeStock(partNumber, i));
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            logger.info("message : {}", e.getMessage());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.info("message : {}", e.getMessage());
        }
        long end = System.currentTimeMillis();
        logger.info("총 걸린 시간 : {}초", (end - start) / 1000.0);
        stockRepository.save(stocks);
    }

    public List<Stock> whole() {
        getStart(kospiUrl);
        List<Stock> stocks = new ArrayList<>();
        try {
            for (int i = 1; i <= 4; i++)
                for (int j = 1; j <= 380; j++)
                    stocks.add(makeStock(i, j));
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            logger.info("message : {}", e.getMessage());
        }
        return stocks;
    }

    public String test() {
        List<WebElement> elements = getDriver().findElements(By.cssSelector("#wrap .wBox:nth-child(1) .nBox div:nth-child(3) > dl"));
        logger.info("elements size : {}", elements.size());
        return "hello";
    }

    public List<Stock> test2(int partNumber) {
        getStart(kospiUrl);
        logger.info("전달받은 partNumber : {}", partNumber);
        long start = System.currentTimeMillis();
        List<Stock> stocks = new ArrayList<>();
        try {
            for (int i = 0; i < getElements(partNumber).size(); i++)
                stocks.add(making(getElements(partNumber).get(i)));
            return stocks;
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
