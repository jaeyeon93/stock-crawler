package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger =  LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User find(Long id) {
        return userRepository.findOne(id);
    }
}
