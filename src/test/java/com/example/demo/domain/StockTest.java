package com.example.demo.domain;

import com.example.demo.dto.StockDto;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StockTest {
    private static final Logger logger =  LoggerFactory.getLogger(StockTest.class);
    private StockDto stockDto;
    private Double result;
    private Stock stock;

    @Before
    public void setUp() {
        stockDto = new StockDto("test1", "3,170", "35", "-1.09%", "http://finance.daum.net/item/main.daum?code=060310");
    }

    @Test
    public void 영업이익률() {
        double sales = 4018.3;
        double profit = 116.81;
        double result  = (profit/sales)*100;
        logger.info("result : {}", result);
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
}
