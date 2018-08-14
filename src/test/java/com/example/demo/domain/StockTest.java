package com.example.demo.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StockTest {
    private static final Logger logger =  LoggerFactory.getLogger(StockTest.class);
    private Stock stock;

    @Before
    public void setUp() {
        stock = new Stock("삼성전자", "46,650", "536,450", "2,994,615억");
    }

    @Test
    public void nameTest() {
        assertThat(stock.getName(), is("삼성전자"));
    }

    @Test
    public void upperCaseTest() {
        stock = new Stock("naver", "46,650", "536,450", "2,994,615억");
        logger.info("stock : {}", stock.toString());
        String name = stock.getName().toUpperCase();
        logger.info("change name : {}", name);
    }

    @Test
    public void upperCaseTest2() {
        stock = new Stock("sk하이닉스", "46,650", "536,450", "2,994,615억");
        logger.info("stock : {}", stock.toString());
        String name = stock.getName().toUpperCase();
        logger.info("change name : {}", name);
    }

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
}
