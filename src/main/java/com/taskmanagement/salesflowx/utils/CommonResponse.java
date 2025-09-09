package com.taskmanagement.salesflowx.utils;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonResponse<T> {
    private String api;
    private ApiStatus status;
    private String message;
    private T data;
}
