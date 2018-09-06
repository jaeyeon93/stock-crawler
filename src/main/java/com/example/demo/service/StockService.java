package com.example.demo.service;

import com.example.demo.dao.StockInfo;
import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class StockService {
    public static final Logger logger = LoggerFactory.getLogger(StockService.class);

    @PersistenceContext
    private EntityManager em;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    @Resource(name = "stockRepository")
    private StockRepository stockRepository;

    @Autowired
    private StockInfo stockInfo;

    @Value("${kosdaqUrl}")
    private String kosdaqUrl;

    @Value("${kospiUrl}")
    private String kospiUrl;

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

//    @Transactional
    public void addAll() throws Exception {
        long start = System.currentTimeMillis();
//        stockRepository.save(stockInfo.stockCrawling(kospiUrl));
//        stockRepository.save(stockInfo.stockCrawling(kosdaqUrl));
        for (int i = 1; i <= 4;i ++) {
            stockInfo.partCrawing(i, kospiUrl);
            stockInfo.partCrawing(i, kosdaqUrl);
        }
        long end = System.currentTimeMillis();
        logger.info("총 걸린 시간 : {}초", (end - start)/1000.0);
    }

    @Transactional
    public void update(String stockName) throws IOException {
        long start =  System.currentTimeMillis();
        Stock stock = stockRepository.findByName(stockName);
        stockInfo.update(stock);
        long end = System.currentTimeMillis();
        logger.info("총 걸린 시간 : {}초", (end - start)/1000.0);
    }

    @Transactional
    public void wholeUpdate() throws IOException {
        long start =  System.currentTimeMillis();
        for (int i = 1; i  <= stockRepository.findAll().size(); i++)
            stockInfo.update(stockRepository.findOne((long)i));
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
