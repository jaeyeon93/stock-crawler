package com.jimmy.crawler.dao;

import com.jimmy.crawler.domain.Stock;
import com.jimmy.crawler.service.StockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BulkTest {
    private static final Logger logger =  LoggerFactory.getLogger(BulkTest.class);

    @Autowired
    private StockInfo stockInfo;

    @Autowired
    private StockService stockService;

    @PersistenceContext
    private EntityManager em;

    @Value("${kospiUrl}")
    private String kospiUrl;

    private int batchSize = 20;

    @Test
    public void 배치사이즈테스트() {
        assertThat(batchSize, is(20));
    }

    @Test
    @Transactional
    public void bulk() throws Exception {
        stockInfo.getStart(kospiUrl);
        logger.info("current url : {}", stockInfo.getDriver().getCurrentUrl());
        List<WebElement> elements = stockInfo.getElements(1);
        logger.info("elements size : {}", elements.size());
        List<Stock> stocks = stockService.findAll();

        for (int i = 0; i < elements.size(); i++) {
            Stock stock = stockInfo.making(elements.get(i), stocks);
            em.persist(stock);
            if (i % batchSize == 0) {
                logger.info("bulk test삽입중, i는 {}", i);
                em.flush();
                em.clear();
            }
        }
        em.flush();
        em.clear();
    }


}
