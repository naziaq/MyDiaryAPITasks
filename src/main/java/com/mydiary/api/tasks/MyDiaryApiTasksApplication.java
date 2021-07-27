package com.mydiary.api.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MyDiaryApiTasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyDiaryApiTasksApplication.class, args);
	}

}
