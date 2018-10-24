package com.example.demo.service;

import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.example.demo.dto.RealData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockServiceTest {
    private static final Logger logger =  LoggerFactory.getLogger(StockServiceTest.class);

    @Autowired
    private StockService stockService;

    @Autowired
    private StockRepository stockRepository;

    @Before
    public void setUp() throws Exception {
        stockService.getAllStock();
    }

    @Test
    public void 단기통안채테스트() throws IOException {
        Stock tiger = stockRepository.findByName("TIGER 단기통안채");
        logger.info("{}", tiger.toString());
        assertThat(tiger.getName(), is("TIGER 단기통안채"));
        Stock edit = stockService.updateByStockName(tiger.getName());
        logger.info("after : {}", edit.toString());
        stockService.detailWholeUpdate();
    }
}
