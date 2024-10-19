package com.livescore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class LiveScoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiveScoreApplication.class, args);
	}

}
