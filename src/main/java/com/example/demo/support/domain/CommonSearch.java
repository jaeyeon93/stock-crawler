package com.example.demo.support.domain;

import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

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

    public WebDriver getDriver() {
        return driver;
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
