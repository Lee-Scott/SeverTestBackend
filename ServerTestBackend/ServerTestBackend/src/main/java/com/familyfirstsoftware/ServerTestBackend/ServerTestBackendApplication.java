package com.familyfirstsoftware.ServerTestBackend;


import com.familyfirstsoftware.ServerTestBackend.domainOrModel.Server;
import com.familyfirstsoftware.ServerTestBackend.repo.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import static com.familyfirstsoftware.ServerTestBackend.enums.Status.*;

@SpringBootApplication
public class ServerTestBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerTestBackendApplication.class, args);
	}

	/*
		set up some servers for testing
	 */

	@Bean
	CommandLineRunner run(ServerRepo serverRepo){
		return args -> {
			serverRepo.save(new Server(
					null,
					"192.169.169",
					"Mac",
					"16 GB",
					"Personal PC",
					"http:localhost/8080/server/image/server1.png",
					SERVER_UP));

			serverRepo.save(new Server(
					null,
					"192.111.161",
					"Windows",
					"64 GB",
					"Personal PC",
					"http:localhost/8080/server/image/server2.png",
					SERVER_UP));

			serverRepo.save(new Server(
					null,
					"192.333.444",
					"Web Server",
					"32 GB",
					"Dell Tower",
					"http:localhost/8080/server/image/server3.png",
					SERVER_UP));

			serverRepo.save(new Server(
					null,
					"192.444.445",
					"Red Hat",
					"128 GB",
					"Mail Server",
					"http:localhost/8080/server/image/server4.png",
					SERVER_UP));
		};
	}
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200")); // reactJS and angular
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
