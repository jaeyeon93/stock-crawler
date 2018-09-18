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
    private StockDto stock;
    private Double result;

    @Before
    public void setUp() {
        stock = new StockDto("test1", "3,170", "35", "-1.09%", "http://finance.daum.net/item/main.daum?code=060310");
    }


    @Test
    public void stringToInteger() {
        assertThat(stock.getCost(), is(3170));
        logger.info("changePrice : {}", stock.getRate());
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
        assertThat(stock.getRate(), is(-1.09));
    }

    @Test
    public void changePercentPlus() {
        assertThat(stock.getRate(), is(11.09));
    }
}
