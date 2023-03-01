package com.basket.statistics;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class StatisticsApplication {

	@Value("${frontapp.url}")
	private String frontBaseUrl;

	public static void main(String[] args) {
		SpringApplication.run(StatisticsApplication.class, args);
	}
	@Bean
	public WebMvcConfigurer myMvcConfigurer() {
		return new WebMvcConfigurer() {




			// CROS ORIGIN
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				String[] allowedUrls = frontBaseUrl.split(";");
				System.out.println(allowedUrls);
				registry.addMapping("/**")
						.allowedOrigins("*") //en dév
						//.allowedOrigins(allowedUrls) //en prod
						.allowedMethods("GET","POST","PUT","DELETE","PATCH","OPTIONS");
			}



		};
	}

}
