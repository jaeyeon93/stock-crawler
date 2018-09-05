package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.service.StockService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
    private StockInfo stockInfo;

    @Autowired
    private StockService stockService;

    @Before
    public void setUp() {
        stockInfo.getStart("http://finance.daum.net/quote/allpanel.daum?stype=P&type=S");
    }

    @Test
    public void 크롤링() throws Exception {
        long start = System.currentTimeMillis();
        stockService.addAll();
        long end = System.currentTimeMillis();
        System.out.println("총 걸린 시간 : " + (end - start)/1000.0 + "초");
    }

    @Test
    public void partTest() throws Exception {
        stockInfo.stockCrawling("http://finance.daum.net/quote/allpanel.daum?stype=P&type=S");
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
