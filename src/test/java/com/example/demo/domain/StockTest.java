package com.example.demo.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StockTest {
    private static final Logger logger =  LoggerFactory.getLogger(StockTest.class);
    private Stock stock;
    private Double result;

    @Test
    public void timeTest() throws Exception {
        String format = "yyyyMMdd";;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String startDate = "20180701";
        String endDate = "20180702";
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        long diff = (end.getTime() - start.getTime());
        System.out.println("diff : " + diff);
        System.out.println("diff time : " + (diff / (3600 * 1000)));
    }

    @Test
    public void currentTime() throws Exception {
        stock = new Stock(1L, "naver","760,000", "1000","1000");
        logger.info("현재시간 : {}", LocalDateTime.now().getDayOfYear());
    }

    @Test
    public void atomicInteger() {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        logger.info("default value : {}", atomicInteger.getAndIncrement());
        for (int i = 1; i < 30; i++) {
            logger.info("value1 : {}", atomicInteger.getAndIncrement());
            logger.info("value2 : {}", atomicInteger.getAndIncrement());
            logger.info("value3 : {}", atomicInteger.getAndIncrement());
            logger.info("value4 : {}", atomicInteger.getAndIncrement());

        }
    }

    @Test
    public void updateTest() {
        stock = new Stock("test1", "3,170", "35", "-1.09%", "http://finance.daum.net/item/main.daum?code=060310");
        stock.update("3000", "40",1.07, "1000","10000","100000");
        logger.info("바뀐정보 : {}", stock.toString());
        assertThat(stock.getPrice(), is(3000));
        assertThat(stock.getChangePercent(), is(1.07));
    }

    @Test
    public void stringToInteger() {
        stock = new Stock("test1", "3,170", "35", "+0.33％", "http://finance.daum.net/item/main.daum?code=060310");
        assertThat(stock.getPrice(), is(3170));
        logger.info("changePercent : {}", stock.getChangePercent());
//        assertThat(stock.getChangePercent(), is(0.33));
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
    public void changePercentMinus() {
        stock = new Stock("test1", "3,170", "35", "-1.09%", "http://finance.daum.net/item/main.daum?code=060310");
        assertThat(stock.getChangePercent(), is(-1.09));
    }

    @Test
    public void changePercentPlus() {
        stock = new Stock("test1", "3,170", "35", "+11.09%", "http://finance.daum.net/item/main.daum?code=060310");
        assertThat(stock.getChangePercent(), is(11.09));
    }
}
