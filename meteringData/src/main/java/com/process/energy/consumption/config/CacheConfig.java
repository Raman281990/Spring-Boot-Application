package com.process.energy.consumption.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;
import com.process.energy.consumption.constants.EnergyConsumptionConstants;

/**
 * @author GAGAN
 * 
 * Cache config for configuration of cache on appliation start up.
 *
 */
@Configuration
public class CacheConfig {
	
	private static final String SPEC = "initialCapacity=1000,maximumSize=100000,expireAfterWrite=480m";
	
	@Bean(name = EnergyConsumptionConstants.DATA_CACHE_MANAGER)
	public CacheManager cacheManager() {
	    List<GuavaCache> cacheList = new ArrayList<GuavaCache>();
	    cacheList.add(this.createNewCache(EnergyConsumptionConstants.RULE_SET_CACHE));
	    cacheList.add(this.createNewCache(EnergyConsumptionConstants.METER_READINGS_CACHE));
	    SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
	    simpleCacheManager.setCaches(cacheList);
	    return simpleCacheManager;
	}

	private GuavaCache createNewCache(String name) {
	    return new GuavaCache(name, CacheBuilder.from(SPEC).build());
	}
	
}
