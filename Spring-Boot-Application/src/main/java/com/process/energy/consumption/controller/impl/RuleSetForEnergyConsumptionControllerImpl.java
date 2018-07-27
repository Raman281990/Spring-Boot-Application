package com.process.energy.consumption.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.process.energy.consumption.cache.RuleSetCacheService;
import com.process.energy.consumption.constants.ErrorConstants;
import com.process.energy.consumption.controller.RuleSetForEnergyConsumptionController;
import com.process.energy.consumption.vo.request.CreateOrUpdateRuleSetRequest;
import com.process.energy.consumption.vo.request.FetchRuleSetRequest;
import com.process.energy.consumption.vo.response.BusinessError;
import com.process.energy.consumption.vo.response.CreateOrUpdateRuleSetResponse;
import com.process.energy.consumption.vo.response.FetchRuleSetResponse;
import com.process.energy.consumption.vo.response.RemoveRuleSetResponse;

/**
 * @author Raman
 * 
 * RuleSetForEnergyConsumptionControllerImpl class
 * 
 * Contains CRUD operations for Rule set in order to calculate energy consumption based on customer data.
 *
 */
@Service
public class RuleSetForEnergyConsumptionControllerImpl implements RuleSetForEnergyConsumptionController{

	
	@Autowired
	RuleSetCacheService ruleSetCacheService;
	
	/* (non-Javadoc)
	 * @see com.process.energy.consumption.controller.RuleSetForEnergyConsumptionController#createRuleSetForEnergyConsumption(com.process.energy.consumption.model.CreateOrUpdateRuleSetRequest, org.springframework.validation.BindingResult)
	 *   createRuleSetForEnergyConsumption class is use to create the rule set for calculation of energy consumption.
	 */
	
	@Override
	public ResponseEntity<CreateOrUpdateRuleSetResponse> saveRuleSetForEnergyConsumption(@RequestBody @Valid
			CreateOrUpdateRuleSetRequest createRuleSetRequest, BindingResult errors) {
		
		if(errors.hasErrors()) {
			BusinessError br = new BusinessError(ErrorConstants.INVALID_PROFILE_DATA);
			return new ResponseEntity<>(new CreateOrUpdateRuleSetResponse(false,br),HttpStatus.BAD_REQUEST);
		}
		else if(ruleSetCacheService.addRuleSetIntoCache(createRuleSetRequest)) {
			return new ResponseEntity<>(new CreateOrUpdateRuleSetResponse(true),HttpStatus.OK);
		}
		 return new ResponseEntity<>(new CreateOrUpdateRuleSetResponse(false),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/* (non-Javadoc)
	 * @see com.process.energy.consumption.controller.RuleSetForEnergyConsumptionController#fetchRuleSetForEnergyConsumption(com.process.energy.consumption.model.FetchRuleSetRequest)
	*   fetchRuleSetForEnergyConsumption class fetches rule set based on Profile and month and retrive corresponding fraction value.
	 */
	@Override
	public ResponseEntity<FetchRuleSetResponse> fetchRuleSetForEnergyConsumption(@RequestParam String profile,@RequestParam String month) {
		
		FetchRuleSetRequest fetchRuleSetRequest = buildRequestForFetchingRuleSet(profile, month);
		String fraction = ruleSetCacheService.fetchRuleSetFromCache(fetchRuleSetRequest);
		FetchRuleSetResponse ruleAndFractionDataResponse = new FetchRuleSetResponse();
		if(null!=fraction) {
			ruleAndFractionDataResponse.setFraction(fraction);
			return new ResponseEntity<>(ruleAndFractionDataResponse,HttpStatus.OK);
		}
		
		BusinessError br = new BusinessError(ErrorConstants.NO_DATA_FOUND);
		ruleAndFractionDataResponse.setErrors(br);
		return new ResponseEntity<>(ruleAndFractionDataResponse,HttpStatus.NO_CONTENT);
	}

	

	/* (non-Javadoc)
	 * @see com.process.energy.consumption.controller.RuleSetForEnergyConsumptionController#removeRuleSetForEnergyConsumption()
	 *  removeRuleSetForEnergyConsumption class removes Rule set for calculation of energy consumption
	 */
	@Override
	public ResponseEntity<RemoveRuleSetResponse> removeRuleSetForEnergyConsumption() {
		
		if(ruleSetCacheService.evictRuleSetFromCache()) {
			return new ResponseEntity<>(new RemoveRuleSetResponse(true),HttpStatus.OK);
		}
		
		return new ResponseEntity<>(new RemoveRuleSetResponse(Boolean.FALSE),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/* (non-Javadoc)
	 * @see com.process.energy.consumption.controller.RuleSetForEnergyConsumptionController#updateRuleSetForEnergyConsumption(com.process.energy.consumption.model.CreateOrUpdateRuleSetRequest, org.springframework.validation.BindingResult)
	 *  updateRuleSetForEnergyConsumption class updates rule set for energy consumption calculation
	 */
	@Override
	public ResponseEntity<CreateOrUpdateRuleSetResponse> updateRuleSetForEnergyConsumption(
			CreateOrUpdateRuleSetRequest updateRuleSet, BindingResult errors) {
		
		if(errors.hasErrors()) {
			BusinessError br = new BusinessError(ErrorConstants.INVALID_PROFILE_DATA);
			return new ResponseEntity<>(new CreateOrUpdateRuleSetResponse(Boolean.FALSE,br),HttpStatus.BAD_REQUEST);
		}
		if(ruleSetCacheService.addRuleSetIntoCache(updateRuleSet)) {
			return new ResponseEntity<>(new CreateOrUpdateRuleSetResponse(Boolean.TRUE),HttpStatus.OK);
		}
		 return new ResponseEntity<>(new CreateOrUpdateRuleSetResponse(Boolean.FALSE),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	/**buildRequestForFetchingRuleSet build request for fetching Rule Set from Cache
	 * @param profile
	 * @param month
	 * @return
	 */
	private FetchRuleSetRequest buildRequestForFetchingRuleSet(String profile, String month) {
		FetchRuleSetRequest fetchRuleSetRequest = new FetchRuleSetRequest();
		fetchRuleSetRequest.setMonth(month);
		fetchRuleSetRequest.setProfile(profile);
		return fetchRuleSetRequest;
	}

}