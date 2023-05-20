package com.example.Unityville;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class UnityvilleApplication {

	public static void main(String[] args) {
		SpringApplication.run(UnityvilleApplication.class, args);
	}

}
