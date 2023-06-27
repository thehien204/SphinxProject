package com.sphinx.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class ManagementToolApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementToolApplication.class, args);
	}

}
