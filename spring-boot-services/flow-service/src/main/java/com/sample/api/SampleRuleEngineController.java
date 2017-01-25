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
	
	@RequestMapping(value = "/startflow", method=RequestMethod.GET, produces = "application/json")
	public String getProcessflow () {
		
		ruleEngineService.getProcessflow();
		return "completed successfully" ;
	}
			                    
}
