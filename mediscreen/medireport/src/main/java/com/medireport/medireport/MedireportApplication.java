package com.medireport.medireport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients("com.medireport")
@SpringBootApplication
public class MedireportApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedireportApplication.class, args);
    }

}
