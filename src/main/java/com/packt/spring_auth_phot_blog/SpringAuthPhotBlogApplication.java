package com.packt.spring_auth_phot_blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;//for file upload
import org.springframework.boot.context.properties.EnableConfigurationProperties;//for file upload
import org.springframework.context.annotation.Bean;//for file upload

import com.packt.spring_auth_phot_blog.storage.StorageProperties;//for file upload
import com.packt.spring_auth_phot_blog.storage.StorageService;//for file upload

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)//for file upload
public class SpringAuthPhotBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAuthPhotBlogApplication.class, args);
	}

	//for file upload
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			//storageService.deleteAll();
			storageService.init();
		};
	}

}
