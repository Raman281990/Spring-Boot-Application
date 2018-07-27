package com.process.energy.consumption.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import com.process.energy.consumption.cache.RuleSetCacheService;
import com.process.energy.consumption.vo.request.CreateOrUpdateRuleSetRequest;
import com.process.energy.consumption.vo.request.FetchRuleSetRequest;
import com.process.energy.consumption.vo.request.MeterCacheData;
import com.process.energy.consumption.vo.request.RuleSetData;
import com.process.energy.consumption.vo.response.CreateOrUpdateRuleSetResponse;
import com.process.energy.consumption.vo.response.FetchRuleSetResponse;
import com.process.energy.consumption.vo.response.RemoveRuleSetResponse;



@RunWith(MockitoJUnitRunner.class)
public class RuleSetForEnergyConsumptionControllerTest {
	
	
	@InjectMocks
	RuleSetForEnergyConsumptionControllerImpl ruleSetForEnergyConsumptionControllerImpl;
	
	@Mock
	RuleSetCacheService ruleSetCacheService;
	
	
	@Test
	public void testCreateRuleSetForSuccessScenario() {
		
		Mockito.when(ruleSetCacheService.addRuleSetIntoCache(Mockito.mock(CreateOrUpdateRuleSetRequest.class)))
		.thenReturn(Boolean.TRUE);
		
		BindingResult mockedBindignResults = (BindingResult) Mockito.mock(BindingResult.class);
		
		CreateOrUpdateRuleSetRequest createRuleSetRequest = new CreateOrUpdateRuleSetRequest();
		RuleSetData ruleSetData = new RuleSetData();
		ruleSetData.setFraction(0.2);
		ruleSetData.setMonth("JAN");
		ruleSetData.setProfile("A");
		List<RuleSetData> listOfRuleSet = new ArrayList<RuleSetData>();
		listOfRuleSet.add(ruleSetData);
		createRuleSetRequest.setRuleSetData(listOfRuleSet);
		
		Mockito.when(mockedBindignResults.hasErrors()).thenReturn(Boolean.FALSE);
		
		ResponseEntity<CreateOrUpdateRuleSetResponse> response = ruleSetForEnergyConsumptionControllerImpl.saveRuleSetForEnergyConsumption(createRuleSetRequest, mockedBindignResults);
		Assert.notNull(response);
		Assert.notNull(response.getStatusCode().OK);
		
	}
	
	@Test
	public void testCreateRuleSetForFailureScenario() {
		
		Mockito.when(ruleSetCacheService.addRuleSetIntoCache(Mockito.mock(CreateOrUpdateRuleSetRequest.class)))
		.thenReturn(Boolean.FALSE);
		
		BindingResult mockedBindignResults = (BindingResult) Mockito.mock(BindingResult.class);
		Errors mockedErrorResults = (Errors) Mockito.mock(Errors.class);
		List<ObjectError> listOfErrorObjects = new ArrayList<ObjectError>();
		
		ObjectError objectError = new ObjectError("INVALID_Profile", "INCorrect Profile Data");
		listOfErrorObjects.add(objectError);
		
		mockedBindignResults.addError(objectError);
		mockedBindignResults.addAllErrors(mockedErrorResults);
		
		Mockito.when(mockedBindignResults.hasErrors()).thenReturn(Boolean.TRUE);
		
		CreateOrUpdateRuleSetRequest createRuleSetRequest = new CreateOrUpdateRuleSetRequest();
		RuleSetData ruleSetData = new RuleSetData();
		ruleSetData.setFraction(0.2);
		ruleSetData.setMonth("JAN");
		ruleSetData.setProfile("A");
		List<RuleSetData> listOfRuleSet = new ArrayList<RuleSetData>();
		listOfRuleSet.add(ruleSetData);
		createRuleSetRequest.setRuleSetData(listOfRuleSet);
		
		ResponseEntity<CreateOrUpdateRuleSetResponse> response = ruleSetForEnergyConsumptionControllerImpl.saveRuleSetForEnergyConsumption(createRuleSetRequest, mockedBindignResults);
		Assert.notNull(response);
		Assert.notNull(response.getBody().getErrors());
		
	}
	

