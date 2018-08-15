package com.example.demo.dao;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class kospiInfoTest {
    private static final Logger logger =  LoggerFactory.getLogger(kospiInfoTest.class);
    private KospiInfo KospiInfo;

    @Before
    public void setUp() {
    }

}
