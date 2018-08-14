package com.example.demo.service;

import com.example.demo.dao.Research;
import com.example.demo.dao.kospiInfo;
import com.example.demo.domain.Stock;
import com.example.demo.domain.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class StockService {
    public static final Logger logger = LoggerFactory.getLogger(StockService.class);

    @Resource(name = "stockRepository")
    private StockRepository stockRepository;

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

    public Stock add(String stockName) throws Exception {
        Research research = new Research(stockName);
        return stockRepository.save(research.update(stockRepository.findByName(stockName.toUpperCase()), checkMakingStock(stockName)));
    }

    public boolean checkMakingStock(String stockName) {
        Stock stock = stockRepository.findByName(stockName.toUpperCase());
        if (stock != null && stock.getDiffday() < 15)
            return true;
        logger.info("stockName : {}", stockName);
        return false;
    }

    @Transactional
    public void delete(long id) throws Exception {
        logger.info("delete method called {}", id);
        stockRepository.delete(id);
    }

    @Transactional
    public void serviceOne() throws Exception {
        System.out.println("service one method called");
        kospiInfo kospiInfo = new kospiInfo();
        stockRepository.save(kospiInfo.oneInfo());
    }

    @Transactional
    public void addAll() throws Exception {
        long startTime = System.currentTimeMillis();
        System.out.println("시작시간 : " + startTime);
        kospiInfo kospiInfo = new kospiInfo();
        stockRepository.save(kospiInfo.part1());
        stockRepository.save(kospiInfo.part2());
        stockRepository.save(kospiInfo.part3());
        long endTime = System.currentTimeMillis();
        System.out.println("종료시간 : " + endTime);
        System.out.println("총 걸린 시간 : " + (endTime - startTime));

    }
}
