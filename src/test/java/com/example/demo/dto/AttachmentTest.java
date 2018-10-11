package com.example.demo.dto;

import com.example.demo.domain.Stock;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AttachmentTest {
    public static final Logger logger = LoggerFactory.getLogger(AttachmentTest.class);
    private Stock stock;
    private Attachment attachment;

    @Before
    public void setUp() {
        stock = new Stock("testStock", 50000, 1000, 10.0, "http://www.naver.com");
    }

    @Test
    public void 값테스트() {
        attachment = new Attachment("red", stock);
        logger.info("{}", attachment.toString());
    }

}
