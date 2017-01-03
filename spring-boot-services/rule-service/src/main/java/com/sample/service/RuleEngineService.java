package com.sample.service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.drools.command.CommandFactory;
import org.drools.examples.decisiontable.Driver;
import org.drools.examples.decisiontable.Policy;
import org.drools.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RuleEngineService {

	protected static Logger log = Logger.getLogger(RuleEngineService.class.getName());
	
	
	private final KieContainer kieContainer;
	
	
	@Autowired
	public RuleEngineService(KieContainer kieContainer){
		
		log.info("****************initializing rule engine kiecontainer");
		this.kieContainer = kieContainer;
		
	}
	
	/*
	 * Create a session with Policy and Driver facts and fire the rules
	 */
	
	
	
    public int getBasePrice(Policy policy, Driver driver){
    	
    	
    	StatelessKieSession kieSession = kieContainer.newStatelessKieSession("DecisionTableKS");
     	List<Object> facts = new ArrayList<Object>();
    	facts.add(driver);
    	facts.add(policy);
    	kieSession.execute(facts);
    	
    	return policy.getBasePrice();
    }
    
	
}
