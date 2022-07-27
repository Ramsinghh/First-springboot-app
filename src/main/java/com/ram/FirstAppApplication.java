package com.ram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@RestController
public class FirstAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstAppApplication.class, args);
	}

	@GetMapping("/hello")
	public String fun() {
		return "hello ramsingh";
	}

	@GetMapping("/date")
	public String fun2()
	{
		return new Date().toString();
	}
//GET, POST, PUT, DELETE
	// CRUD- Create,Retrieve, Update, Delete
}
