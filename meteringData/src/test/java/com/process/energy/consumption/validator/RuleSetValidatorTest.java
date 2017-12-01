package com.process.energy.consumption.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidatorContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.process.energy.consumption.vo.request.CreateOrUpdateRuleSetRequest;
import com.process.energy.consumption.vo.request.RuleSetData;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class RuleSetValidatorTest {
	
	
	@InjectMocks
	RuleSetValidator ruleSetValidator;
	
	@Test
	public void validateRuleSet() {
		
		
		CreateOrUpdateRuleSetRequest createOrUpdateRuleSetRequest = new CreateOrUpdateRuleSetRequest();
		
		List<RuleSetData> listOfRuleSet = new ArrayList<RuleSetData>();
		RuleSetData ruleSet = new RuleSetData();
		ruleSet.setFraction(0.02);
		ruleSet.setMonth("JAN");
		ruleSet.setProfile("A");
		listOfRuleSet.add(ruleSet);
		createOrUpdateRuleSetRequest.setRuleSetData(listOfRuleSet);
		ConstraintValidatorContext constraintValidatorContext = Mockito.mock(ConstraintValidatorContext.class);
		
		Boolean response =ruleSetValidator.isValid(createOrUpdateRuleSetRequest, constraintValidatorContext);
		Assert.assertFalse(response);
		
	}

	@Test
	public void validateRuleSetForFailureScenario() {
		
		
		
		ConstraintValidatorContext constraintValidatorContext = Mockito.mock(ConstraintValidatorContext.class);
		
		Boolean response =ruleSetValidator.isValid(null, constraintValidatorContext);
		Assert.assertFalse(response);
		
	}

}
