package com.example.demo.security;

import com.example.demo.domain.Account;
import com.example.demo.domain.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtFactoryTest {
    public static final Logger logger = LoggerFactory.getLogger(JwtFactoryTest.class);

    private AccountContext context;

    @Autowired
    private JwtFactory factory;

    @Before
    public void setUp() {
        Account account = new Account();
        logger.error("userId : {}, password : {}, role : {}", account.getUserId(), account.getPassword(), account.getUserRole());
        this.context = AccountContext.fromAccountModel(account);
    }

    @Test
    public void jwtGenerateTest() {
        logger.error(factory.generateToken(this.context));
    }
}
