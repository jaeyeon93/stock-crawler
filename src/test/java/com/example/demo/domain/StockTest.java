package com.example.demo.domain;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StockTest {
    private static final Logger logger =  LoggerFactory.getLogger(StockTest.class);
    private Stock stock;
    private Double result;

    @Before
    public void setUp() {
        stock = new Stock("test1", "3,170", "35", "-1.09%", "http://finance.daum.net/item/main.daum?code=060310");
    }

    @Test
    public void updateTest() {
        stock.update("3000", "40",1.07, "1000","10000","100000");
        logger.info("바뀐정보 : {}", stock.toString());
        assertThat(stock.getPrice(), is(3000));
        assertThat(stock.getChangePercent(), is(1.07));
    }

    @Test
    public void 리얼데이터업데이트() {
        stock.realDataUpdate("test1", "5000", "500","10%","http://www.naver.com");
        assertThat(stock.getPrice(), is(5000));
    }

    @Test
    public void stringToInteger() {
        assertThat(stock.getPrice(), is(3170));
        logger.info("changePrice : {}", stock.getChangePercent());
    }

    @Test
    public void numberTest() {
        String test = "-0.33%";
        if (test.contains("+")) {
            result = Double.parseDouble(test.replace("%","").replace("+", "")) * (1.0);
            assertThat(result, is(0.33));
        }
        test = "-0.33%";
        if (test.contains("-")) {
            result = Double.parseDouble(test.replace("%","").replace("-", "")) * (-1.0);
            assertThat(result, is(-0.33));
        }
    }

    @Test
    public void changePirce() {
        stock = new Stock("test", "2620", "▲10", "1.8%", "http://www.naver.con");
        int changePrice = stock.changePrice(stock.getChangeMoney());
        assertThat(changePrice, is(10));
    }

    @Test
    public void changePercentMinus() {
        assertThat(stock.getChangePercent(), is(-1.09));
    }

    @Test
    public void changePercentPlus() {
        assertThat(stock.getChangePercent(), is(11.09));
    }
}
