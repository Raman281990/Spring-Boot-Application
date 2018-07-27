package com.process.energy.consumption.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.cache.CacheManager;

import com.process.energy.consumption.config.CacheConfig;

import junit.framework.Assert;

@RunWith(MockitoJUnitRunner.class)
public class CacheConfigTest {
	
	@InjectMocks
	CacheConfig cacheConfig;

	@Test
	public void getCacheManager() {
		
		CacheManager cacheManager = cacheConfig.cacheManager();
		Assert.assertNotNull(cacheManager);
		Assert.assertNotNull(cacheManager.getCacheNames());
		
	}
}
