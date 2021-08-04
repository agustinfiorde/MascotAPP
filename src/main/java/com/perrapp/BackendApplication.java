package com.perrapp;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

import com.perrapp.servicios.RolService;

import lombok.extern.slf4j.Slf4j;

@PropertySource(value = "classpath:secrets.yml", factory = YamlPropertySourceFactory.class)
@Slf4j
@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		
//		SpringApplication.run(BackendApplication.class, args);
		log.info("App started at "+ new Date());
		
		ConfigurableApplicationContext app = SpringApplication.run(BackendApplication.class, args);
		RolService rolService = (RolService) app.getBean("RolService");
		rolService.generarRoles();
	}

}
