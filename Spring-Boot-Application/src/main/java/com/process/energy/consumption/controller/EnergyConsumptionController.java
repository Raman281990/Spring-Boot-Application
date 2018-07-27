package com.process.energy.consumption.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.process.energy.consumption.vo.response.EnergyConsumptionResponse;

import io.swagger.annotations.ApiOperation;

/** EnergyConsumptionController controller provides endpoint for retrieving consumption value based on
 *  Meter readings and Rule Set.
 *  
 * @author GAGAN
 *
 */
@RestController
@RequestMapping("/consumption")
public interface EnergyConsumptionController {
	
	/**retrieveConsumption API retrives Consumption for a month based on meterId and Rule Set information in the system.
	 * @param meterId
	 * @param month
	 * @return
	 */
	@ApiOperation(value="Retrive Consumption Value",notes="This operation is use to retrieve consumption value of a meter for a"
			+ " particluar month based on rule set and customer data")
	@RequestMapping(value = "/retrieve", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EnergyConsumptionResponse> retrieveConsumption( @RequestParam(value = "meterId", required = true) String meterId,
			@RequestParam(value = "month", required = true) String month);

}

