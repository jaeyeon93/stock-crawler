package com.example.demo.service;

import com.example.demo.dao.StockInfo;
import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import com.example.demo.dto.StockDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {
    public static final Logger logger = LoggerFactory.getLogger(StockService.class);

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

    public Stock getStockById(long id) {
        return stockRepository.findOne(id);
    }

    public Stock getStockByStockName(String stockName) {
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
    public void deleteStockById(long id) throws Exception {
        logger.info("deleteStockById method called {}", id);
        stockRepository.delete(id);
    }

    @Transactional
    public void getAllStock() throws Exception {
        long start = System.currentTimeMillis();
        stockRepository.save(stockInfo.getAllStockByUrl(kospiUrl));
        stockRepository.save(stockInfo.getAllStockByUrl(kosdaqUrl));
        long end = System.currentTimeMillis();
        logger.info("총 걸린 시간 : {}초", (end - start)/1000.0);
    }

    @Transactional
    public Stock updateByStockName(String stockName) throws IOException {
        Stock stock = stockRepository.findByName(stockName);
        return stockInfo.updateByStockName(stock);
    }

    @Transactional
    public void detailWholeUpdate() throws IOException {
        long start =  System.currentTimeMillis();
        List<Stock> original = stockRepository.findAll();
        List<Stock> stocks = new ArrayList<>();
        for (int i = 100; i  < 200; i++)
            stocks.add(stockInfo.updateByStockName(original.get(i)));
        stockRepository.save(stocks);
        long end = System.currentTimeMillis();
        logger.info("총 업데이트 시간 : {}초", (end - start)/1000.0);
    }


    public List<Stock> lowPercent() {
        return stockRepository.findAllByOrderByRateAsc();
    }

    public List<Stock> lowPrice() {
        return stockRepository.findAllByOrderByCostAsc();
    }

    public List<Stock> topPercent() {
        return stockRepository.findAllByOrderByRateDesc();
    }

    public List<Stock> topPrice() {
        return stockRepository.findAllByOrderByCostDesc();
    }

    public List<Stock> searchByStockName(String stockName) {
        return stockRepository.findByNameStartingWith(stockName);
    }
}

