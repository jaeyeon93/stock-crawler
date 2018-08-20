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
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

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

//    @Async("threadPoolTaskExecutor")
//    public void part(int section) throws Exception {
//        getStart(kospiUrl);
//        List<Stock> stocks = new ArrayList<>();
//        for (int i = 1; i <= 381; i++)
//            stocks.add(makeStock(section, i));
//        logger.info("size : {}", stocks.size());
//        stockRepository.save(stocks);
//    }

    public List<Stock> whole() {
        getStart(kospiUrl);
        List<Stock> stocks = new ArrayList<>();
        for (int i = 1; i <= 4; i++)
            for (int j = 1; j <= 10; j++)
                stocks.add(makeStock(i, j));
        return stocks;
    }

    public Stock makeStock(int i, int j) {
        WebElement element = getDriver().findElement(By.xpath("//*[@id=\"wrap\"]/div[" + i + "]/div/div[3]/dl[" + j + "]"));
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
