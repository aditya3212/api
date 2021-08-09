package com.moneylend.api;

import java.util.HashMap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class ApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
	
	@GetMapping("/hello")
	public String fun() {
		return "Hello Aditya How are you";
	}
	
	@GetMapping("/details")
	public Object details() {
		HashMap hm=new HashMap<String,String>();
		hm.put("name", "aditya");
		hm.put("age","18");
		hm.put("father name", "Tata");
		hm.put("Mother Name", "Anita");
		return hm;
		
	}
}
