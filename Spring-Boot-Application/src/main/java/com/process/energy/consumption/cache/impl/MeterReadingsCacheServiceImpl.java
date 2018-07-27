package com.process.energy.consumption.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.process.energy.consumption.cache.MeterReadingsCacheService;
import com.process.energy.consumption.config.CacheConfig;
import com.process.energy.consumption.constants.EnergyConsumptionConstants;
import com.process.energy.consumption.vo.request.EnergyConsumptionRequest;
import com.process.energy.consumption.vo.request.GetMeterReadingsRequest;
import com.process.energy.consumption.vo.request.MeterCacheData;
import com.process.energy.consumption.vo.request.RemoveMeterReadingsRequest;
import com.process.energy.consumption.vo.request.SaveOrUpdateMeterReadingRequest;


/**
 * @author GAGAN
 * 
 * MeterReadingsCacheServiceImpl is a interface containing CRUD operations for Meter Readings
 *
 */
@Service
public class MeterReadingsCacheServiceImpl implements MeterReadingsCacheService {
	
	
	private static final String HYPEN_CONSTANT="-";
	
	@Autowired
	CacheConfig cacheConfig;

	/* (non-Javadoc)
	 * @see com.process.energy.consumption.cache.MeterReadingsCacheService#addOrUpdateMeteringReadings(com.process.energy.consumption.model.SaveOrUpdateMeterReadingRequest)
	 */
	@Override
	@Cacheable(cacheManager = EnergyConsumptionConstants.DATA_CACHE_MANAGER , value = EnergyConsumptionConstants.METER_READINGS_CACHE)
	@Transactional(readOnly = true)
	public boolean addOrUpdateMeteringReadings(
			SaveOrUpdateMeterReadingRequest saveMeterReadingRequest) {
		
		GuavaCache meterReadingCache = (GuavaCache) cacheConfig.cacheManager().getCache(EnergyConsumptionConstants.METER_READINGS_CACHE);
		
		saveMeterReadingRequest.getMeterReadingData().stream().
		forEach(a -> meterReadingCache.put(a.getMeterId()+HYPEN_CONSTANT+a.getMonth(),
				new MeterCacheData(a.getProfile(),a.getMeterReading(),a.getConsumption())));
		return true;
		
	}
	
	/* (non-Javadoc)
	 * @see com.process.energy.consumption.cache.MeterReadingsCacheService#removeMeterReadingsFromCache(com.process.energy.consumption.model.RemoveMeterReadingsRequest)
	 */
	@CacheEvict(cacheManager = EnergyConsumptionConstants.DATA_CACHE_MANAGER, value = EnergyConsumptionConstants.METER_READINGS_CACHE)
	@Transactional(readOnly = true)
	@Override
	public boolean removeMeterReadingsFromCache(RemoveMeterReadingsRequest removeMeterReadingsRequest) {

		GuavaCache meterReadingsCache = (GuavaCache) cacheConfig.cacheManager().getCache(EnergyConsumptionConstants.METER_READINGS_CACHE);
		meterReadingsCache.evict(removeMeterReadingsRequest.getMeterId()+HYPEN_CONSTANT
				+removeMeterReadingsRequest.getMonth());
		return true;
	}

	/* (non-Javadoc)
	 * @see com.process.energy.consumption.cache.MeterReadingsCacheService#getMeterReadingsFromCache(com.process.energy.consumption.model.GetMeterReadingsRequest)
	 */
	@Override
	public MeterCacheData getMeterReadingsFromCache(GetMeterReadingsRequest getMeteringDataRequest) {
		
		GuavaCache meterReadingCache = (GuavaCache) cacheConfig.cacheManager().getCache(EnergyConsumptionConstants.METER_READINGS_CACHE);
		
		ValueWrapper value= meterReadingCache.get(getMeteringDataRequest.getMeterId()+HYPEN_CONSTANT
				+getMeteringDataRequest.getMonth());
		if(null!=value) {
			return ((MeterCacheData)value.get());
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see com.process.energy.consumption.cache.MeterReadingsCacheService#getEnergyConsumptionValueFromCache(com.process.energy.consumption.model.EnergyConsumptionRequest)
	 */
	@Override
	public MeterCacheData getEnergyConsumptionValueFromCache(
			EnergyConsumptionRequest consumptionRequest) {
		
		GuavaCache meterReadingCache = (GuavaCache) cacheConfig.cacheManager().getCache(EnergyConsumptionConstants.METER_READINGS_CACHE);
		
		ValueWrapper value= meterReadingCache.get(consumptionRequest.getMeterId()+HYPEN_CONSTANT
				+consumptionRequest.getMonth());
		if(null!=value) {
			return ((MeterCacheData)value.get());
		}
		return null;
	}
	
}
