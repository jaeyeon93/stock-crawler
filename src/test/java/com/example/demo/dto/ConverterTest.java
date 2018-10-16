package com.example.demo.dto;

import com.example.demo.domain.Stock;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConverterTest {
    private static final Logger logger =  LoggerFactory.getLogger(ConverterTest.class);
    private Stock stock;
    private Converter converter;

    @Before
    public void setUp() {
        stock = new Stock("삼성전자", 43900, 1400, -3.09, "http://www.naver.com", "http://www.daum.net");
        converter = new Converter(stock, "general");
        logger.info("{}", converter.toString());
    }

    @Test
    public void convertToJson() {
        Gson gson = new Gson();
        String result = gson.toJson(converter);
        logger.info("{}", result);
    }
}
