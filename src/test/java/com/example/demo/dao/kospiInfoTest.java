package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.service.StockService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class kospiInfoTest {
    private static final Logger logger = LoggerFactory.getLogger(kospiInfoTest.class);
    private String kospiUrl = "http://finance.daum.net/quote/allpanel.daum?stype=P&type=S";
    @Autowired
    private StockInfo stockInfo;

    @Autowired
    private StockService stockService;

    @Before
    public void setUp() {
        stockInfo.getStart("http://finance.daum.net/quote/allpanel.daum?stype=P&type=S");
    }

    @Test
    public void 소스정보() {
        String result = stockInfo.getHtml("http://finance.daum.net/quote/allpanel.daum?stype=P&type=S");
        logger.info("result : {}", result);
    }

    @Test
    public void 리스트갯엘리먼트() throws Exception {
        stockService.addAll();
//        List<WebElement> list = stockInfo.getElements(1);
    }

    @Test
    public void updateTest() throws IOException {
        Stock stock = stockService.findByName("삼성전자");
        stockInfo.update(stock);
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
}
