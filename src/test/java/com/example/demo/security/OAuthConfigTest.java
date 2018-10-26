package com.example.demo.security;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = "spring.config.location=classpath:/google.yml")
public class OAuthConfigTest {
    private static final Logger logger =  LoggerFactory.getLogger(OAuthConfigTest.class);

    @LocalServerPort
    int port;

    @Before
    public void setUp() {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=port;
    }

    @Test
    public void google로그인_시도하면_OAuth인증창_등장한다() throws Exception {
        given()
                .when()
                    .redirects().follow(false) // 리다이렉트방지
                    .get("/login")
                .then()
                    .statusCode(302)
                    .header("Location", containsString("https://accounts.google.com/o/oauth2/auth"));
    }
}
