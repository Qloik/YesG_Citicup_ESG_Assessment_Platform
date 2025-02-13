package com.citycup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.citycup.mapper")
public class CityCupApplication {

	public static void main(String[] args) {
		SpringApplication.run(CityCupApplication.class, args);
	}

}
