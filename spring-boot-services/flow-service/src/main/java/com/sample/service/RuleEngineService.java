package com.sample.service;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.drools.examples.decisiontable.Driver;
import org.drools.examples.decisiontable.Policy;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class RuleEngineService {

	protected static Logger log = Logger.getLogger(RuleEngineService.class.getName());
	
	private final KieContainer kieContainer;

    @Autowired
    public RuleEngineService(KieContainer kieContainer) {
        log.info("Initialising a new session.");
        this.kieContainer = kieContainer;
    }

	
	/*
	 * Create a session with Policy and Driver facts and fire the rules
	 */
	
	
	
    public void getProcessflow(){
    	
    	
    	
    	KieSession ksession = kieContainer.newKieSession();
		
	

		// set the parameters
		Map<String, Object> params = new HashMap<String, Object>();
		HelloProcessModel hpm = new HelloProcessModel();
		hpm.setCount(new Integer("3"));
		hpm.setUserlocation("NewYorkUser");
		params.put("hpm", hpm);
		ksession.startProcess("looptest777",params);
	
		ksession.fireAllRules();
    	
    }
    
	
}
