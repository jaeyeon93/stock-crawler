package com.example.demo.service;

import com.example.demo.dao.Research;
import com.example.demo.dao.KospiInfo;
import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.google.common.util.concurrent.Futures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class StockService {
    public static final Logger logger = LoggerFactory.getLogger(StockService.class);

    @Resource(name = "stockRepository")
    private StockRepository stockRepository;

    @Autowired
    private Research research;

    @Autowired
    private KospiInfo kospiInfo;

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
        stockRepository.save(kospiInfo.whole());
        long end = System.currentTimeMillis();
        System.out.println("총 걸린 시간 : " + (end - start)/1000.0 + "초");
    }

    @Transactional
    public void update(String stockName) {
        long start =  System.currentTimeMillis();
        Stock stock = stockRepository.findByName(stockName);
        research.update(stock);
        long end = System.currentTimeMillis();
        System.out.println("총 걸린 시간 : " + (end - start)/1000.0 + "초");
    }

    @Transactional
    public void wholeUpdate() {
        long start =  System.currentTimeMillis();
        for (int i = 1; i < stockRepository.findAll().size(); i++)
            research.update(stockRepository.findOne((long)i));
        long end = System.currentTimeMillis();
        System.out.println("총 걸린 시간 : " + (end - start)/1000.0 + "초");
    }
}
