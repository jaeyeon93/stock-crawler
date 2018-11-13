package com.example.demo.security;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import com.example.demo.social.FrontUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByEmail(username);

        if(user == null) {
            throw new UsernameNotFoundException("Not Exist User");
        }

        FrontUserDetail frontUserDetail = new FrontUserDetail(user);
        return frontUserDetail;
    }
}
