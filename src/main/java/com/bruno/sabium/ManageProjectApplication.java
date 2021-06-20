package com.bruno.sabium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ManageProjectApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(ManageProjectApplication.class, args);
		
	}
}
