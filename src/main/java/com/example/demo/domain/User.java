package com.example.demo.domain;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class User implements Serializable {
    private static final Logger logger =  LoggerFactory.getLogger(User.class);
    
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String username;
    private String remark;

    public User() {}

    public User(String name, String username, String remark) {
        this.name = name;
        this.username = username;
        this.remark = remark;
    }
}
