package com.example.demo.security.handlers;

import com.example.demo.dto.TokenDto;
import com.example.demo.security.AccountContext;
import com.example.demo.security.JwtFactory;
import com.example.demo.security.tokens.PostAuthorizationToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FormLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtFactory jwtFactory;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //Todo write JWT value on response writer.
        PostAuthorizationToken token = (PostAuthorizationToken) authentication;
        AccountContext context = (AccountContext)token.getPrincipal();

        String tokenString = jwtFactory.generateToken(context);

        processResponse(response, writeDto(tokenString));
    }

    private TokenDto writeDto(String token) {
        return new TokenDto(token);
    }

    private void processResponse(HttpServletResponse res, TokenDto dto) throws IOException {
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        res.setStatus(HttpStatus.OK.value());
        res.getWriter().write(objectMapper.writeValueAsString(dto));
    }
}
