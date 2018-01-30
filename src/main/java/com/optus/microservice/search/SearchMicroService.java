package com.optus.microservice.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan({"com.optus.microservice.search"}) // redundant as the controller is in the same package. already covered by above @ 
public class SearchMicroService {

	public static void main(String[] args) {
		SpringApplication.run(SearchMicroService.class, args);
	}
}