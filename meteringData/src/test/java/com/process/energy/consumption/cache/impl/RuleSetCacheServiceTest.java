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
import org.springframework.util.Assert;

import com.google.common.cache.CacheBuilder;
import com.process.energy.consumption.config.CacheConfig;
import com.process.energy.consumption.constants.EnergyConsumptionConstants;
import com.process.energy.consumption.vo.request.CreateOrUpdateRuleSetRequest;
import com.process.energy.consumption.vo.request.FetchRuleSetRequest;
import com.process.energy.consumption.vo.request.RuleSetData;

@RunWith(MockitoJUnitRunner.class)
public class RuleSetCacheServiceTest {
	
	
	private static final String SPEC = "initialCapacity=1000,maximumSize=100000,expireAfterWrite=480m";
	
	
	@InjectMocks
	RuleSetCacheServiceImpl ruleSetCacheServiceImpl;
	
	@Mock
	CacheConfig CacheConfig;
	
	@Test
	public void readRuleSetCache(){
		
		CreateOrUpdateRuleSetRequest createRuleSetRequest = new CreateOrUpdateRuleSetRequest();
		
		RuleSetData ruleSetData = new RuleSetData();
		ruleSetData.setFraction(0.2);
		ruleSetData.setMonth("JAN");
		ruleSetData.setProfile("A");
		List<RuleSetData> listOfRuleSetData = new ArrayList<RuleSetData>();
		listOfRuleSetData.add(ruleSetData);
		createRuleSetRequest.setRuleSetData(listOfRuleSetData);
		
		GuavaCache cache = Mockito.mock(GuavaCache.class);
		
		CacheManager cacheManager =Mockito.mock(CacheManager.class);
		
		 Mockito.when(CacheConfig.cacheManager()).thenReturn(cacheManager);
		
		Mockito.when(CacheConfig.cacheManager().getCache(Mockito.anyString())).thenReturn(cache);
		
		Boolean response = ruleSetCacheServiceImpl.addRuleSetIntoCache(createRuleSetRequest);
		Assert.isTrue(response);
		
	}
	
	
	@Test
	public void fetchRuleSetFromCacheSuccessScenario() {
		
		FetchRuleSetRequest fetchRuleSetRequest = new FetchRuleSetRequest();
		fetchRuleSetRequest.setMonth("JAN");
		fetchRuleSetRequest.setProfile("A");
		
		GuavaCache cache = new GuavaCache(EnergyConsumptionConstants.RULE_SET_CACHE, CacheBuilder.from(SPEC).build());
		cache.put("JAN"+"-"+"A", 0.2);
		CacheManager cacheManager =Mockito.mock(CacheManager.class);
		
		 Mockito.when(CacheConfig.cacheManager()).thenReturn(cacheManager);
		
		Mockito.when(CacheConfig.cacheManager().getCache(Mockito.anyString())).thenReturn(cache);
		
		String response = ruleSetCacheServiceImpl.fetchRuleSetFromCache(fetchRuleSetRequest);
		
		Assert.isNull(response);
		
		
	}
	
	@Test
	public void evictRuleSetFromCache() {
		
		
		GuavaCache cache = Mockito.mock(GuavaCache.class);
		cache.put("JAN"+"-"+"A", 0.2);
		CacheManager cacheManager =Mockito.mock(CacheManager.class);
		
		 Mockito.when(CacheConfig.cacheManager()).thenReturn(cacheManager);
		
		Mockito.when(CacheConfig.cacheManager().getCache(Mockito.anyString())).thenReturn(cache);
		
		boolean response = ruleSetCacheServiceImpl.evictRuleSetFromCache();
		
		Assert.isTrue(response);
		
		
	}
	
	
	@Test
	public void updateRuleSetCache(){
		
		CreateOrUpdateRuleSetRequest createRuleSetRequest = new CreateOrUpdateRuleSetRequest();
		
		RuleSetData ruleSetData = new RuleSetData();
		ruleSetData.setFraction(0.2);
		ruleSetData.setMonth("JAN");
		ruleSetData.setProfile("A");
		List<RuleSetData> listOfRuleSetData = new ArrayList<RuleSetData>();
		listOfRuleSetData.add(ruleSetData);
		createRuleSetRequest.setRuleSetData(listOfRuleSetData);
		
		GuavaCache cache = Mockito.mock(GuavaCache.class);
		
		CacheManager cacheManager =Mockito.mock(CacheManager.class);
		
		 Mockito.when(CacheConfig.cacheManager()).thenReturn(cacheManager);
		
		Mockito.when(CacheConfig.cacheManager().getCache(Mockito.anyString())).thenReturn(cache);
		
		Boolean response = ruleSetCacheServiceImpl.updateRuleSetIntoCache(createRuleSetRequest);
		Assert.isTrue(response);
		
	}
	

}
