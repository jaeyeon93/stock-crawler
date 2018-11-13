package com.example.demo.security.filter;

import com.example.demo.dto.FormLoginDto;
import com.example.demo.security.tokens.PreAuthorizationToken;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormLoginFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private AuthenticationFailureHandler authenticationFailureHandler;

    public FormLoginFilter(String defaultUrl, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        super(defaultUrl);
        this.authenticationSuccessHandler = successHandler;
        this.authenticationFailureHandler = failureHandler;
    }

    protected FormLoginFilter(String defaultFilterProcessUrl) {
        super(defaultFilterProcessUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        // 처음에 인증을 시도하는 메소드
        FormLoginDto dto = new ObjectMapper().readValue(request.getReader(),FormLoginDto.class);
        PreAuthorizationToken token = new PreAuthorizationToken(dto);
        return super.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        this.authenticationSuccessHandler.onAuthenticationSuccess(request, response, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        AuthenticationFailureHandler handler = (req, res, exception) -> logger.error(exception.getMessage());
        handler.onAuthenticationFailure(request, response, failed);
    }
}
