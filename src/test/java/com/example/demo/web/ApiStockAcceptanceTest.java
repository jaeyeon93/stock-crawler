package com.example.demo.web;

import com.example.demo.domain.Stock;
import com.example.demo.AcceptanceTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ApiStockAcceptanceTest extends AcceptanceTest {
    private Stock stock;
    public static final Logger logger = LoggerFactory.getLogger(ApiStockAcceptanceTest.class);

    @Test
    public void create() throws Exception {
        String path = createResource("/api/stock", "SK하이닉스");
        logger.info("path : {}", path);
        stock = getResource(path, Stock.class);
        logger.info("info : {}", getResource(path, Stock.class));
    }

    @Test
    public void 소문자이름() throws Exception {
        String path = createResource("/api/stock", "naver");
        logger.info("path info : {}", path);
        stock = getResource(path, Stock.class);
        logger.info("stock info : {}", stock.toString());
    }

    @Test
    public void 소문자한글() throws Exception {
        String path = createResource("/api/stock", "sk하이닉스");
        logger.info("path info : {}", path);
        stock = getResource(path, Stock.class);
        logger.info("stock info : {}", stock.toString());
    }

    @Test
    public void listTest() throws Exception {
        ResponseEntity<String> response = template().getForEntity("/api/stock/list", String.class);
        logger.info("body is {}", response.getBody());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void show() throws Exception {
        ResponseEntity<String> response = template().getForEntity("/api/stock/1", String.class);
        logger.info("Header is {}", response.getHeaders());
        logger.info("body is {}", response.getBody());
        assertThat(response.getStatusCode(), is(HttpStatus.OK));
    }

    @Test
    public void delete() throws Exception {
        template.delete("/api/stock/1");
    }
}
