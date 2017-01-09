package com.sample.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.KieRepository;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.io.ResourceFactory;
import org.kie.spring.KModuleBeanFactoryPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.kie.api.builder.Message.Level;
import org.kie.api.builder.Message;

import com.sample.config.property.RulesProperties;



@Configuration
public class RuleEngineConfig {

	@Autowired
	private RulesProperties rulesProperties;
   
	
    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
             return resourcePatternResolver.getResources("classpath*:" + rulesProperties.getResourcePATH().trim() + "**/*.*");
    }
    
    @Bean
    @ConditionalOnMissingBean(KieContainer.class)
    public KieContainer kieContainer() throws Exception {
    	final KieServices kieServices = KieServices.Factory.get();
        final KieRepository kieRepository = kieServices.getRepository();
        
        kieRepository.addKieModule(new KieModule() {
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
        
        
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(rulesProperties.getResourcePATH().trim() + file.getFilename(), "UTF-8"));
        }   
        
        
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem).buildAll(); 
        
        if (kieBuilder.getResults().hasMessages(Level.ERROR)) {
            List<Message> errors = kieBuilder.getResults().getMessages(Level.ERROR);
            StringBuilder sb = new StringBuilder("Errors:");
            for (Message msg : errors) {
                sb.append("\n  " + printBuildMessage(msg));
            }
            throw new Exception(sb.toString());
}
        
        return kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
    }
    
   
	    private static String printBuildMessage(Message msg) {
	        return "Message: {"
	            + "id="+ msg.getId()
	            + ", level=" + msg.getLevel()
	            + ", path=" + msg.getPath()
	            + ", line=" + msg.getLine()
	            + ", column=" + msg.getColumn()
	            + ", text=\"" + msg.getText() + "\""
	+ "}";
    
	    } 
}
