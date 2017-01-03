package io.pivotal.microservices.services.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Sample Eureka server.
 * 
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer {

	
	public static void main(String[] args) {
		// Tell server to look for eureka.properties or eureka.yml
		System.setProperty("spring.config.name", "eureka-server");

		SpringApplication.run(EurekaServer.class, args);
	}
}
