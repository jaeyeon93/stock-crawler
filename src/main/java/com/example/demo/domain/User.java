package com.example.demo.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@DynamicInsert
@DynamicUpdate
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", length = 100)
    private String lastName;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    public static User fromVO(UserCreateRequestVO userCreateRequestVO) {
        User user = new User();
        user.setLastName(userCreateRequestVO.getLastName());
        user.setFirstName(userCreateRequestVO.getFirstName());
        user.setEmail(userCreateRequestVO.getEmail());
        return user;
    }
}
