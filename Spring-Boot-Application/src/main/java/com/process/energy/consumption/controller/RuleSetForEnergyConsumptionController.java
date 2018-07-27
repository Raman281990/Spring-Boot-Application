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

import com.process.energy.consumption.vo.request.CreateOrUpdateRuleSetRequest;
import com.process.energy.consumption.vo.request.FetchRuleSetRequest;
import com.process.energy.consumption.vo.response.CreateOrUpdateRuleSetResponse;
import com.process.energy.consumption.vo.response.FetchRuleSetResponse;
import com.process.energy.consumption.vo.response.RemoveRuleSetResponse;

import io.swagger.annotations.ApiOperation;


/**
 * @author Raman
 * 
 * RuleSetForEnergyConsumptionController Interface
 * 
 * Contains functions for the CRUD operations on Rule Set Data.
 * 1) Save Rule Set
 * 2) Fetch Rule Set
 * 3) Delete Rule Set
 * 4) Update Rule Set
 *
 */
@RestController
@RequestMapping("/ruleSet")
public interface RuleSetForEnergyConsumptionController {
	
	/**saveRuleSetForEnergyConsumption API saves rule set in the system for calculation of energy consumption.
	 * @param createRuleSetRequest
	 * @param errors
	 * @return
	 */
	@ApiOperation(value="Save Rule Set",notes="This API saves rule set in the system for calculation of energy consumption")
	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateOrUpdateRuleSetResponse> saveRuleSetForEnergyConsumption(@RequestBody @Valid CreateOrUpdateRuleSetRequest 
			createRuleSetRequest, BindingResult errors);
	
	/**fetchRuleSetForEnergyConsumption API fetches Rule set information from the system based on profile and month data.
	 * @param profile
	 * @param month
	 * @return
	 */
	@ApiOperation(value="Read Rule Set",notes="This API fetches rule set in the system for calculation of energy consumption")
	@RequestMapping(value = "/read", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FetchRuleSetResponse> fetchRuleSetForEnergyConsumption(@RequestParam(value = "profile", required = true) String profile,
			@RequestParam(value = "month", required = true) String month);

	/**removeRuleSetForEnergyConsumption API removes Rule Set information.
	 * @return
	 */
	@ApiOperation(value="Delete Rule Set",notes="This API deletes rule set in the system for calculation of energy consumption")
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RemoveRuleSetResponse> removeRuleSetForEnergyConsumption();
	
	/**updateRuleSetForEnergyConsumption API updates Rule Set information in the system for calculation of energy consumption.
	 * @param updateRuleSet
	 * @param errors
	 * @return
	 */
	@ApiOperation(value="Update Rule Set",notes="This API updates rule set in the system for calculation of energy consumption")
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateOrUpdateRuleSetResponse> updateRuleSetForEnergyConsumption(@RequestBody @Valid CreateOrUpdateRuleSetRequest 
			updateRuleSet, BindingResult errors);

}
