package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.domain.UserCreateRequestVO;
import com.example.demo.domain.UserRepository;
import com.example.demo.support.domain.DuplicateEmailException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private static final Logger logger =  LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User create(UserCreateRequestVO userCreateRequestVO) throws DuplicateEmailException {
        User user = User.fromVO(userCreateRequestVO);

        if (existUser(user.getEmail()))
            throw new DuplicateEmailException("Already registered email address");
        user.setPassword(passwordEncoder.encode(userCreateRequestVO.getPassword()));
        userRepository.save(user);
        return user;
    }

    public boolean existUser(String email) {
        User user = findByEmail(email);

        return user != null ? true : false;
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
