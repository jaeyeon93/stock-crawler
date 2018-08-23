package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.example.demo.support.domain.CommonSearch;
import com.google.common.util.concurrent.Futures;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.lang.model.util.Elements;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class KospiInfo extends CommonSearch {
    private static final Logger logger =  LoggerFactory.getLogger(KospiInfo.class);

    @Resource(name = "stockRepository")
    private StockRepository stockRepository;

    @Value("${kospiUrl}")
    private String kospiUrl;

    @PostConstruct
    public void init() {
        super.init();
    }

    @Async("threadPoolTaskExecutor")
    public void part(int partNumber) throws Exception {
        long start = System.currentTimeMillis();
        getStart(kospiUrl);
        List<Stock> stocks = new ArrayList<>();
        try {
            for (int i = 1; i <= 380; i++)
                stocks.add(makeStock(partNumber, i));
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            logger.info("message : {}", e.getMessage());
        }
        long end = System.currentTimeMillis();
        logger.info("총 걸린 시간 : {}초", (end - start)/1000.0);
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

    public boolean checkDb(WebElement element) {
        if (stockRepository.findAll().contains(stockRepository.findByName(getTitle(element))))
            return true;
        return false;
    }

    public Stock makeStock(int i, int j) {
        WebElement element = getDriver().findElement(By.xpath("//*[@id=\"wrap\"]/div[" + i + "]/div/div[3]/dl[" + j + "]"));
        if (j % 10 == 0)
            logger.info("{}part {}번째 data, title : {}",i, j, getTitle(element));

        if (checkDb(element))
            return stockRepository.findByName(getTitle(element)).realDataUpdate(getTitle(element), getInfo(element).get(1), getInfo(element).get(2), getInfo(element).get(3), getUrl(element));
        return new Stock(getTitle(element), getInfo(element).get(1), getInfo(element).get(2), getInfo(element).get(3), getUrl(element));
    }

    public List<WebElement> getChilds(WebElement element) {
        return element.findElements(By.xpath(".//*"));
    }

    public String getUrl(WebElement element) {
        String url = getChilds(element).get(0).getAttribute("onclick");
        return url.substring(8, url.length() -2);
    }

    public String getTitle(WebElement element) {
        return getChilds(element).get(0).getAttribute("title");
    }

    public List<String> getInfo(WebElement element) {
        return Arrays.asList(element.getText().split("\n"));
    }
}
