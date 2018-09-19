package com.example.demo.dto;

import com.example.demo.domain.Stock;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StockDtoTest {
    private static final Logger logger =  LoggerFactory.getLogger(StockDtoTest.class);
    private Gson gson;
    private JsonParser parser = new JsonParser();
    private JsonObject object;

    @Before
    public void setUp() {
        gson = new Gson();
    }

    @Test
    public void test() {
        String test = "{code:\"005930\",name :\"삼성전자\",cost :\"45,150\",updn :\"▼700\",rate :\"-1.53%\"}";
        object = (JsonObject)parser.parse(test);
        logger.info("object name : {}", object.get("name"));
        logger.info("{}", object.toString());
        StockDto dto = gson.fromJson(object, StockDto.class);
        logger.info("dto : {}", dto.toString());
        Stock stock = gson.fromJson(object, StockDto.class).toStock();
        logger.info("stock정보 : {}", stock.toString());
    }

    @Test
    public void 한미약품() {
        String hanmi = "{code:\"128940\",name :\"한미약품\",cost :\"505,000\",updn :\"▲9,000\",rate :\"+1.81%\"}";
        boolean result = hanmi.contains("▲");
        assertThat(result, is(true));
        StockDto dto = gson.fromJson(hanmi, StockDto.class);
        logger.info("{}", dto.toString());
        logger.info("before : {}", dto.getUpdn());
        int test = dto.updnToInteger(dto.getUpdn());
        logger.info("after : {}", test);
        Stock stock = dto.toStock();
        logger.info("stock : {}", stock.toString());
    }

}
