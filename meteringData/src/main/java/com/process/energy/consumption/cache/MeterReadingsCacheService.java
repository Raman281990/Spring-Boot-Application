package com.process.energy.consumption.cache;

import com.process.energy.consumption.vo.request.EnergyConsumptionRequest;
import com.process.energy.consumption.vo.request.GetMeterReadingsRequest;
import com.process.energy.consumption.vo.request.MeterCacheData;
import com.process.energy.consumption.vo.request.RemoveMeterReadingsRequest;
import com.process.energy.consumption.vo.request.SaveOrUpdateMeterReadingRequest;

/**
 * @author GAGAN
 * 
 * MeterReadingsCacheService is a interface containing CRUD operations for Meter Readings
 *
 */
public interface MeterReadingsCacheService {
	
	/** addOrUpdateMeteringReadings operation add/updates meter Readings in cache
	 * @param saveMeterReadingRequest
	 * @return
	 */
	public boolean addOrUpdateMeteringReadings(SaveOrUpdateMeterReadingRequest saveMeterReadingRequest);
	
	/** removeMeterReadingsFromCache removes meter readings from cache.
	 * @param removeMeterReadingsRequest
	 * @return
	 */
	public boolean removeMeterReadingsFromCache(RemoveMeterReadingsRequest removeMeterReadingsRequest);
	
	/** getMeterReadingsFromCache fetches meter readings from cache.
	 * @param getMeteringDataRequest
	 * @return
	 */
	public MeterCacheData getMeterReadingsFromCache(GetMeterReadingsRequest getMeteringDataRequest);
	/**
	 * getEnergyConsumptionValueFromCache retrives consumption value for meter readings
	 * @param consumptionRequest
	 * @return
	 */
	public MeterCacheData getEnergyConsumptionValueFromCache(EnergyConsumptionRequest consumptionRequest);


}