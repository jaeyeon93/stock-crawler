package com.example.demo.security;

import com.example.demo.domain.Account;
import com.example.demo.domain.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AccountContext extends User {
    // AccountUserDetails로서 implements UserDetails를 하거나 UserDetails의 구현체인 User를 상속을 받은 클래스
    // 인증처리에 필요한 자격정보와 사용자 상태정보를 제공하는 UserDetails인터페이스 구현체
    private Account account;

    public AccountContext(Account account, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.account = account;
    }

    public static AccountContext fromAccountModel(Account account) {
        return new AccountContext(account, account.getUserId(), account.getPassword(), parseAuthorities(account.getUserRole()));
    }

    private static List<SimpleGrantedAuthority> parseAuthorities(UserRole role) {
        return Arrays.asList(role).stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

    public Account getAccount() {
        return account;
    }
}
