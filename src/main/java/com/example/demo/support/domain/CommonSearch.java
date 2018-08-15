package com.example.demo.support.domain;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonSearch {
    private static final Logger logger =  LoggerFactory.getLogger(CommonSearch.class);
    private WebDriver driver;

    public CommonSearch() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        System.setProperty("webdriver.chrome.driver", "/Users/jaeyeonkim/Desktop/stock-crawler/src/main/java/com/example/demo/chromedriver");
        driver = new ChromeDriver(options);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(840, 530));
    }

    public WebDriver getDriver() {
        return driver;
    }
}
