package com.example.demo.dao;

import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.example.demo.dto.StockDto;
import com.example.demo.support.domain.CommonSearch;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class StockInfo extends CommonSearch {
    private static final Logger logger = LoggerFactory.getLogger(StockInfo.class);
    private JsonParser parser;
    private Stock stock;

    @Autowired
    private StockRepository stockRepository;

    @Value("${batch.size}")
    private int batchSize;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void getAllStockByUrl(String url) throws Exception {
        Map<String, Stock> stockMap = getMap(stockRepository.findAll());
        String body = new Research(url).getBody();
        parser = new JsonParser();
        String [] infos = splitBody(body);
        determineMakeUpdate(infos, stockMap);
        logger.info("batch끝");
        em.flush();
        em.clear();
    }

    public void determineMakeUpdate(String [] infos, Map<String, Stock> stockMap) throws Exception {
        for (int i = 0; i < infos.length; i++) {
            JsonObject object = (JsonObject)parser.parse(infos[i]);
            if (chekcDB(object, stockMap)) {
                stock = stockRepository.findByName(getTitle(object)).realDataUpdate(makeStockDtoByJson(object, parser));
                em.merge(stock);
                insertUsingBatch(stock, i);
                continue;
            }
            insertUsingBatch(makeStockDtoByJson(object, parser).toStock(), i);
        }
    }

    public void insertUsingBatch(Stock stock, int i) {
        em.persist(stock);
        if (i % batchSize == 0) {
            logger.info("{}번째 배치삽입", i);
            em.flush();
            em.clear();
        }
    }
}

