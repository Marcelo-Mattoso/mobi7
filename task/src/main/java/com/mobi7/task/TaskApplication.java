package com.mobi7.task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.mobi7"} )
@EntityScan("com.mobi7.model")
@EnableJpaRepositories("com.mobi7.repository")

public class TaskApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
	}

}
