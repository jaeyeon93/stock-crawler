package com.example.demo.bot;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageTest {
    private static final Logger logger =  LoggerFactory.getLogger(MessageTest.class);

    @Test
    public void 메세지유효성테스트() {
        String beforeTrim = " ";
        logger.info("beforeTrim : {}", beforeTrim);
        logger.info("{}", beforeTrim.trim().length());
        String stockName = "ARIRANG 단기채권액티브";
        logger.info("{}", stockName.replace(" ",""));
    }
}
