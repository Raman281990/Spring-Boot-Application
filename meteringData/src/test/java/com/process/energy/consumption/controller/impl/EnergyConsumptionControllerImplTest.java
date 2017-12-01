package com.process.energy.consumption.controller.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.process.energy.consumption.cache.MeterReadingsCacheService;
import com.process.energy.consumption.vo.request.EnergyConsumptionRequest;
import com.process.energy.consumption.vo.request.MeterCacheData;
import com.process.energy.consumption.vo.response.EnergyConsumptionResponse;

@RunWith(MockitoJUnitRunner.class)
public class EnergyConsumptionControllerImplTest {
	
	@InjectMocks
	EnergyConsumptionControllerImpl energyConsumptionControllerImpl;
	
	@Mock
	MeterReadingsCacheService meterReadingsCacheService;

	@Test
	public void calculateEnergyConsumption() {
		
		EnergyConsumptionRequest  energyConsumptionRequest = new EnergyConsumptionRequest();
		energyConsumptionRequest.setMeterId("0001");
		energyConsumptionRequest.setMonth("DEC");
		
		MeterCacheData meterCacheData = new MeterCacheData();
		meterCacheData.setConsumption(9000.00);
		
		
		Mockito.when(meterReadingsCacheService.getEnergyConsumptionValueFromCache(Mockito.any(EnergyConsumptionRequest.class))).thenReturn(meterCacheData);
		ResponseEntity<EnergyConsumptionResponse> Response = energyConsumptionControllerImpl.retrieveConsumption("0001","JAN");
		
		Assert.assertNotNull(Response);
		Assert.assertNotNull(Response.getBody().getConsumption());
	}
	
	@Test
	public void calculateEnergyConsumptionFailureScenario1() {
		
		EnergyConsumptionRequest  energyConsumptionRequest = new EnergyConsumptionRequest();
		energyConsumptionRequest.setMeterId("0001");
		energyConsumptionRequest.setMonth("DEC");
		
		MeterCacheData meterCacheData = new MeterCacheData();
		
		
		Mockito.when(meterReadingsCacheService.getEnergyConsumptionValueFromCache(Mockito.any(EnergyConsumptionRequest.class))).thenReturn(meterCacheData);
		ResponseEntity<EnergyConsumptionResponse> Response = energyConsumptionControllerImpl.retrieveConsumption("0001","JAN");
		
		Assert.assertNotNull(Response);
		Assert.assertNull(Response.getBody().getConsumption());
	}
	
	@Test
	public void calculateEnergyConsumptionFailureScenario2() {
		
		EnergyConsumptionRequest  energyConsumptionRequest = new EnergyConsumptionRequest();
		energyConsumptionRequest.setMeterId("0001");
		energyConsumptionRequest.setMonth("DEC");
		
		MeterCacheData meterCacheData = new MeterCacheData();
		
		
		Mockito.when(meterReadingsCacheService.getEnergyConsumptionValueFromCache(Mockito.any(EnergyConsumptionRequest.class))).thenReturn(null);
		ResponseEntity<EnergyConsumptionResponse> Response = energyConsumptionControllerImpl.retrieveConsumption("0001","JAN");
		
		Assert.assertNotNull(Response);
		Assert.assertNull(Response.getBody().getConsumption());
	}

}
