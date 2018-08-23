package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.service.StockService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class kospiInfoTest {
    private static final Logger logger = LoggerFactory.getLogger(kospiInfoTest.class);

    @Autowired
    private KospiInfo kospiInfo;

    @Autowired
    private StockService stockService;

    @Before
    public void setUp() {
        kospiInfo.getStart("http://finance.daum.net/quote/allpanel.daum?stype=P&type=S");
    }

    @Test
    public void 전체크롤링() {
        List<Stock> stocks = kospiInfo.whole();
        logger.info("stocks size : {}", stocks.size());
    }

    @Test
    public void db에정보확인() {
        List<Stock> stocks = stockService.findAll();
        logger.info("Stocks의 사이즈 : {}", stocks.size());
        assertThat(stocks.get(0).getName(), is("삼성전자"));
    }

    @Test
    public void db에주식이있으면true() {
        boolean result =stockService.checkMakingStock("삼성전자");
        assertThat(result, is(true));
    }

    @Test
    public void db주식존재주식생성() {
        long start = System.currentTimeMillis();
        Stock stock = kospiInfo.makeStock(3,202);
        long end = System.currentTimeMillis();
        System.out.println("총 걸린 시간 : " + (end - start)/1000.0 + "초");
        logger.info("stock info : {}", stock.toString());
    }

    @Test
    public void db주식없음주식생성() {
        long start = System.currentTimeMillis();
        Stock stock = kospiInfo.makeStock(1,202);
        long end = System.currentTimeMillis();
        System.out.println("총 걸린 시간 : " + (end - start)/1000.0 + "초");
        logger.info("stock info : {}", stock.toString());
    }

    @Test
    public void 주식확인() {
        WebElement element = kospiInfo.getDriver().findElement(By.xpath("//*[@id=\"wrap\"]/div[3]/div/div[3]/dl[202]"));
        logger.info("title : {}", kospiInfo.getTitle(element));
        assertThat(kospiInfo.getTitle(element), is("삼성전자"));
        boolean result = kospiInfo.checkDb(element);
        assertThat(result, is(true));
    }

    @Test
    public void 주식존재하지않음() {
        WebElement element = kospiInfo.getDriver().findElement(By.xpath("//*[@id=\"wrap\"]/div[3]/div/div[3]/dl[203]"));
        logger.info("title : {}", kospiInfo.getTitle(element));
        boolean result = kospiInfo.checkDb(element);
        assertThat(result, is(false));
    }
}
