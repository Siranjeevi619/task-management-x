package com.taskmanagement.salesflowx.entity;


import com.taskmanagement.salesflowx.utils.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "Task")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    private String id;
    @NotBlank
    private String title;

    private String description;
    @NotBlank
    private Status status;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;


}
