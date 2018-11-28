package com.example.demo.security;

import com.example.demo.domain.Account;
import com.example.demo.domain.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class AccountContextService implements UserDetailsService {
    // 자격정보오 사용자 정보를 DB에서 가져오는 인터페이스
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Account account = accountRepository.findByUserId(userId).orElseThrow(() -> new NoSuchElementException("아이디에 맞는 계정이 없습니다."));
        return getAccountContext(account);
    }

    private AccountContext getAccountContext(Account account) {
        return AccountContext.fromAccountModel(account);
    }
}
