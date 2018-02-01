package com.emc.ps.appmod.adaa.iph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class IphServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IphServiceApplication.class, args);
	}
	
}
