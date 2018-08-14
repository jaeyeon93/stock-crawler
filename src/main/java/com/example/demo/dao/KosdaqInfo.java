package com.example.demo.dao;

import com.example.demo.domain.Stock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KosdaqInfo {
    private String wholeInfoUrl = "http://finance.daum.net/quote/allpanel.daum?stype=Q&type=S";
    private WebDriver driver;


    public KosdaqInfo() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        System.setProperty("webdriver.chrome.driver", "/Users/jaeyeonkim/Desktop/web-crawler/src/main/java/com/example/demo/chromedriver");
        driver = new ChromeDriver(options);
        driver.get(getWholeInfoUrl());
    }

//    public List<Stock> kosdaqPart1() {
//        List<Stock> stocks = new ArrayList<>();
//        for (int i = 1; i < 430; i++) {
//            WebElement element = driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[1]/div/div[3]/dl[" + i + "]"));
//            List<WebElement> childs = element.findElements(By.xpath(".//*"));
//            String url = childs.get(0).getAttribute("onclick");
//            List<String> info = Arrays.asList(element.getText().split("\n"));
//            stocks.add(new Stock(childs.get(0).getAttribute("title"), info.get(1), info.get(2), info.get(3), url.substring(8, url.length() - 2)));
//        }
//        return stocks;
//    }
//
//    public List<Stock> kosdaqPart2() {
//        List<Stock> stocks = new ArrayList<>();
//        for (int i = 1; i < 430; i++) {
//            WebElement element = driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[2]/div/div[3]/dl[" + i + "]"));
//            List<WebElement> childs = element.findElements(By.xpath(".//*"));
//            String url = childs.get(0).getAttribute("onclick");
//            List<String> info = Arrays.asList(element.getText().split("\n"));
//            stocks.add(new Stock(childs.get(0).getAttribute("title"), info.get(1), info.get(2), info.get(3), url.substring(8, url.length() - 2)));
//        }
//        return stocks;
//    }
//
//    public List<Stock> kosdaqPart3() {
//        List<Stock> stocks = new ArrayList<>();
//        for (int i = 1; i < 431; i++) {
//            WebElement element = driver.findElement(By.xpath("//*[@id=\"wrap\"]/div[3]/div/div[3]/dl[" + i + "]"));
//            List<WebElement> childs = element.findElements(By.xpath(".//*"));
//            String url = childs.get(0).getAttribute("onclick");
//            List<String> info = Arrays.asList(element.getText().split("\n"));
//            stocks.add(new Stock(childs.get(0).getAttribute("title"), info.get(1), info.get(2), info.get(3), url.substring(8, url.length() - 2)));
//        }
//        return stocks;
//    }

    public String getWholeInfoUrl() {
        return wholeInfoUrl;
    }
}
