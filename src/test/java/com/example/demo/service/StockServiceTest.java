package com.example.demo.service;

import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.example.demo.dto.RealData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
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
    public void 단기통안채테스트() throws Exception {
        Stock tiger = stockRepository.findByName("TIGER 단기통안채");
        logger.info("{}", tiger.toString());
        assertThat(tiger.getName(), is("TIGER 단기통안채"));
        Stock edit = stockService.updateByStockName(tiger.getName());
        logger.info("after : {}", edit.toString());
    }

    @Test
    public void 힘스테스트() throws Exception {
        Stock stock = stockRepository.findByName("힘스");
        logger.info("주식의 사이즈 : {}", stockRepository.findAll().size());
        assertThat(stock.getName(), is("힘스"));
        Stock edit = stockService.updateByStockName(stock.getName());
        logger.info("after : {}", edit.toString());
    }

    @Test
    public void KB우량주에러() throws Exception {
        Stock stock = stockRepository.findByName("KB KQ 우량주30 ETN");
        logger.info("kb : {}", stock.toString());
        assertThat(stock.getName(), is("KB KQ 우량주30 ETN"));
        logger.info("UpdateUrl : {}", stock.getUpdateUrl());
        Stock edit = stockService.updateByStockName(stock.getName());
        logger.info("after : {}", edit.toString());
    }

    @Test
    public void 전체테스트() throws IOException {
        logger.info("size : {}", stockRepository.findAll().size());
        stockService.detailWholeUpdate();
    }
}
