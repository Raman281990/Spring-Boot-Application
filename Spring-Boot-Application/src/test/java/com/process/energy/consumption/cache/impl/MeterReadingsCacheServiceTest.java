package com.process.energy.consumption.cache.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCache;

import com.process.energy.consumption.config.CacheConfig;
import com.process.energy.consumption.vo.request.EnergyConsumptionRequest;
import com.process.energy.consumption.vo.request.GetMeterReadingsRequest;
import com.process.energy.consumption.vo.request.MeterCacheData;
import com.process.energy.consumption.vo.request.MeterReadingData;
import com.process.energy.consumption.vo.request.RemoveMeterReadingsRequest;
import com.process.energy.consumption.vo.request.SaveOrUpdateMeterReadingRequest;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class MeterReadingsCacheServiceTest {

	@InjectMocks
	MeterReadingsCacheServiceImpl meterReadingsCacheService;

	@Mock
	CacheConfig cacheConfig;

	@Test
	public void addOrUpdateMeterReadings() {

		SaveOrUpdateMeterReadingRequest saveOrUpdateMeterReadingRequest = new SaveOrUpdateMeterReadingRequest();
		List<MeterReadingData> listOfMeterReading = new ArrayList<MeterReadingData>();

		MeterReadingData meterReadingData = new MeterReadingData();
		meterReadingData.setProfile("A");
		meterReadingData.setMeterId("0001");
		meterReadingData.setMeterReading(1000.00);
		meterReadingData.setMonth("JAN");
		listOfMeterReading.add(meterReadingData);
		saveOrUpdateMeterReadingRequest.setMeterReadingData(listOfMeterReading);
		

		GuavaCache cache = Mockito.mock(GuavaCache.class);

		CacheManager cacheManager =Mockito.mock(CacheManager.class);

		Mockito.when(cacheConfig.cacheManager()).thenReturn(cacheManager);

		Mockito.when(cacheConfig.cacheManager().getCache(Mockito.anyString())).thenReturn(cache);

		Boolean response = meterReadingsCacheService.addOrUpdateMeteringReadings(saveOrUpdateMeterReadingRequest);
		
		Assert.assertTrue(response);

	}
	
	@Test
	public void fetchMeterReadings(){
		
		GuavaCache cache = Mockito.mock(GuavaCache.class);

		CacheManager cacheManager =Mockito.mock(CacheManager.class);

		Mockito.when(cacheConfig.cacheManager()).thenReturn(cacheManager);

		Mockito.when(cacheConfig.cacheManager().getCache(Mockito.anyString())).thenReturn(cache);
		
		GetMeterReadingsRequest getMeteringDataRequest = new GetMeterReadingsRequest();
		getMeteringDataRequest.setMeterId("0001");
		getMeteringDataRequest.setMonth("JAN");
		MeterCacheData response = meterReadingsCacheService.getMeterReadingsFromCache(getMeteringDataRequest);
		
		Assert.assertNull(response);
		
	}
     @Test
	public void removeMeterReadings() {
    	 
    	 
    	 GuavaCache cache = Mockito.mock(GuavaCache.class);

 		CacheManager cacheManager =Mockito.mock(CacheManager.class);

 		Mockito.when(cacheConfig.cacheManager()).thenReturn(cacheManager);

 		Mockito.when(cacheConfig.cacheManager().getCache(Mockito.anyString())).thenReturn(cache);
 		RemoveMeterReadingsRequest removeMeterReadingsRequest = new  RemoveMeterReadingsRequest();
 		removeMeterReadingsRequest.setMeterId("0001");
 		removeMeterReadingsRequest.setMonth("JAN");
 		boolean response = meterReadingsCacheService.removeMeterReadingsFromCache(removeMeterReadingsRequest);
 		
 		Assert.assertTrue(response);
		
	}
  @Test
  public void getEnergyConsumptionValueFromCache(){
	  
	  GuavaCache cache = Mockito.mock(GuavaCache.class);

		CacheManager cacheManager =Mockito.mock(CacheManager.class);

		Mockito.when(cacheConfig.cacheManager()).thenReturn(cacheManager);

		Mockito.when(cacheConfig.cacheManager().getCache(Mockito.anyString())).thenReturn(cache);
		EnergyConsumptionRequest energyConsumptionRequest = new EnergyConsumptionRequest();
		energyConsumptionRequest.setMeterId("001");
		energyConsumptionRequest.setMonth("JAN");
		MeterCacheData response = meterReadingsCacheService.getEnergyConsumptionValueFromCache(energyConsumptionRequest);
		
		Assert.assertNull(response);
  }

}
