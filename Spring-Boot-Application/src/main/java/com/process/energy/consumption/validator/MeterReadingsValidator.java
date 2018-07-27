package com.process.energy.consumption.validator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.process.energy.consumption.annotations.ValidMeterReadings;
import com.process.energy.consumption.config.CacheConfig;
import com.process.energy.consumption.constants.EnergyConsumptionConstants;
import com.process.energy.consumption.enums.MonthEnum;
import com.process.energy.consumption.vo.request.MeterReadingData;
import com.process.energy.consumption.vo.request.SaveOrUpdateMeterReadingRequest;

import lombok.extern.slf4j.Slf4j;

/**MeterReadingsValidator performs validations on meter readings received in the application.
 * 
 * 1) Meter Reading of a month must not be greater than the previous month.
 * 2) Profile data must exists.
 * 3) Consumption for a month must be consistent based on the  rule set and tolearance.
 * 
 * @author GAGAN
 *
 */
@Slf4j
@Component
public class MeterReadingsValidator implements ConstraintValidator<ValidMeterReadings, SaveOrUpdateMeterReadingRequest> {


	@Autowired
	CacheConfig cacheConfig;

	@Override
	public void initialize(ValidMeterReadings constraintAnnotation) {

	}

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(SaveOrUpdateMeterReadingRequest request,
			ConstraintValidatorContext context) {
		
		boolean valid = true;
		if (null != request && !CollectionUtils.isEmpty(request.getMeterReadingData())) {

			Map<String, List<MeterReadingData>> groupedMap = request.getMeterReadingData().stream()
					.collect(Collectors.groupingBy(MeterReadingData::getMeterId));

			for (Map.Entry<String, List<MeterReadingData>> entry : groupedMap.entrySet()) {

				log.info("::Processing Data for the MeterId::->" + entry.getKey());
				List<MeterReadingData> listOfMeteringData  = entry.getValue();

				//sorting the list as per month in ascending order
				Collections.sort(listOfMeteringData, new Comparator<MeterReadingData> () {
					@Override
					public int compare(MeterReadingData o1, MeterReadingData o2) {
						return  Integer.valueOf(MonthEnum.fromValue(o1.getMonth()).getIndex())
								.compareTo(Integer.valueOf(MonthEnum.fromValue(o2.getMonth()).getIndex()));

					}
				});

				if(!checkReadingOfPreviousMonth(context, listOfMeteringData,request)
						|| !checkIfProfileExitsForMeterReading(context, listOfMeteringData,request)
						|| !CheckIfConsumptionForAMonthIsConsistent(context, listOfMeteringData, request)) {
					valid = false;
				}

			}

		}
		return valid;
	}


	/**checkReadingOfPreviousMonth validates if the meter reading is higher than the previous month.
	 * 
	 * @param context
	 * @param listOfMeteringData
	 * @param request
	 * @return
	 */
	private boolean checkReadingOfPreviousMonth(
			ConstraintValidatorContext context,
			List<MeterReadingData> listOfMeteringData,SaveOrUpdateMeterReadingRequest request) {

		for(MeterReadingData meterReadingData : listOfMeteringData) {

			if(!(listOfMeteringData.get(0) == meterReadingData)){

				if(meterReadingData.getMeterReading()
						< (listOfMeteringData.get((listOfMeteringData.indexOf(meterReadingData) -1)).getMeterReading())) {

					context.buildConstraintViolationWithTemplate("Reading for the month is higher than previous month for  For Meter Id"+meterReadingData.getMeterId()
					+"And Month"+meterReadingData.getMonth()).addConstraintViolation().disableDefaultConstraintViolation();
					request.getMeterReadingData().removeAll(listOfMeteringData);
					return false;

				}
			}
		}
		return true;
	}

	/** checkIfProfileExitsForMeterReading if the profile data exists for the given data.
	 * @param context
	 * @param listOfMeteringData
	 * @param request
	 * @return
	 */
	private boolean checkIfProfileExitsForMeterReading(
			ConstraintValidatorContext context,
			List<MeterReadingData> listOfMeteringData ,SaveOrUpdateMeterReadingRequest request) {

		for(MeterReadingData meterReadingData : listOfMeteringData) {

			GuavaCache meteringDataCache = (GuavaCache) cacheConfig.cacheManager().getCache(EnergyConsumptionConstants.RULE_SET_CACHE);
			ValueWrapper valueWrapper =meteringDataCache.get(meterReadingData.getProfile()+"-"+meterReadingData.getMonth());

			if(null == valueWrapper) {
				context.buildConstraintViolationWithTemplate("Profile doesn't exits in the rule set for Meter id "+meterReadingData.getMeterId()).addConstraintViolation().disableDefaultConstraintViolation();
				request.getMeterReadingData().removeAll(listOfMeteringData);
				return false;

			}
		}
		return true;

	}


	/**CheckIfConsumptionForAMonthIsConsistent validates if the consumption value is consistent as per rule set.
	 * @param context
	 * @param listOfMeteringData
	 * @param request
	 * @return
	 */
	private boolean CheckIfConsumptionForAMonthIsConsistent(ConstraintValidatorContext context,
			List<MeterReadingData> listOfMeteringData ,SaveOrUpdateMeterReadingRequest request) {

		boolean valid = false;
		int totalConsumption = 0;
		for(MeterReadingData meterReadingData : listOfMeteringData) {

			if(!(listOfMeteringData.get(0) == meterReadingData)){

				totalConsumption+= meterReadingData.getMeterReading()
						- (listOfMeteringData.get((listOfMeteringData.indexOf(meterReadingData) -1)).getMeterReading());

			}
		}

		totalConsumption+=listOfMeteringData.get(0).getMeterReading();
		
		for(MeterReadingData meterReadingData : listOfMeteringData) {

			Double fraction = 0.0;
			Double expectedConsumptionValue = 0.0;
			Double shiftInValue =0.0;
			Double minRange =0.0;
			Double maxRange = 0.0;
			Double consumptionForAMonth = 0.0;

			if(!(listOfMeteringData.get(0) == meterReadingData)) {
				consumptionForAMonth = meterReadingData.getMeterReading()
						- (listOfMeteringData.get((listOfMeteringData.indexOf(meterReadingData) -1)).getMeterReading());
			}
			else {
				consumptionForAMonth = meterReadingData.getMeterReading();
			}
			GuavaCache meteringDataCache = (GuavaCache) cacheConfig.cacheManager().getCache(EnergyConsumptionConstants.RULE_SET_CACHE);
			ValueWrapper valueWrapper =meteringDataCache.get(meterReadingData.getProfile()+"-"+meterReadingData.getMonth());
			if(null!=valueWrapper) {
				fraction = (Double)(valueWrapper.get());
				expectedConsumptionValue = fraction*totalConsumption;
				shiftInValue = Math.abs(((totalConsumption *25)/100 )- expectedConsumptionValue);
				minRange = expectedConsumptionValue -shiftInValue;
				maxRange = expectedConsumptionValue + shiftInValue;
				
				if(minRange <= consumptionForAMonth && consumptionForAMonth <= maxRange) {
					meterReadingData.setConsumption(consumptionForAMonth);
					valid = true;
				}
				else{
					context.buildConstraintViolationWithTemplate("Consumption value is not permissible for a month for "+meterReadingData.getMeterId()
							+"And Month "+meterReadingData.getMonth()).addConstraintViolation().disableDefaultConstraintViolation();
					request.getMeterReadingData().removeAll(listOfMeteringData);
					return false;
				}

			}
		}

		return valid;
	}

}
