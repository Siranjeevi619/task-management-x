package com.taskmanagement.salesflowx.controller;

import com.taskmanagement.salesflowx.dto.TaskDTO;
import com.taskmanagement.salesflowx.entity.Task;
import com.taskmanagement.salesflowx.service.TaskService;
import com.taskmanagement.salesflowx.utils.ApiStatus;
import com.taskmanagement.salesflowx.utils.CommonResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

//    @Operation(summary = "Create a new task")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Task created successfully"),
//            @ApiResponse(responseCode = "400", description = "Invalid input")
//    })
    @PostMapping("/tasks")
    public ResponseEntity<CommonResponse> newTask(@RequestBody TaskDTO task, HttpServletRequest request) {
        try {
            CommonResponse apiResponse = new CommonResponse();

            if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
                apiResponse.setMessage("Task title is required");
                apiResponse.setStatus(ApiStatus.REJECTED);
                apiResponse.setApi(request.getRequestURI());
                return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
            }

            if (task.getDescription() == null || task.getDescription().trim().isEmpty()) {
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
                    new CommonResponse(request.getRequestURI(), ApiStatus.REJECTED,
                            "INTERNAL SERVER ERROR", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

//    @Operation(summary = "Get all tasks")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Tasks fetched successfully"),
//            @ApiResponse(responseCode = "204", description = "No tasks found")
//    })
    @GetMapping("/tasks")
    public ResponseEntity<CommonResponse> getAllTasks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "100") int size,
            HttpServletRequest request) {
        try {
            CommonResponse apiResponse = new CommonResponse();
            Page<Task> taskPage = taskService.getTasks(page, size);

            if (taskPage.isEmpty()) {
                apiResponse.setMessage("No tasks found");
                apiResponse.setStatus(ApiStatus.REJECTED);
                apiResponse.setApi(request.getRequestURI());
                return new ResponseEntity<>(apiResponse, HttpStatus.NO_CONTENT);
            }

            apiResponse.setData(taskPage.getContent());
            apiResponse.setMessage("Tasks fetched successfully");
            apiResponse.setStatus(ApiStatus.SUCCESS);
            apiResponse.setApi(request.getRequestURI());
            apiResponse.setMessage("Tasks fetched successfully. Page: " + page + " / Total Pages: " + taskPage.getTotalPages());

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new CommonResponse(request.getRequestURI(), ApiStatus.REJECTED,
                            "INTERNAL SERVER ERROR", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
//    @Operation(summary = "Get a task by ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Task found"),
//            @ApiResponse(responseCode = "404", description = "Task not found")
//    })
    @GetMapping("/tasks/{id}")
    public ResponseEntity<CommonResponse> getTaskById(@PathVariable String id, HttpServletRequest request) {
        try {
            Task task = taskService.getTaskById(id);

            CommonResponse apiResponse = new CommonResponse();
            apiResponse.setMessage("Task found");
            apiResponse.setData(task);
            apiResponse.setApi(request.getRequestURI());
            apiResponse.setStatus(ApiStatus.SUCCESS);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new CommonResponse(request.getRequestURI(), ApiStatus.REJECTED,
                            "Task not found", e.getMessage()),
                    HttpStatus.NOT_FOUND
            );
        }
    }

//    @Operation(summary = "Update a task by ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
//            @ApiResponse(responseCode = "404", description = "Task not found"),
//            @ApiResponse(responseCode = "500", description = "Update failed")
//    })
    @PutMapping("/tasks/{id}")
    public ResponseEntity<CommonResponse> updateTask(@PathVariable String id,
                                                  @RequestBody TaskDTO task,
                                                  HttpServletRequest request) {
        try {
            Task updateTask = taskService.updateTaskById(id, task);
            if (updateTask == null) {
                CommonResponse apiResponse = new CommonResponse();
                apiResponse.setMessage("Task not found with id: " + id);
                apiResponse.setApi(request.getRequestURI());
                apiResponse.setStatus(ApiStatus.REJECTED);
                return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
            }

            CommonResponse apiResponse = new CommonResponse();
            apiResponse.setMessage("Task updated successfully");
            apiResponse.setData(updateTask);
            apiResponse.setApi(request.getRequestURI());
            apiResponse.setStatus(ApiStatus.SUCCESS);
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(
                    new CommonResponse(request.getRequestURI(), ApiStatus.REJECTED,
                            "Update failed", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

//    @Operation(summary = "Delete a task by ID")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "Task deleted successfully"),
//            @ApiResponse(responseCode = "404", description = "Task not found"),
//            @ApiResponse(responseCode = "500", description = "Delete failed")
//    })
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<CommonResponse> deleteTask(@PathVariable String id, HttpServletRequest request) {
        try {
            boolean isDeleted = taskService.deleteTaskById(id);

            CommonResponse apiResponse = new CommonResponse();
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
                    new CommonResponse(request.getRequestURI(), ApiStatus.REJECTED,
                            "Delete failed", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
