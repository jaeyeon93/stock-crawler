package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "user") // "/users" url => /user
public interface UserRepository extends JpaRepository<User, Long> {

}
