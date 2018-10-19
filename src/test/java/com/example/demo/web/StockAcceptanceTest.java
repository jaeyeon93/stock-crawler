package com.example.demo.web;

import com.example.demo.AcceptanceTest;
import com.example.demo.HtmlFormDataBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import javax.xml.ws.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StockAcceptanceTest extends AcceptanceTest {
    public static final Logger logger = LoggerFactory.getLogger(StockAcceptanceTest.class);

    @Test
    public void addlowerCaseName() throws Exception {
        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder.urlEncodeForm()
                .addParameter("stockName", "naver").build();
        ResponseEntity<String> response = template().postForEntity("/stock/naver", request, String.class);
    }

    @Test
    public void 소문자한글포함() throws Exception {
        HttpEntity<MultiValueMap<String, Object>> request = HtmlFormDataBuilder.urlEncodeForm()
                .addParameter("stockName", "sk하이닉스").build();
        ResponseEntity<String> response = template().postForEntity("/stock/sk하이닉스", request, String.class);
    }

}