	@Test
	public void testRemoveRuleSetSuccessScenario() {
		
		Mockito.when(ruleSetCacheService.evictRuleSetFromCache())
		.thenReturn(Boolean.TRUE);
		
		BindingResult mockedBindignResults = (BindingResult) Mockito.mock(BindingResult.class);
		
		Mockito.when(mockedBindignResults.hasErrors()).thenReturn(Boolean.FALSE);
		
		ResponseEntity<RemoveRuleSetResponse> response = ruleSetForEnergyConsumptionControllerImpl.removeRuleSetForEnergyConsumption();
		Assert.notNull(response);
		Assert.notNull(response.getStatusCode().OK);
		
	}
	
	
	@Test
	public void testRemoveRuleSetFailureScenario() {
		
		Mockito.when(ruleSetCacheService.evictRuleSetFromCache())
		.thenReturn(Boolean.FALSE);
		
		BindingResult mockedBindignResults = (BindingResult) Mockito.mock(BindingResult.class);
		
		Mockito.when(mockedBindignResults.hasErrors()).thenReturn(Boolean.FALSE);
		
		ResponseEntity<RemoveRuleSetResponse> response = ruleSetForEnergyConsumptionControllerImpl.removeRuleSetForEnergyConsumption();
		Assert.notNull(response);
		Assert.notNull(response.getStatusCode().INTERNAL_SERVER_ERROR);
		
	}
	
	
	@Test
	public void testFetchRuleSetForSuccessScenario() {
		
		
		MeterCacheData data = new MeterCacheData();
		data.setMeteringData(100.00);
		data.setProfile("A");
		
		Mockito.when(ruleSetCacheService.fetchRuleSetFromCache(Mockito.mock(FetchRuleSetRequest.class)))
		.thenReturn(data.getMeteringData().toString());
		
		BindingResult mockedBindignResults = (BindingResult) Mockito.mock(BindingResult.class);
		
		Mockito.when(mockedBindignResults.hasErrors()).thenReturn(Boolean.FALSE);
		
		ResponseEntity<FetchRuleSetResponse> response = ruleSetForEnergyConsumptionControllerImpl.fetchRuleSetForEnergyConsumption("0001", "JAN");
		Assert.notNull(response);
		Assert.notNull(response.getStatusCode().OK);
		
	}
	@Test
	public void testFetchRuleSetWhenFractionIsNull() {
		
		
		MeterCacheData data = new MeterCacheData();
		data.setMeteringData(100.00);
		data.setProfile(null);
		
		Mockito.when(ruleSetCacheService.fetchRuleSetFromCache(Mockito.mock(FetchRuleSetRequest.class)))
		.thenReturn(data.getMeteringData().toString());
		
		BindingResult mockedBindignResults = (BindingResult) Mockito.mock(BindingResult.class);
		
		Mockito.when(mockedBindignResults.hasErrors()).thenReturn(Boolean.FALSE);
		
		ResponseEntity<FetchRuleSetResponse> response = ruleSetForEnergyConsumptionControllerImpl.fetchRuleSetForEnergyConsumption("0001", "JAN");
		Assert.notNull(response);
		Assert.notNull(response.getStatusCode().NO_CONTENT);
		
	}
	@Test
	public void testUpdateRuleSetForSuccessScenario() {
		
		Mockito.when(ruleSetCacheService.updateRuleSetIntoCache(Mockito.mock(CreateOrUpdateRuleSetRequest.class)))
		.thenReturn(Boolean.TRUE);
		
		BindingResult mockedBindignResults = (BindingResult) Mockito.mock(BindingResult.class);
		
		CreateOrUpdateRuleSetRequest createRuleSetRequest = new CreateOrUpdateRuleSetRequest();
		RuleSetData ruleSetData = new RuleSetData();
		ruleSetData.setFraction(0.2);
		ruleSetData.setMonth("JAN");
		ruleSetData.setProfile("A");
		List<RuleSetData> listOfRuleSet = new ArrayList<RuleSetData>();
		listOfRuleSet.add(ruleSetData);
		createRuleSetRequest.setRuleSetData(listOfRuleSet);
		
		Mockito.when(mockedBindignResults.hasErrors()).thenReturn(Boolean.FALSE);
		
		ResponseEntity<CreateOrUpdateRuleSetResponse> response = ruleSetForEnergyConsumptionControllerImpl.updateRuleSetForEnergyConsumption(createRuleSetRequest, mockedBindignResults);
		Assert.notNull(response);
		Assert.notNull(response.getStatusCode().OK);
		
	}
	
