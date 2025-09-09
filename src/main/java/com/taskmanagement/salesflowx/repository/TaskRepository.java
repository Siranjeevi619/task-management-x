package com.taskmanagement.salesflowx.Repository;

import com.taskmanagement.salesflowx.entity.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends MongoRepository<Task,String> {

}
