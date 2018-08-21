package com.example.demo.support.domain;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

public abstract class CommonSearch {
    private static final Logger logger =  LoggerFactory.getLogger(CommonSearch.class);
    private WebDriver driver;

    @Value("${driver.path}")
    private String path;

    public void init() {
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
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
}
