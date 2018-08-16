package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.service.StockService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class kospiInfoTest {
    private static final Logger logger =  LoggerFactory.getLogger(kospiInfoTest.class);

    @Autowired
    private KospiInfo kospiInfo;

    @Autowired
    private StockService stockService;

    @Test
    public void 주식크롤링테스트1() {
        kospiInfo.whole1();
        Stock stock = stockService.findById(3);
        logger.info("찾은 주식 : {}", stock.toString());
    }
}
