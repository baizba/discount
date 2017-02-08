package com.xteam.discount;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@EnableCaching
public class DiscountApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscountApplication.class, args);
	}
}
