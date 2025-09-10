package com.taskmanagement.salesflowx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class SalesflowxApplication {
	public static void main(String[] args) {
		SpringApplication.run(SalesflowxApplication.class, args);
	}

}
