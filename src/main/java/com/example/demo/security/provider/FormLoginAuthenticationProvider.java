package com.example.demo.security.provider;

import com.example.demo.domain.Account;
import com.example.demo.domain.AccountRepository;
import com.example.demo.security.AccountContext;
import com.example.demo.security.AccountContextService;
import com.example.demo.security.tokens.PostAuthorizationToken;
import com.example.demo.security.tokens.PreAuthorizationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;

public class FormLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private AccountContextService accountContextService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PreAuthorizationToken token = (PreAuthorizationToken)authentication;
        String username = token.getUsernmae();
        String password = token.getUserPassword();

        Account account = accountRepository.findByUserId(username).orElseThrow(() -> new NoSuchElementException("정보에 맞는 계정이 없습니다."));

        if (isCorrectPassword(password, account)) {
            //Todo return post authentication token.
            return PostAuthorizationToken.getTokenFromAccountContext(AccountContext.fromAccountModel(account));
        }

        // 여기까지 잘안되면 인증이 잘못된거다.
        throw new NoSuchElementException("인증 정보가 정확하지 않습니다.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // PreAuthorizationToken으로 들어오는 요청은 다 이 필터에 걸리게 된다.
        return PreAuthorizationToken.class.isAssignableFrom(authentication);
    }

    private boolean isCorrectPassword(String password, Account account) {
        // 순서중요. 원본이 앞에 와야한다.
        return passwordEncoder.matches(account.getPassword(), password);
    }
}
