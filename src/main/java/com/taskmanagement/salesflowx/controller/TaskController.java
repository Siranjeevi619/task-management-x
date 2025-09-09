package com.taskmanagement.salesflowx.controller;

import com.taskmanagement.salesflowx.dto.TaskDTO;
import com.taskmanagement.salesflowx.entity.Task;
import com.taskmanagement.salesflowx.service.TaskService;
import com.taskmanagement.salesflowx.utils.ApiResponse;
import com.taskmanagement.salesflowx.utils.ApiStatus;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<ApiResponse> newTask(@RequestBody TaskDTO task, HttpServletRequest request) {
        try {
            ApiResponse apiResponse = new ApiResponse();

            if (task.getTitle() == null || task.getTitle().trim().equals("")) {
                apiResponse.setMessage("Task title is required");
                apiResponse.setStatus(ApiStatus.REJECTED);
                apiResponse.setApi(request.getRequestURI());
                return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
            }

            if (task.getDescription() == null || task.getDescription().trim().equals("")) {
                apiResponse.setMessage("Task description is required");
                apiResponse.setStatus(ApiStatus.REJECTED);
                apiResponse.setApi(request.getRequestURI());
                return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
            }

            if (task.getStatus() == null) {
                apiResponse.setMessage("Task status is required");
                apiResponse.setStatus(ApiStatus.REJECTED);
                apiResponse.setApi(request.getRequestURI());
                return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
            }

            Task createTask = taskService.createTask(task);
            apiResponse.setMessage("New Task created");
            apiResponse.setData(createTask);
            apiResponse.setApi(request.getRequestURI());
            apiResponse.setStatus(ApiStatus.SUCCESS);
            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse(request.getRequestURI(), ApiStatus.REJECTED,
                            "INTERNAL SERVER ERROR", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/tasks")
    public ResponseEntity<ApiResponse> getAllTasks(HttpServletRequest request) {
        try {
            ApiResponse apiResponse = new ApiResponse();
            List<Task> tasks = taskService.getAllTasks();

            if (tasks.isEmpty()) {
                apiResponse.setMessage("No tasks found");
                apiResponse.setStatus(ApiStatus.REJECTED);
                apiResponse.setApi(request.getRequestURI());
                return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
            }

            apiResponse.setData(tasks);
            apiResponse.setApi(request.getRequestURI());
            apiResponse.setStatus(ApiStatus.SUCCESS);
            apiResponse.setMessage("Tasks fetched successfully");
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse(request.getRequestURI(), ApiStatus.REJECTED,
                            "INTERNAL SERVER ERROR", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<ApiResponse> getTaskById(@PathVariable String id, HttpServletRequest request) {
        try {
            Task task = taskService.getTaskById(id);

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("Task found");
            apiResponse.setData(task);
            apiResponse.setApi(request.getRequestURI());
            apiResponse.setStatus(ApiStatus.SUCCESS);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse(request.getRequestURI(), ApiStatus.REJECTED,
                            "Task not found", e.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<ApiResponse> updateTask(@PathVariable String id,
                                                  @RequestBody TaskDTO task,
                                                  HttpServletRequest request) {
        try {
            Task updateTask = taskService.updateTaskById(id, task);
            if (updateTask == null) {
                ApiResponse apiResponse = new ApiResponse();
                apiResponse.setMessage("Task not found with id: " + id);
                apiResponse.setApi(request.getRequestURI());
                apiResponse.setStatus(ApiStatus.REJECTED);
                return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage("Task updated successfully");
            apiResponse.setData(updateTask);
            apiResponse.setApi(request.getRequestURI());
            apiResponse.setStatus(ApiStatus.SUCCESS);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse(request.getRequestURI(), ApiStatus.REJECTED,
                            "Update failed", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<ApiResponse> deleteTask(@PathVariable String id, HttpServletRequest request) {
        try {
            boolean isDeleted = taskService.deleteTaskById(id);

            ApiResponse apiResponse = new ApiResponse();
            if (!isDeleted) {
                apiResponse.setMessage("Task not found with id: " + id);
                apiResponse.setApi(request.getRequestURI());
                apiResponse.setStatus(ApiStatus.REJECTED);
                return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }

            apiResponse.setMessage("Task deleted successfully");
            apiResponse.setApi(request.getRequestURI());
            apiResponse.setStatus(ApiStatus.SUCCESS);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ApiResponse(request.getRequestURI(), ApiStatus.REJECTED,
                            "Delete failed", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


}
