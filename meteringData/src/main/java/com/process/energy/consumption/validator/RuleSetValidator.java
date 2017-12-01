package com.process.energy.consumption.validator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.process.energy.consumption.annotations.ValidRuleSet;
import com.process.energy.consumption.vo.request.CreateOrUpdateRuleSetRequest;
import com.process.energy.consumption.vo.request.RuleSetData;

import lombok.extern.slf4j.Slf4j;

/**
 * @author GAGAN
 *
 *RuleSetValidator class performs validations on Rule Set Data.
 *
 *1) Sum of fractions for a spepcific profile should be one, else error should be raised.
 */
@Slf4j
@Component
public class RuleSetValidator implements ConstraintValidator<ValidRuleSet, CreateOrUpdateRuleSetRequest> {

	@Override
	public void initialize(
			ValidRuleSet constraintAnnotation) {
	}

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 * isValid functions performs validation on Rule Set Data
	 * Sum of fractions for a particular profile must be 1.
	 */
	@Override
	public boolean isValid(CreateOrUpdateRuleSetRequest request,
			ConstraintValidatorContext context) {
		if (null != request && !CollectionUtils.isEmpty(request.getRuleSetData())) {

			Map<String, List<RuleSetData>> groupedMap = request.getRuleSetData().stream()
					.collect(Collectors.groupingBy(RuleSetData::getProfile));

			for (Map.Entry<String, List<RuleSetData>> entry : groupedMap.entrySet()) {

				log.info("Processing Data for the Profile" + entry.getKey());
				List<RuleSetData> listOfRuleSetData  = entry.getValue();
				double sum = 0.0;
				for(RuleSetData ruleSetData :listOfRuleSetData) {
					sum+= ruleSetData.getFraction();
				}

				if(sum!=1.0){
					return false;
				}
				else {
					return true;
				}

			}

		}
		return false;
	}



}