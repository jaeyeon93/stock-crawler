package com.example.demo.service;

import com.example.demo.dao.KosdaqInfo;
import com.example.demo.dao.KospiInfo;
import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class StockService {
    public static final Logger logger = LoggerFactory.getLogger(StockService.class);

    @Resource(name = "stockRepository")
    private StockRepository stockRepository;

    @Autowired
    private KospiInfo kospiInfo;

    @Autowired
    private KosdaqInfo kosdaqInfo;

    public List<Stock> findAll() {
        return stockRepository.findAll();
    }

    public Stock findById(long id) {
        return stockRepository.findOne(id);
    }

    public Stock findByName(String stockName) {
        logger.info("stockName on Service : {}", stockName);
        return stockRepository.findByName(stockName);
    }

    public boolean checkMakingStock(String stockName) {
        Stock stock = stockRepository.findByName(stockName.toUpperCase());
        if (stock != null)
            return true;
        logger.info("주식이 DB에 없음");
        return false;
    }

    @Transactional
    public void delete(long id) throws Exception {
        logger.info("delete method called {}", id);
        stockRepository.delete(id);
    }

    @Transactional
    public void addAll() throws Exception {
        long start = System.currentTimeMillis();
//        for (int i = 1; i <= 4; i++)
//            kospiInfo.part2(i);
        kospiInfo.batchInsert();
        long end = System.currentTimeMillis();
        logger.info("총 걸린 시간 : {}초", (end - start)/1000.0);
    }

    @Transactional
    public void update(String stockName) {
        long start =  System.currentTimeMillis();
        Stock stock = stockRepository.findByName(stockName);
        kosdaqInfo.getUpdate(stock);
        long end = System.currentTimeMillis();
        logger.info("총 걸린 시간 : {}초", (end - start)/1000.0);
    }

    @Transactional
    public void wholeUpdate() {
        long start =  System.currentTimeMillis();
        for (int i = 1; i <= 20; i++)
            kosdaqInfo.getUpdate(stockRepository.findOne((long)i));
        long end = System.currentTimeMillis();
        logger.info("총 업데이트 시간 : {}초", (end - start)/1000.0);
    }

    public List<Stock> lowPercent() {
        return stockRepository.findAllByOrderByChangePercentAsc();
    }

    public List<Stock> lowPrice() {
        return stockRepository.findAllByOrderByPriceAsc();
    }

    public List<Stock> topPercent() {
        return stockRepository.findAllByOrderByChangePercentDesc();
    }

    public List<Stock> topPrice() {
        return stockRepository.findAllByOrderByPriceDesc();
    }
}
