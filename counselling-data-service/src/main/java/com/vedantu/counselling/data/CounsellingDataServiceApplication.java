package com.vedantu.counselling.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CounsellingDataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CounsellingDataServiceApplication.class, args);
	}

}
