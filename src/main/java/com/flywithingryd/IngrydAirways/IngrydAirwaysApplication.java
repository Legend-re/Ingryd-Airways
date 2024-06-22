package com.flywithingryd.IngrydAirways;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class IngrydAirwaysApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngrydAirwaysApplication.class, args);
	}

}
