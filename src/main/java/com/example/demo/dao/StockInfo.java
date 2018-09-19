package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.example.demo.support.domain.CommonSearch;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Map;

@Service
public class StockInfo extends CommonSearch {
    private static final Logger logger = LoggerFactory.getLogger(StockInfo.class);
    private JsonParser parser;

    @Autowired
    private StockRepository stockRepository;

    @Value("${batch.size}")
    private int batchSize;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void getAllStockByUrl(String url) throws IOException {
        Map<String, Stock> stockMap = getMap(stockRepository.findAll());
        String body = new Research(url).getBody();
        parser = new JsonParser();
        String [] infos = splitBody(body);
        for (int i = 0; i < infos.length; i++) {
            em.persist(makeStockByJson(infos[i], parser, stockMap));
            if (i % batchSize == 0) {
                logger.info("{}번째i batch실행", i);
                em.flush();
                em.clear();
            }
        }
        logger.info("batch끝");
        em.flush();
        em.clear();
    }
}

