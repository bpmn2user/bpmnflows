package com.sample.config.property;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="rules.url")
public class RulesProperties {

	@NotNull
	private String rulesURL;

	public String getRulesURL() {
		return rulesURL;
	}

	public void setRulesURL(String rulesURL) {
		this.rulesURL = rulesURL;
	}
}
