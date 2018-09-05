package com.example.demo.dao;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class ResearchTest {
    private static final Logger logger =  LoggerFactory.getLogger(ResearchTest.class);
    private Research research;

    @Before
    public void setUp() throws Exception {
        research = new Research("http://finance.daum.net/item/main.daum?code=005930");
    }

    @Test
    public void getElements() throws Exception {
        List<String> elements = research.getElements();
        for (String s : elements)
            logger.info("value : {}", s);
    }

    @Test
    public void 시가총액() throws Exception {
        logger.info("{}", research.getTotalCost());
    }

    @Test
    public void 업데이트() throws Exception {
        List<String> elements = research.getElements();
        logger.info("주가 : {}", elements.get(0));
        logger.info("변동가격 : {}", elements.get(1));
        logger.info("변동률 : {}", elements.get(2));
        logger.info("시가총액 : {}", research.getTotalCost());
        logger.info("매출액 : {}", research.getSalesMoney());
        logger.info("영업이익 : {}", research.getProfit());
    }
}
