package com.taskmanagement.salesflowx.repository;

import com.taskmanagement.salesflowx.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;


@Repository
public interface AuthRepository extends MongoRepository<User, String> {
    boolean existsUserByEmail(String email);

    User findUserByEmail(String email);

    User findByEmail(String email);
}
