package com.morena.citTestBack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class CitTestBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitTestBackApplication.class, args);
	}

	@Bean
	MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofKilobytes(128L));
		factory.setMaxRequestSize(DataSize.ofKilobytes(128L));
		return factory.createMultipartConfig();
	}

}
