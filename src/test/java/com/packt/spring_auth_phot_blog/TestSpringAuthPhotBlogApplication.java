package com.packt.spring_auth_phot_blog;

import org.springframework.boot.SpringApplication;

public class TestSpringAuthPhotBlogApplication {

	public static void main(String[] args) {
		SpringApplication.from(SpringAuthPhotBlogApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
