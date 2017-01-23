package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {
	
	@Bean
	public FilterRegistrationBean filtroJwt() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new TokenFilter());
		bean.addUrlPatterns("/admin/*");
		
		return bean;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
