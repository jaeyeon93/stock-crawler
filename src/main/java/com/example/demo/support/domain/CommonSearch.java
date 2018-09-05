package com.example.demo.support.domain;

import com.example.demo.dao.Research;
import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public abstract class CommonSearch {
    private static final Logger logger =  LoggerFactory.getLogger(CommonSearch.class);
    private WebDriver driver;

    @Value("${driver.path}")
    private String path;

    @Autowired
    private StockRepository stockRepository;

    public void init() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver(options);
    }

    public void getStart(String url) {
        driver.get(url);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1360, 430));
    }

    public Stock update(Stock original) throws IOException {
        Research research = new Research(original.getDetailUrl());
        try {
            Double changePercent = Double.valueOf(research.getElements().get(2).substring(0, research.getElements().get(2).length()-1));
            original.update(research.getElements().get(0),research.getElements().get(1), changePercent, research.getProfit(), research.getSalesMoney(), research.getTotalCost());
        } catch (Exception e) {
            logger.info(e.getMessage());
        }
        return original;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public boolean checkDb(WebElement element, List<Stock> stocks) {
        if (stocks.contains(getTitle(element)))
            return true;
        return false;
    }

    public List<WebElement> getElements(int partNumber) {
        return getDriver().findElements(By.cssSelector("#wrap .wBox:nth-child(" + partNumber  +") .nBox div:nth-child(3) > dl"));
    }

    public Stock making(WebElement element, List<Stock> stocks) {
        if (checkDb(element, stocks))
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