	@Test
	public void testUpdateRuleSetValidationErrors1(){
		
		Mockito.when(ruleSetCacheService.updateRuleSetIntoCache(Mockito.mock(CreateOrUpdateRuleSetRequest.class)))
		.thenReturn(Boolean.TRUE);
		
		BindingResult mockedBindignResults = (BindingResult) Mockito.mock(BindingResult.class);
		
		CreateOrUpdateRuleSetRequest createRuleSetRequest = new CreateOrUpdateRuleSetRequest();
		RuleSetData ruleSetData = new RuleSetData();
		ruleSetData.setFraction(0.2);
		ruleSetData.setMonth("JAN");
		ruleSetData.setProfile("A");
		List<RuleSetData> listOfRuleSet = new ArrayList<RuleSetData>();
		listOfRuleSet.add(ruleSetData);
		createRuleSetRequest.setRuleSetData(listOfRuleSet);
		
		Mockito.when(mockedBindignResults.hasErrors()).thenReturn(Boolean.FALSE);
		Mockito.when(mockedBindignResults.hasErrors()).thenReturn(true);
		List<ObjectError> listOfError = new ArrayList<ObjectError>();
		listOfError.add(new ObjectError("INVALID DATA", "INVALID DATA"));
		Mockito.when(mockedBindignResults.getAllErrors()).thenReturn(listOfError);
		
		ResponseEntity<CreateOrUpdateRuleSetResponse> response = ruleSetForEnergyConsumptionControllerImpl.updateRuleSetForEnergyConsumption(createRuleSetRequest, mockedBindignResults);
		Assert.notNull(response);
		Assert.notNull(response.getStatusCode().OK);
		
	}
	
	@Test
	public void testUpdateRuleSetValidationErrors2(){
		
		Mockito.when(ruleSetCacheService.updateRuleSetIntoCache(Mockito.mock(CreateOrUpdateRuleSetRequest.class)))
		.thenReturn(Boolean.FALSE);
		
		BindingResult mockedBindignResults = (BindingResult) Mockito.mock(BindingResult.class);
		
		CreateOrUpdateRuleSetRequest createRuleSetRequest = new CreateOrUpdateRuleSetRequest();
		RuleSetData ruleSetData = new RuleSetData();
		ruleSetData.setFraction(0.2);
		ruleSetData.setMonth("JAN");
		ruleSetData.setProfile("A");
		List<RuleSetData> listOfRuleSet = new ArrayList<RuleSetData>();
		listOfRuleSet.add(ruleSetData);
		createRuleSetRequest.setRuleSetData(listOfRuleSet);
		
		Mockito.when(mockedBindignResults.hasErrors()).thenReturn(Boolean.FALSE);
		Mockito.when(mockedBindignResults.hasErrors()).thenReturn(true);
		List<ObjectError> listOfError = new ArrayList<ObjectError>();
		listOfError.add(new ObjectError("INVALID DATA", "INVALID DATA"));
		Mockito.when(mockedBindignResults.getAllErrors()).thenReturn(listOfError);
		
		ResponseEntity<CreateOrUpdateRuleSetResponse> response = ruleSetForEnergyConsumptionControllerImpl.updateRuleSetForEnergyConsumption(createRuleSetRequest, mockedBindignResults);
		Assert.notNull(response);
		Assert.notNull(response.getStatusCode().OK);
		
	}
	
	
}
