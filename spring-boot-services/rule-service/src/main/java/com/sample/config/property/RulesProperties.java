package com.sample.config.property;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="rules.dir")
public class RulesProperties {

	@NotNull
	private String resourcePATH;

	public String getResourcePATH() {
		return resourcePATH;
	}

	public void setResourcePATH(String resourcePATH) {
		this.resourcePATH = resourcePATH;
	}
}