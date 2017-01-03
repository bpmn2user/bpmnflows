package com.sample.api;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.drools.examples.decisiontable.Driver;
import org.drools.examples.decisiontable.Policy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.service.RuleEngineService;

@RestController
public class SampleRuleEngineController {
	
	protected static Logger log = Logger.getLogger(SampleRuleEngineController.class.getName());
	
	
	private final RuleEngineService ruleEngineService;
	

	@Autowired
	public SampleRuleEngineController(RuleEngineService ruleEngineService) {
		this.ruleEngineService = ruleEngineService;
	}
	
	
	/*
	 * Get baseprice using driver and policy information
	 */
	
	@RequestMapping(value = "/baseprice", method=RequestMethod.GET, produces = "application/json")
	public Policy getBasePrice (@RequestParam(value="age", defaultValue="20", required = true) int age,
			                    @RequestParam(value="locationRiskProfile", defaultValue="LOW",required = true) String locationRiskProfile,
			                    @RequestParam(value="priorClaims", defaultValue="1",required = false) int priorClaims,
			                    @RequestParam(value="policyType", defaultValue="COMPREHENSIVE",required = true) String policyType) {
		
		Policy policy = new Policy();
		Driver driver = new Driver();
		
		driver.setAge(new Integer(age));
		driver.setLocationRiskProfile(locationRiskProfile);
		driver.setPriorClaims(new Integer(priorClaims));
		policy.setType(policyType);
		policy.setBasePrice(ruleEngineService.getBasePrice(policy, driver));
		return policy ;
	}
			                    
}
