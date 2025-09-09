package com.taskmanagement.salesflowx.service;


import com.taskmanagement.salesflowx.entity.Task;
import com.taskmanagement.salesflowx.repository.TaskRepository;
import com.taskmanagement.salesflowx.utils.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task){
        if(task.getStatus() == null){
            task.setStatus(Status.PENDING);
        }
        task.setCreatedAt(new Date());
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(String id){
        if(id == null){
            return null;
        }
        return taskRepository.findById(id);
    }

    public Task updateTaskById(String id, Task task){
        if(id == null){
            return null;
        }
        Task found = taskRepository.findById(id).orElse(null);
        if(found == null){
            return null;
        }
        found.setTitle(task.getTitle());
        found.setDescription(task.getDescription());
        found.setStatus(task.getStatus());
        return taskRepository.save(found);

    }

    public void deleteTaskById(String id){
        if(id == null){
            return;
        }
        taskRepository.deleteById(id);


    }


}
