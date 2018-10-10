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

//    @Scheduled(fixedRate = 5000)
//    public void reportCurrentTime() throws Exception {
//        logger.info("The time is now {}", dateformat.format(new Date()));
//    }

//    @Scheduled(cron = "0 * 9-23 * * MON-FRI")
//    public void getAllStock() throws Exception {
//        logger.info("평일 9시-16시까지 1분마다 자동업데이트");
//        stockService.getAllStock();
//    }
//
//    @Scheduled(cron = "1 * 16 * * MON-FRI")
//    public void detailWholeUpdate() throws Exception {
//        logger.info("평일 16:01, 장 종료 후 전체 업데이트 진행");
//        stockService.detailWholeUpdate();
//    }
//
//    @Scheduled(cron = "0 0 0 * * SAT,SUN")
//    public void getWeekUpdate() throws Exception {
//        logger.info("토, 일 0시0분0초에 전체 주식정보 업데이트");
//        stockService.detailWholeUpdate();
//    }
}
