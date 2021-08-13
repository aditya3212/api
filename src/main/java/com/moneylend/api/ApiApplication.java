package com.moneylend.api;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RestController
@SpringBootApplication
public class ApiApplication {
	
	@Autowired
    private Environment environment;
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				String origins = environment.getProperty("origins");
//				logger.info(origins);
//				registry.addMapping("/**").allowedOrigins("http://d2wibqnk5fd2ft.cloudfront.net", "http://d1o6e0ryot8avi.cloudfront.net", "http://sandbox.spacebasic.com", "https://sandbox.spacebasic.com", "http://spacebasic.com", "https://spacebasic.com", "http://www.spacebasic.com", "https://www.spacebasic.com", "https://web.spacebasic.com", "http://dev.spacebasic.com");
				registry.addMapping("/**").allowedOrigins("*");
			}
		};
		
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
