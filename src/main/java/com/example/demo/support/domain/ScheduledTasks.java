package com.example.demo.support.domain;

import com.example.demo.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScheduledTasks {
    private static final Logger logger =  LoggerFactory.getLogger(ScheduledTasks.class);
    private static final SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private StockService stockService;

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() throws Exception {
        logger.info("The time is now {}", dateformat.format(new Date()));
    }

    @Scheduled(fixedRate = 10000)
    public void call() throws Exception {
        logger.info("10초마다 자동 업데이트");
        stockService.getAllStock();
    }
}
