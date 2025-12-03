package com.ktdsuniversity.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BrichApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrichApplication.class, args);
	}

}
