package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtFactory {
    public static final Logger logger = LoggerFactory.getLogger(JwtFactory.class);

    private static String signingKey = "jwttest";

    public String generateToken(AccountContext context) {

        String token = null;
        try {
            token = JWT.create()
                    .withIssuer("jimmy")
                    .withClaim("USER_ROLE", context.getAccount().getUserRole().getRoleName())
                    .sign(generateAlgorithm());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return token;
    }

    private Algorithm generateAlgorithm() {
        return Algorithm.HMAC256(signingKey);
    }
}
