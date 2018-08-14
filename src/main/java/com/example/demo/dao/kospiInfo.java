package com.example.demo.dao;

import com.example.demo.domain.Stock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class kospiInfo {
    private static final Logger logger =  LoggerFactory.getLogger(kospiInfo.class);

    @Value("${spring.default.url}")
    private String wholeInfoUrl;

//    private String wholeInfoUrl = "http://finance.daum.net/quote/allpanel.daum?stype=P&type=S";

    private WebDriver driver;

    @PostConstruct
    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        System.setProperty("webdriver.chrome.driver", "/Users/jaeyeonkim/Desktop/stock-crawler/src/main/java/com/example/demo/chromedriver");
        driver = new ChromeDriver(options);
        driver.get(getWholeInfoUrl());
    }

    public List<Stock> part1() {
        List<Stock> stocks = new ArrayList<>();
        for (int i = 1; i < 507; i++) {
            WebElement element = driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[1]/div/div[3]/dl[" + i + "]"));
            List<WebElement> childs = element.findElements(By.xpath(".//*"));
            String url = childs.get(0).getAttribute("onclick");
            List<String> info = Arrays.asList(element.getText().split("\n"));
            stocks.add(new Stock(childs.get(0).getAttribute("title"), info.get(1), info.get(2), info.get(3), url.substring(8, url.length() - 2)));
        }
        return stocks;
    }

    public List<Stock> part2() {
        List<Stock> stocks = new ArrayList<>();
        for (int i = 1; i < 507; i++) {
            WebElement element = driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[2]/div/div[3]/dl[" + i + "]"));
            List<WebElement> childs = element.findElements(By.xpath(".//*"));
            String url = childs.get(0).getAttribute("onclick");
            List<String> info = Arrays.asList(element.getText().split("\n"));
            stocks.add(new Stock(childs.get(0).getAttribute("title"), info.get(1), info.get(2), info.get(3), url.substring(8, url.length() - 2)));
        }
        return stocks;
    }

    public List<Stock> part3() {
        List<Stock> stocks = new ArrayList<>();
        for (int i = 1; i < 509; i++) {
            WebElement element = driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[3]/div/div[3]/dl[" + i + "]"));
            List<WebElement> childs = element.findElements(By.xpath(".//*"));
            String url = childs.get(0).getAttribute("onclick");
            List<String> info = Arrays.asList(element.getText().split("\n"));
            stocks.add(new Stock(childs.get(0).getAttribute("title"), info.get(1), info.get(2), info.get(3), url.substring(8, url.length() - 2)));
        }
        return stocks;
    }

    public List<Stock> whole() {
        List<Stock> stocks = new ArrayList<>();
//        for (int i = 1; i <=3; i++) {
            for (int j = 1; j <= 507; j++) {
                WebElement element = driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[1]/div/div[3]/dl[" + j + "]"));
//                WebElement element = driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[" + i + "]/div/div[3]/dl[" + j + "]"));
                List<String> info = Arrays.asList(element.getText().split("\n"));
                String url = getChilds(element).get(0).getAttribute("onclick");
                stocks.add(new Stock(getChilds(element).get(0).getAttribute("title"), info.get(1), info.get(2), info.get(3), url.substring(8, url.length() - 2)));
            }
//        }
        return stocks;
    }

    public List<WebElement> getChilds(WebElement element) {
        return element.findElements(By.xpath(".//*"));
    }

    public String getWholeInfoUrl() {
        return wholeInfoUrl;
    }
}
