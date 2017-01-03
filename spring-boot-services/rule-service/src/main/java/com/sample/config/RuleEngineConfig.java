package com.sample.config;

import java.io.IOException;
import java.io.InputStream;

import org.drools.core.io.impl.UrlResource;
import org.drools.examples.decisiontable.Driver;
import org.drools.examples.decisiontable.Policy;
import org.kie.api.KieServices;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.KieResources;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sample.config.property.RulesProperties;



@Configuration
public class RuleEngineConfig {

	@Autowired
	private RulesProperties rulesProperties;
	
	 @Bean
	public KieContainer kieContainer() throws IOException {
		 
		 KieServices ks = KieServices.Factory.get();
		 KieResources resources = ks.getResources();
		 
		 UrlResource urlResource = (UrlResource)resources.newUrlResource(rulesProperties.getRulesURL());
		 InputStream stream = urlResource.getInputStream();
		 
		 KieRepository repo = ks.getRepository();
		 KieModule km = repo.addKieModule(resources.newInputStreamResource(stream));
		 
        return ks.newKieContainer(km.getReleaseId());
	 } 
		 
}
