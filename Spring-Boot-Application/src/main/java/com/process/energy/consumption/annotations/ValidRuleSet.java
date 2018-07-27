package com.process.energy.consumption.annotations;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.process.energy.consumption.constants.ErrorConstants;
import com.process.energy.consumption.validator.RuleSetValidator;


/**
 * @author RAMAN
 * 
 *ValidRuleSet Interface is a  custom annotation for performing  validations on the Rule Set Data.
 *
 */
@Constraint(validatedBy = { RuleSetValidator.class })
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Documented
public @interface ValidRuleSet {

	String message() default ErrorConstants.INVALID_PROFILE_DATA;

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}