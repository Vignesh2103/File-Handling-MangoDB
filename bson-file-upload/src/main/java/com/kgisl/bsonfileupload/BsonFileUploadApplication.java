package com.kgisl.bsonfileupload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.kgisl.bsonfileupload"})
public class BsonFileUploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(BsonFileUploadApplication.class, args);
	}

}
