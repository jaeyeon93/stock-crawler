package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.dto.Field;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class FieldTest {
    public static final Logger logger = LoggerFactory.getLogger(FieldTest.class);

    private Field field1;
    private Field field2;
    private Field field3;

    @Before
    public void setUp() {
        Stock stock = new Stock("testStock", 50000, 1000, 10.0, "http://www.naver.com");
        field1 = new Field("주가", String.valueOf(stock.getCost()));
        field2 = new Field("변동가격", String.valueOf(stock.getUpdn()));
        field3 = new Field("변동률", String.valueOf(stock.getRate()));
    }

    @Test
    public void 값확인() {
        assertThat("주가", is(field1.getTitle()));
        assertThat("50000", is(field1.getValue()));
        assertThat("변동가격", is(field2.getTitle()));
        assertThat("1000", is(field2.getValue()));
        assertThat("10.0", is(field3.getValue()));
        logger.info("{}", field1.toString());
        logger.info("{}", field2.toString());
        logger.info("{}", field3.toString());
    }
}
