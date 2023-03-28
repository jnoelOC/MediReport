package com.medireportui.medireportui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.medireportui")
@SpringBootApplication
public class MedireportuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedireportuiApplication.class, args);
	}

}
