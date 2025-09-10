package com.taskmanagement.salesflowx.repository;

import com.taskmanagement.salesflowx.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuthRepository extends MongoRepository<User, String> {
}
