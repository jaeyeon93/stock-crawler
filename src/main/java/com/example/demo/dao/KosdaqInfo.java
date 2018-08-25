package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.example.demo.support.domain.CommonSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class KosdaqInfo extends CommonSearch {
    private static final Logger logger =  LoggerFactory.getLogger(KosdaqInfo.class);

    @Value("${kosdaqUrl}")
    private String kosdaqUrl;

    @Autowired
    private StockRepository stockRepository;

    @PostConstruct
    public void init() {
        super.init();
    }

    @Async("threadPoolTaskExecutor")
    public void part(int partNumber) throws Exception {
        long start = System.currentTimeMillis();
        getStart(kosdaqUrl);
        List<Stock> stocks = new ArrayList<>();
        try {
            for (int i = 1; i <= 324; i++)
                stocks.add(makeStock(partNumber, i));
        } catch (org.openqa.selenium.StaleElementReferenceException e) {
            logger.info("message : {}", e.getMessage());
        }
        long end = System.currentTimeMillis();
        logger.info("코스닥 총 걸린 시간 : {}초", (end - start)/1000.0);
        stockRepository.save(stocks);
    }
}
