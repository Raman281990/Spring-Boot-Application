package com.process.energy.consumption.controller;


import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.process.energy.consumption.vo.request.RemoveMeterReadingsRequest;
import com.process.energy.consumption.vo.request.SaveOrUpdateMeterReadingRequest;
import com.process.energy.consumption.vo.response.GetMeterReadingsResponse;
import com.process.energy.consumption.vo.response.RemoveMeterReadingResponse;
import com.process.energy.consumption.vo.response.SaveOrUpdateMeterReadingResponse;

import io.swagger.annotations.ApiOperation;

/** MeterReadingsController provides CRUD operations for meter readings data.
 * 1)Create Meter readings.
 * 2)Fetch Meter Readings.
 * 3)Remove meter readings.
 * 4)Update meter readings.
 * @author GAGAN
 *
 */
@RestController
@RequestMapping("/meterReading")
public interface MeterReadingsController {
	
	/** createMeteringDataConsumption operation save customer data in the system.
	 * @param saveMeterReadingRequest
	 * @param errors
	 * @return
	 */
	@ApiOperation(value="Save customer data",notes="This API saved Meter readings in the system after performing certain validations")
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SaveOrUpdateMeterReadingResponse> saveMeterReadings(@RequestBody @Valid SaveOrUpdateMeterReadingRequest 
			saveMeterReadingRequest, BindingResult errors);
	
	/** getMeterReadings operation fetches customer meter readings data from the system.
	 * @param meterId
	 * @param month
	 * @return
	 */
	@ApiOperation(value="Fetch Customer Data",notes="This API fetches Meter readings from the system")
	@RequestMapping(value = "/fetch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<GetMeterReadingsResponse> getMeterReadings(@RequestParam(value = "meterId", required = true) String meterId,@RequestParam(value = "month", required = true) String month);

	/**removeMeterReadings operation removes customer meter reading data from the system.
	 * @param removeMeterReadings
	 * @return
	 */
	@ApiOperation(value="Remove Customer Data",notes="This API removes Meter readings from the system")
	@RequestMapping(value = "/evict", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RemoveMeterReadingResponse> removeMeterReadings(@RequestBody @Valid RemoveMeterReadingsRequest removeMeterReadings);
	
	/**updateMeteringDataConsumption operation updates customer meter readings from the system.
	 * @param updateMeterReadings
	 * @param errors
	 * @return
	 */
	@ApiOperation(value="Update Customer Data",notes="This API updates Meter Readings in the system")
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SaveOrUpdateMeterReadingResponse> updateMeteringDataConsumptionReadings(@RequestBody @Valid SaveOrUpdateMeterReadingRequest 
			updateMeterReadings, BindingResult errors);

}
