package com.example.demo.dao;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResearchTest {
    private static final Logger logger =  LoggerFactory.getLogger(ResearchTest.class);

    private Research research;
    private String stockName1;
    private String stockName2;

    @Before
    public void setUp() {
        stockName1 = "sk하이닉스";
        stockName2 = "naver";
    }

    @Test
    public void checkTest() {
        logger.info("stockName1 : {}", stockName1);
        research = new Research(stockName1);
    }
}
