package com.example.demo.support.domain;

import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        System.setProperty("webdriver.chrome.driver", path);
        driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1360, 430));
    }

    public Stock getUpdate(Stock original) {
        getStart(original.getDetailUrl());
        WebDriver driver = getDriver();
        try {
            WebElement element = driver.findElement(By.xpath("//*[@id=\"topWrap\"]/div[1]/ul[2]"));
            String price = element.findElement(By.xpath(".//li[1]/em")).getText();
            String changeMoney = element.findElement(By.xpath(".//li[2]/span")).getText();
            Double changePercent = Double.valueOf(element.findElement(By.xpath(".//li[3]")).getText().substring(0, element.findElement(By.xpath(".//li[3]")).getText().length() -1));
            String profit = driver.findElement(By.xpath("//*[@id=\"performanceCorp\"]/table/tbody/tr[5]/td[9]")).getText();
            String salesMoney = driver.findElement(By.xpath("//*[@id=\"performanceCorp\"]/table/tbody/tr[4]/td[9]")).getText();
            String total_cost = driver.findElement(By.xpath("//*[@id=\"stockContent\"]/ul[2]/li[2]/dl[2]/dd")).getText();
            original.update(price, changeMoney, changePercent, profit, salesMoney, total_cost);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.info(e.getMessage());
        }
        return original;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public boolean checkDb(WebElement element) {
        if (stockRepository.findAll().contains(stockRepository.findByName(getTitle(element))))
            return true;
        return false;
    }

    public List<WebElement> getElements(int partNumber) {
        return getDriver().findElements(By.cssSelector("#wrap .wBox:nth-child(" + partNumber  +") .nBox div:nth-child(3) > dl"));
    }

    public Stock makeStock(int i, int j) {
        WebElement element = getDriver().findElement(By.xpath("//*[@id=\"wrap\"]/div[" + i + "]/div/div[3]/dl[" + j + "]"));
        if (j % 10 == 0)
            logger.info("{}part {}번째 data, title : {}",i, j, getTitle(element));

        if (checkDb(element))
            return stockRepository.findByName(getTitle(element)).realDataUpdate(getTitle(element), getInfo(element).get(1), getInfo(element).get(2), getInfo(element).get(3), getUrl(element));
        return new Stock(getTitle(element), getInfo(element).get(1), getInfo(element).get(2), getInfo(element).get(3), getUrl(element));
    }

    public Stock making(WebElement element) {
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
