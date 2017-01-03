package com.sample.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Sample Config server.
 * 
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServer {

	
	public static void main(String[] args) {
		SpringApplication.run(ConfigServer.class, args);
	}
}
