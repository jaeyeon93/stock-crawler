package com.example.demo.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class kospiInfoTest {
    private static final Logger logger =  LoggerFactory.getLogger(kospiInfoTest.class);
    private kospiInfo kospiInfo;

    @Before
    public void setUp() {
    }

    @Test
    public void url가지고오기() {
        kospiInfo = new kospiInfo();
        kospiInfo.init();
        String url = kospiInfo.getWholeInfoUrl();
        System.out.println("url : " + kospiInfo.getWholeInfoUrl());
        assertThat(url, is("http://finance.daum.net/quote/allpanel.daum?stype=P&type=S"));
    }
}
