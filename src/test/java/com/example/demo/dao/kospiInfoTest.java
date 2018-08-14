package com.example.demo.dao;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class kospiInfoTest {
    private static final Logger logger =  LoggerFactory.getLogger(kospiInfoTest.class);
    private String wholeInfoUrl = "http://finance.daum.net/quote/allpanel.daum?stype=P&type=U";
    private kospiInfo kospiInfo;

    @Before
    public void setUp() {
        kospiInfo = new kospiInfo();
    }

    @Test
    public void 태그갯수() {
        System.out.println(kospiInfo.divCount());
    }

    @Test
    public void getBodyTest() {
        kospiInfo = new kospiInfo();
        String result = kospiInfo.getBody();
        logger.info("result : {}", result);
    }

    @Test
    public void getTest() {
        String result = kospiInfo.getTest();
        logger.info("result : {}", result);
    }
}
