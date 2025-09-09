package com.taskmanagement.salesflowx.exception;


import com.taskmanagement.salesflowx.utils.ApiResponse;
import com.taskmanagement.salesflowx.utils.ApiStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiResponse> handleException(TaskNotFoundException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(exception.getMessage());
        apiResponse.setData(exception);
        apiResponse.setStatus(ApiStatus.REJECTED);
        apiResponse.setApi("/api/tasks");
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleException(Exception exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(exception.getMessage());
        apiResponse.setData(exception);
        apiResponse.setStatus(ApiStatus.REJECTED);
        apiResponse.setApi("/api/tasks");
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
