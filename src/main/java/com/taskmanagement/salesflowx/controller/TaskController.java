package com.taskmanagement.salesflowx.controller;


import com.taskmanagement.salesflowx.entity.Task;
import com.taskmanagement.salesflowx.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/tasks")
    public ResponseEntity<Task> newTask(@RequestBody Task task) {
        Task createTask = taskService.createTask(task);
        return new ResponseEntity<>(createTask, HttpStatus.CREATED);
    }


    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);

    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable String id){
        Task task = taskService.getTaskById(id).orElseThrow(null);
        return new ResponseEntity<>(task, HttpStatus.FOUND);
    }


    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable String id, @RequestBody Task task){
        Task updateTask  = taskService.updateTaskById(id, task);
        return new ResponseEntity<>(updateTask, HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteTaskById(@PathVariable String id){
        taskService.deleteTaskById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
