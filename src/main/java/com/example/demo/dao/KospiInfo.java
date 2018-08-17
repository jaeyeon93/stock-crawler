package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.support.domain.CommonSearch;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class KospiInfo extends CommonSearch {
    private static final Logger logger =  LoggerFactory.getLogger(KospiInfo.class);

    @Value("${kospiUrl}")
    private String kospiUrl;

    @PostConstruct
    public void init() {
        super.init();
    }

    public List<Stock> whole1() {
        getStart(kospiUrl);
        List<Stock> stocks = new ArrayList<>();
            for (int j = 50; j <= 101; j++)
                stocks.add(makeStock(1, j));
        return stocks;
    }

    public List<Stock> whole3() {
        getStart(kospiUrl);
        List<Stock> stocks = new ArrayList<>();
        for (int i = 1; i <= 2; i++)
            for (int j = 1; j <= 763; j++)
                makeStock(i, j);
        return stocks;
    }

    public Integer getCountDl() {
        List<WebElement> elements = getDriver().findElements(By.xpath("//*[@id=\"wrap\"]/div[1]/div/div[3]/dl[1]"));
        for (WebElement element : elements) {
            logger.info("element info : {}", element.getText());
            logger.info("element tagName : {}", element.getTagName());

        }
        return elements.size();
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
