package com.process.energy.consumption.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.process.energy.consumption.cache.MeterReadingsCacheService;
import com.process.energy.consumption.constants.ErrorConstants;
import com.process.energy.consumption.controller.EnergyConsumptionController;
import com.process.energy.consumption.vo.request.EnergyConsumptionRequest;
import com.process.energy.consumption.vo.request.MeterCacheData;
import com.process.energy.consumption.vo.response.EnergyConsumptionResponse;

/** EnergyConsumptionController controller provides endpoint for retrieving consumption value based on
 *  Meter readings and Rule Set.
 *  
 * @author GAGAN
 *
 */
@Service
public class EnergyConsumptionControllerImpl implements EnergyConsumptionController{

		
		@Autowired
		MeterReadingsCacheService meterReadingsCacheService;
		
		/**retrieveConsumption API retrives Consumption for a month based on meterId and Rule Set information in the system.
		 * @param meterId
		 * @param month
		 * @return
		 */
		@Override
		public ResponseEntity<EnergyConsumptionResponse> retrieveConsumption(@RequestParam String meterId,@RequestParam String month)
		{
			EnergyConsumptionRequest consumptionRequest = new EnergyConsumptionRequest();
			consumptionRequest.setMeterId(meterId);
			consumptionRequest.setMonth(month);
			
			MeterCacheData meterCacheData  = meterReadingsCacheService.getEnergyConsumptionValueFromCache(consumptionRequest);
			
			EnergyConsumptionResponse consumptionResponse = new EnergyConsumptionResponse();
			if(null!=meterCacheData && null!=meterCacheData.getConsumption()) {
				consumptionResponse.setConsumption(meterCacheData.getConsumption());
				return new ResponseEntity<>(consumptionResponse, HttpStatus.OK);
			}
			consumptionResponse.setMessage(ErrorConstants.NO_DATA_FOUND);
			return new ResponseEntity<>(consumptionResponse, HttpStatus.NO_CONTENT);
		}

	

}
