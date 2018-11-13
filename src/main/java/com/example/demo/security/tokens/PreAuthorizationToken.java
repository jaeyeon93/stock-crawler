package com.example.demo.security.tokens;

import com.example.demo.dto.FormLoginDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    private PreAuthorizationToken(String username, String password) {
        super(username, password);
    }

    public PreAuthorizationToken(FormLoginDto dto) {
        this(dto.getId(), dto.getPassword());
    }

    public String getUsernmae() {
        return (String)super.getPrincipal();
    }

    public String getUserPassword() {
        return (String)super.getCredentials();
    }
}
