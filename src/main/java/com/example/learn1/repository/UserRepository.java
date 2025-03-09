package com.example.learn1.repository;


import com.example.learn1.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
    Optional<User> findByEmpNumber(String empNumber);
    void deleteByEmpNumber(String empNumber);
}
