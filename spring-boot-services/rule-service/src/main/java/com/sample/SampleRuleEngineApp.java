

package com.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SampleRuleEngineApp {

	public static void main(String[] args) {
		// Look for rule-server.yml or rule-server.properties
		//System.setProperty("spring.config.name","rule-server");
		SpringApplication.run(SampleRuleEngineApp.class, args);
	}
}
