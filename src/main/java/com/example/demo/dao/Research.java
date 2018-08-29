//package com.example.demo.dao;
//
//import com.example.demo.domain.Stock;
//import com.example.demo.domain.StockRepository;
//import com.example.demo.support.domain.CommonSearch;
//import com.example.demo.web.StockController;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Async;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.PostConstruct;
//
//@Service
//public class Research extends CommonSearch {
//    public static final Logger logger = LoggerFactory.getLogger(Research.class);
//
//    @PostConstruct
//    public void init() {
//        super.init();
//    }
//
//    public Stock update(Stock original) {
//        getStart(original.getDetailUrl());
//        WebDriver driver = getDriver();
//        try {
//            WebElement element = driver.findElement(By.xpath("//*[@id=\"topWrap\"]/div[1]/ul[2]"));
//            String price = element.findElement(By.xpath(".//li[1]/em")).getText();
//            String changeMoney = element.findElement(By.xpath(".//li[2]/span")).getText();
//            String changePercent = element.findElement(By.xpath(".//li[3]")).getText();
//            element = driver.findElement(By.xpath("//*[@id=\"performanceCorp\"]/table/tbody"));
//            String profit = element.findElement(By.xpath(".//tr[5]/td[9]")).getText();
//            String sales_moeny = element.findElement(By.xpath(".//tr[4]/td[9]")).getText();
//            String total_cost = driver.findElement(By.xpath("//*[@id=\"stockContent\"]/ul[2]/li[2]/dl[2]/dd")).getText();
//            original.update(price, changeMoney, changePercent, profit, sales_moeny, total_cost);
//        } catch (org.openqa.selenium.NoSuchElementException e) {
//            logger.info(e.getMessage());
//        }
//        return original;
//    }
//}