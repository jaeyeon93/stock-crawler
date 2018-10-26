package com.example.demo;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {
    private static final Logger logger =  LoggerFactory.getLogger(ApplicationTest.class);

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.port=port;
    }

    @Test
    public void 기본path로접근하면_indexHtml호출() throws Exception {
        given()
                .when()
                    .get("/")
                .then()
                    .statusCode(200)
                    .contentType("text/html")
                    .body(containsString("권한"));
    }
}
