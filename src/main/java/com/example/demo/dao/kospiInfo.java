package com.example.demo.dao;

import com.example.demo.domain.Stock;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class kospiInfo {
    private static final Logger logger =  LoggerFactory.getLogger(kospiInfo.class);
    private String wholeInfoUrl = "http://finance.daum.net/quote/allpanel.daum?stype=P&type=S";
    private WebDriver driver;

    public kospiInfo() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        System.setProperty("webdriver.chrome.driver", "/Users/jaeyeonkim/Desktop/stock-crawler/src/main/java/com/example/demo/chromedriver");
        driver = new ChromeDriver(options);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(840, 530));
        driver.get(getWholeInfoUrl());
    }

//    @PostConstruct
//    public void init() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
//        System.setProperty("webdriver.chrome.driver", "/Users/jaeyeonkim/Desktop/stock-crawler/src/main/java/com/example/demo/chromedriver");
//        driver = new ChromeDriver(options);
//        driver.get(wholeInfoUrl);
//    }

    public List<Stock> whole1() {
        List<Stock> stocks = new ArrayList<>();
            for (int j = 1; j <= 763; j++) {
                WebElement element = driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[1]/div/div[3]/dl[" + j + "]"));
                stocks.add(new Stock(getTitle(element), getInfo(element).get(1), getInfo(element).get(2), getInfo(element).get(3), getUrl(element)));
            }
        return stocks;
    }

    public List<Stock> whole2() {
        List<Stock> stocks = new ArrayList<>();
        for (int j = 1; j <= 764; j++) {
            WebElement element = driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[2]/div/div[3]/dl[" + j + "]"));
            stocks.add(new Stock(getTitle(element), getInfo(element).get(1), getInfo(element).get(2), getInfo(element).get(3), getUrl(element)));
        }
        return stocks;
    }

    public List<Stock> whole3() {
        List<Stock> stocks = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            for (int j = 1; j <= 763; j++) {
                WebElement element = driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[" + i + "]/div/div[3]/dl[" + j + "]"));
                stocks.add(new Stock(getTitle(element), getInfo(element).get(1), getInfo(element).get(2), getInfo(element).get(3), getUrl(element)));
            }
        }
        return stocks;
    }

    public List<WebElement> getChilds(WebElement element) {
        return element.findElements(By.xpath(".//*"));
    }

    public String getWholeInfoUrl() {
        return wholeInfoUrl;
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
