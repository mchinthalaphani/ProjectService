package com.project.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@ComponentScan({"com.resourcemanagement.domain","com.model","com.resourcemanagement.repository","com.project.application"})
@EnableWebMvc
@EnableJpaRepositories("com.resourcemanagement.repository")
@EntityScan("com.resourcemanagement.domain")
public class ProjectApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);

	}
}
