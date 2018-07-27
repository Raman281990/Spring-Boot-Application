package com.process.energy.consumption.annotations;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.process.energy.consumption.validator.MeterReadingsValidator;

/**
 * @author GAGAN
 * ValidMeterReadings is custom annotation for validating meter readings
 */
@Constraint(validatedBy = { MeterReadingsValidator.class })
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
@Documented
public @interface ValidMeterReadings {
	

	String message() default "INVALID_METERING_DATA";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}