package com.core.kettle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "com.core")
@ComponentScan(basePackages = "com.core")
public class KettleApplication {

	public static void main(String[] args) {

		SpringApplication.run(KettleApplication.class, args);
	}

}
