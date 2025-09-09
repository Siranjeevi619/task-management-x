package com.taskmanagement.salesflowx.exception;


import com.taskmanagement.salesflowx.utils.ApiStatus;
import com.taskmanagement.salesflowx.utils.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<CommonResponse> handleException(TaskNotFoundException exception) {
        CommonResponse apiResponse = new CommonResponse();
        apiResponse.setMessage(exception.getMessage());
        apiResponse.setData(exception);
        apiResponse.setStatus(ApiStatus.REJECTED);
        apiResponse.setApi("/api/tasks");
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> handleException(Exception exception) {
        CommonResponse apiResponse = new CommonResponse();
        apiResponse.setMessage(exception.getMessage());
        apiResponse.setData(exception);
        apiResponse.setStatus(ApiStatus.REJECTED);
        apiResponse.setApi("/api/tasks");
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
