package com.process.energy.consumption.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.process.energy.consumption.cache.RuleSetCacheService;
import com.process.energy.consumption.config.CacheConfig;
import com.process.energy.consumption.constants.EnergyConsumptionConstants;
import com.process.energy.consumption.vo.request.CreateOrUpdateRuleSetRequest;
import com.process.energy.consumption.vo.request.FetchRuleSetRequest;

/**
 * @author RAMAN
 * 
 *RuleSetCacheServiceImpl class
 *contains operations to update cache for Rule Set Data
 *1) Add Rule Set in Cache
 *2) Fetch Rule Set from Cache
 *3) Evict Rule Set from Cache
 *4) Update Rule Set in Cache 
 *
 */
@Component
public class RuleSetCacheServiceImpl implements RuleSetCacheService{
	
	private static String HYPEN_CONSTANT="-";
	
	@Autowired
	CacheConfig cacheConfig;

	/* (non-Javadoc)
	 * @see com.process.energy.consumption.cache.RuleSetCacheService#addRuleSetIntoCache(com.process.energy.consumption.model.CreateOrUpdateRuleSetRequest)
	 */
	@Override
	@Cacheable(cacheManager = EnergyConsumptionConstants.DATA_CACHE_MANAGER , value = EnergyConsumptionConstants.RULE_SET_CACHE)
	@Transactional(readOnly = true)
	public boolean addRuleSetIntoCache(
			CreateOrUpdateRuleSetRequest createRuleSetRequest) {

		GuavaCache ruleSetCache = (GuavaCache) cacheConfig.cacheManager().getCache(EnergyConsumptionConstants.RULE_SET_CACHE);
		createRuleSetRequest.getRuleSetData().stream().
		forEach(ruleSetData -> ruleSetCache.put(ruleSetData.getProfile()+HYPEN_CONSTANT+ruleSetData
				.getMonth(),ruleSetData.getFraction()));

		return true;

	}
	
	/* (non-Javadoc)
	 * @see com.process.energy.consumption.cache.RuleSetCacheService#fetchRuleSetFromCache(com.process.energy.consumption.model.FetchRuleSetRequest)
	 */
	@Override
	public String fetchRuleSetFromCache(FetchRuleSetRequest fetchRuleSetRequest) {

		GuavaCache ruleSetCache = (GuavaCache) cacheConfig.cacheManager().getCache(EnergyConsumptionConstants.RULE_SET_CACHE);
		@SuppressWarnings("unchecked")
		ValueWrapper value= ruleSetCache.get(fetchRuleSetRequest.getProfile()+HYPEN_CONSTANT+fetchRuleSetRequest.getMonth());
		if(null!=value) {
			return value.get().toString();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see com.process.energy.consumption.cache.RuleSetCacheService#evictRuleSetFromCache()
	 */
	@Override
	@CacheEvict(cacheManager = EnergyConsumptionConstants.DATA_CACHE_MANAGER , value = EnergyConsumptionConstants.RULE_SET_CACHE)
	@Transactional(readOnly = true)
	public boolean evictRuleSetFromCache() {

		GuavaCache ruleSetCache = (GuavaCache) cacheConfig.cacheManager().getCache(EnergyConsumptionConstants.RULE_SET_CACHE);
		ruleSetCache.clear();
		return true;
	}
	
	/* (non-Javadoc)
	 * @see com.process.energy.consumption.cache.RuleSetCacheService#updateRuleSetIntoCache(com.process.energy.consumption.model.CreateOrUpdateRuleSetRequest)
	 */
	@Override
	@CachePut(cacheManager = EnergyConsumptionConstants.DATA_CACHE_MANAGER , value = EnergyConsumptionConstants.RULE_SET_CACHE)
	@Transactional(readOnly = true)
	public boolean updateRuleSetIntoCache(
			CreateOrUpdateRuleSetRequest updateRuleSet) {

		GuavaCache ruleSetCache = (GuavaCache) cacheConfig.cacheManager().getCache(EnergyConsumptionConstants.RULE_SET_CACHE);
		updateRuleSet.getRuleSetData().stream().
		forEach(update -> ruleSetCache.put(update.getProfile()+"-"+update
				.getMonth(),update.getFraction()));

		return true;

	
	}
}
