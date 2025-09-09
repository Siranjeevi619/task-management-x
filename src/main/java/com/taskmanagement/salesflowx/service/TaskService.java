package com.taskmanagement.salesflowx.service;


import com.taskmanagement.salesflowx.dto.TaskDTO;
import com.taskmanagement.salesflowx.entity.Task;
import com.taskmanagement.salesflowx.exception.TaskNotFoundException;
import com.taskmanagement.salesflowx.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(TaskDTO task){
        Task newTask = new Task();
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setStatus(task.getStatus());
        return taskRepository.save(newTask);
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById(String id){
        if(id == null){
            return null;
        }
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task Not Found With "+id));
    }

    public Task updateTaskById(String id, TaskDTO task){
        if(id == null){
            return null;
        }
        Task found = taskRepository.findById(id).orElseThrow(() -> new TaskNotFoundException("Task Not Found With "+id));
        if(found == null){
            return null;
        }
        found.setTitle(task.getTitle());
        found.setDescription(task.getDescription());
        found.setStatus(task.getStatus());
        return taskRepository.save(found);

    }



    public boolean deleteTaskById(String id) {
        if (!taskRepository.existsById(id)) {
           throw new  TaskNotFoundException("Task Not Found With "+id);
        }
        taskRepository.deleteById(id);
        return true;
    }


    public Page<Task> getTasks(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return taskRepository.findAll(pageable);
    }



}
