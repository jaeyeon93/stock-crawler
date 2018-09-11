package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.example.demo.support.domain.CommonSearch;
import org.openqa.selenium.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockInfo extends CommonSearch {
    private static final Logger logger = LoggerFactory.getLogger(StockInfo.class);

    @Resource(name = "stockRepository")
    private StockRepository stockRepository;

    @PersistenceContext
    private EntityManager em;

    @Value("${kospiUrl}")
    private String kospiUrl;

    @Value("${batch.size}")
    private Integer batchSize;

    @PostConstruct
    public void init() {
        super.init();
    }

    @Transactional
    public void stockCrawling(String url) {
        long start = System.currentTimeMillis();
        getStart(url);
        try {
            for (int i = 1; i <= 2; i++) {
                List<WebElement> elements = getElements(i);
                List<Stock> originalStocks = stockRepository.findAll();
                for (int j = 0; j < elements.size(); j++) {
                    em.persist(making(elements.get(j), originalStocks));
                    if (j % batchSize == 0) {
                        logger.info("{}part {}번째 insert", i, j);
                        em.flush();
                        em.clear();
                    }
                }
                em.flush();
                em.clear();
            }
        } catch (Exception e) {
            logger.info("message : {}", e.getMessage());
        }
        long end = System.currentTimeMillis();
        logger.info("총 걸린 시간 : {}초", (end - start) / 1000.0);
    }

    @Async("threadPoolTaskExecutor")
    @Transactional
    public void bulkInsert(int partNumber, String url) throws Exception {
        getStart(url);
        long start = System.currentTimeMillis();
        try {
            List<WebElement> elements = getElements(partNumber);
            List<Stock> originalStocks = stockRepository.findAll();
            for (int i = 0; i < elements.size(); i++) {
                logger.info("{} size is {}", partNumber, elements.size());
                em.persist(making(elements.get(i), originalStocks));
                if (i % batchSize == 0) {
                    logger.info("batch i : {}", i);
                    em.flush();
                    em.clear();
                }
            }
            em.flush();
            em.clear();
        } catch (Exception e) {
            logger.info("error {}", e.getMessage());
        }
        long end = System.currentTimeMillis();
        logger.info("총 걸린 시간 : {}초", (end - start) / 1000.0);
    }
}

