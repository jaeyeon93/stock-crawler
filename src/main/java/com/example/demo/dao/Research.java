package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.example.demo.support.domain.CommonSearch;
import com.example.demo.web.StockController;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class Research extends CommonSearch {
    public static final Logger logger = LoggerFactory.getLogger(Research.class);
    private WebDriver driver;
    private Stock stock;

    public Research() {}

    public Research(Stock original) {
        this.stock = original;
        logger.info("Research에 전달받은 주식 객체 : {}", original.toString());
        getDriver().get(original.getDetailUrl());
    }

    public Stock update() {
        String price = getDriver().findElement(By.xpath("//*[@id=\"topWrap\"]/div[1]/ul[2]/li[1]/em")).getText();
        String changeMoney = getDriver().findElement(By.xpath("//*[@id=\"topWrap\"]/div[1]/ul[2]/li[2]/span")).getText();
        String changePercent = getDriver().findElement(By.xpath("//*[@id=\"topWrap\"]/div[1]/ul[2]/li[3]")).getText();
        String profit = getDriver().findElement(By.xpath("//*[@id=\"performanceCorp\"]/table/tbody/tr[5]/td[9]")).getText();
        String sales_moeny = getDriver().findElement(By.xpath("//*[@id=\"performanceCorp\"]/table/tbody/tr[4]/td[9]")).getText();
        String total_cost = getDriver().findElement(By.xpath("//*[@id=\"stockContent\"]/ul[2]/li[2]/dl[2]/dd")).getText();
        getStock().update(price, changeMoney, changePercent, profit, sales_moeny, total_cost);
        return getStock();
    }

    public Stock getStock() {
        return stock;
    }
}