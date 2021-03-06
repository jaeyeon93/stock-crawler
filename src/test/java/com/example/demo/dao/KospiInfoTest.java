package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.example.demo.service.StockService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KospiInfoTest {
    private static final Logger logger = LoggerFactory.getLogger(KospiInfoTest.class);
    private JsonParser parser;

    @Autowired
    private StockInfo stockInfo;

    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

    @Before
    public void setUp() {
        parser = new JsonParser();
    }

    @Test
    public void crawlingTest() throws Exception {
        stockService.getAllStock();
    }

    @Test
    public void updateTest() throws Exception {
        stockService.getAllStock();
        Stock stock = stockService.updateByStockName("아모레퍼시픽");
        logger.info("{}", stock.toString());
    }

    @Test
    public void db에주식이있으면true() {
        boolean result = stockService.checkMakingStock("삼성전자");
        assertThat(result, is(true));
    }

    @Test
    public void checkDB확인() {
        List<Stock> original = stockService.findAll();
        logger.info("first : {}", original.get(0));
        JsonObject object = (JsonObject)parser.parse("{code:\"005930\",name :\"삼성전자\",cost :\"45,150\",updn :\"▼700\",rate :\"-1.53%\"}");
        Map<String, Stock> map = stockInfo.getMap(original);
        boolean checkDB = stockInfo.chekcDB(object, map);
        assertThat(checkDB, is(true));
    }

}
