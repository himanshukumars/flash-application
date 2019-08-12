package com.flash.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
@EntityScan(basePackages = {"com.flash.app.entity"})  
public class FlashAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlashAppApplication.class, args);
	}

}
