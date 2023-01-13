package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@RestController
public class BookManagementAppApplication {
	

	/*@RequestMapping("/getMsg")
	public String index()
	{
		return "Hello fellow Cognizant employees";
	}*/

	public static void main(String[] args) {
		SpringApplication.run(BookManagementAppApplication.class, args);
	}

}
