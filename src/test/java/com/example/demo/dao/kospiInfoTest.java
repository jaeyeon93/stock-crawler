package com.example.demo.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class kospiInfoTest {
    private static final Logger logger =  LoggerFactory.getLogger(kospiInfoTest.class);
    private String wholeInfoUrl = "http://finance.daum.net/quote/allpanel.daum?stype=P&type=U";
    private kospiInfo kospiInfo;

    @Before
    public void setUp() {
        kospiInfo = new kospiInfo();
    }

    @Test
    public void url가지고오기() {
        String url = kospiInfo.getWholeInfoUrl();
        System.out.println("url : " + url);
    }
}
