package com.process.energy.consumption.controller.impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;

import com.process.energy.consumption.cache.MeterReadingsCacheService;
import com.process.energy.consumption.constants.ErrorConstants;
import com.process.energy.consumption.controller.MeterReadingsController;
import com.process.energy.consumption.vo.request.GetMeterReadingsRequest;
import com.process.energy.consumption.vo.request.MeterCacheData;
import com.process.energy.consumption.vo.request.RemoveMeterReadingsRequest;
import com.process.energy.consumption.vo.request.SaveOrUpdateMeterReadingRequest;
import com.process.energy.consumption.vo.response.BusinessError;
import com.process.energy.consumption.vo.response.GetMeterReadingsResponse;
import com.process.energy.consumption.vo.response.RemoveMeterReadingResponse;
import com.process.energy.consumption.vo.response.SaveOrUpdateMeterReadingResponse;

/** MeterReadingsControllerImpl provides CRUD operations for meter readings data.
 * 1)Create Meter readings.
 * 2)Fetch Meter Readings.
 * 3)Remove meter readings.
 * 4)Update meter readings.
 * @author GAGAN
 *
 */
@Service
public class MeterReadingsControllerImpl implements MeterReadingsController{

	@Autowired
	MeterReadingsCacheService meterReadingsCacheService;

	/** createMeteringDataConsumption operation save customer data in the system.
	 * @param saveMeterReadingRequest
	 * @param errors
	 * @return
	 */
	@Override
	public ResponseEntity<SaveOrUpdateMeterReadingResponse> saveMeterReadings(@RequestBody
			@Valid SaveOrUpdateMeterReadingRequest saveMeterReadingRequest,
			BindingResult errors) {

		if(errors.hasErrors()) {
			List<BusinessError> errorList = new ArrayList<BusinessError>();
			for(ObjectError er  : errors.getAllErrors()) {
				BusinessError br = new BusinessError(er.getDefaultMessage());
				errorList.add(br);
			}

			if(!CollectionUtils.isEmpty(saveMeterReadingRequest.getMeterReadingData())
					&& meterReadingsCacheService.addOrUpdateMeteringReadings(saveMeterReadingRequest)) {
				return new ResponseEntity<>(new SaveOrUpdateMeterReadingResponse(true,errorList),HttpStatus.OK);
			}
			return new ResponseEntity<>(new SaveOrUpdateMeterReadingResponse(false,errorList),HttpStatus.BAD_REQUEST);
		}
		
		else if(meterReadingsCacheService.addOrUpdateMeteringReadings(saveMeterReadingRequest)) {
			return new ResponseEntity<>(new SaveOrUpdateMeterReadingResponse(true),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(new SaveOrUpdateMeterReadingResponse(false),HttpStatus.INTERNAL_SERVER_ERROR);

	}
	/** getMeterReadings operation fetches customer meter readings data from the system.
	 * @param meterId
	 * @param month
	 * @return
	 */
	@Override
	public ResponseEntity<GetMeterReadingsResponse> getMeterReadings(String meterId,
			String month) {

		GetMeterReadingsRequest getMeteringDataRequest = BuildCacheServiceRequest(meterId, month);
		MeterCacheData meterCache = meterReadingsCacheService.getMeterReadingsFromCache(getMeteringDataRequest);
		GetMeterReadingsResponse getMeteringDataResponse = new GetMeterReadingsResponse();
		if(null!=meterCache) {
			getMeteringDataResponse.setMeterReading(meterCache.getMeteringData());
			getMeteringDataResponse.setProfile(meterCache.getProfile());

			return new ResponseEntity<>(getMeteringDataResponse,HttpStatus.OK);
		}

		BusinessError br = new BusinessError(ErrorConstants.NO_DATA_FOUND);
		getMeteringDataResponse.setBusinessError(br);
		return new ResponseEntity<>(getMeteringDataResponse,HttpStatus.NO_CONTENT);
	}
	/**removeMeterReadings operation removes customer meter reading data from the system.
	 * @param removeMeterReadings
	 * @return
	 */
	@Override
	public ResponseEntity<RemoveMeterReadingResponse> removeMeterReadings(
			RemoveMeterReadingsRequest removeMeterReadings) {
		
		if(meterReadingsCacheService.removeMeterReadingsFromCache(removeMeterReadings)) {
			return new ResponseEntity<>(new RemoveMeterReadingResponse(Boolean.TRUE),HttpStatus.OK);
		}
		return new ResponseEntity<>(new RemoveMeterReadingResponse(Boolean.FALSE),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	/**updateMeteringDataConsumption operation updates customer meter readings from the system.
	 * @param updateMeterReadings
	 * @param errors
	 * @return
	 */
	@Override
	public ResponseEntity<SaveOrUpdateMeterReadingResponse> updateMeteringDataConsumptionReadings(
			SaveOrUpdateMeterReadingRequest updateMeterReadings, BindingResult errors) {
		if(errors.hasErrors()) {
			List<BusinessError> errorList = new ArrayList<BusinessError>();
			for(ObjectError er  : errors.getAllErrors()) {
				BusinessError br = new BusinessError(er.getDefaultMessage());
				errorList.add(br);
			}

			if(!CollectionUtils.isEmpty(updateMeterReadings.getMeterReadingData())
					&& meterReadingsCacheService.addOrUpdateMeteringReadings(updateMeterReadings)) {
				return new ResponseEntity<>(new SaveOrUpdateMeterReadingResponse(true,errorList),HttpStatus.OK);
			}
			return new ResponseEntity<>(new SaveOrUpdateMeterReadingResponse(false,errorList),HttpStatus.BAD_REQUEST);
		}
		
		else if(meterReadingsCacheService.addOrUpdateMeteringReadings(updateMeterReadings)) {
			return new ResponseEntity<>(new SaveOrUpdateMeterReadingResponse(true),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(new SaveOrUpdateMeterReadingResponse(false),HttpStatus.INTERNAL_SERVER_ERROR);

	}
	/**BuildCacheServiceRequest build request fro fetching meter readings
	 * @param meterId
	 * @param month
	 * @return
	 */
	private GetMeterReadingsRequest BuildCacheServiceRequest(String meterId, String month) {
		GetMeterReadingsRequest getMeteringDataRequest = new GetMeterReadingsRequest();
		getMeteringDataRequest.setMeterId(meterId);
		getMeteringDataRequest.setMonth(month);
		return getMeteringDataRequest;
	}

	
	

}