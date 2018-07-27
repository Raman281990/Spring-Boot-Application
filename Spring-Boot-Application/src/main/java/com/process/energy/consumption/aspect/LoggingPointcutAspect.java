package com.process.energy.consumption.aspect;

import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author GAGAN
 * 
 * Is a logging aspect for logging the entrance and exits of methods in energy consumption application
 */
@Slf4j
@Aspect
@Component
public class LoggingPointcutAspect {
	
	/** The Constant ENTERING_LOGS. */
    public static final String ENTERING_LOGS = "Entering Class ";

    /** The Constant EXITING_lOGS. */
    public static final String EXITING_lOGS = "Exiting Class ";

    
    /**
     * Following is the definition for a pointcut to select all the methods
     * available in all the controllers.
     */
    @Pointcut("within(com.process.energy.consumption.controller..*)")
    private void selectAllControllers() {
    }

    /**
     * Following is the definition for a pointcut to select all validators
     * available in the project
     */
    @Pointcut("within(com.process.energy.consumption.annotation.valid..*)")
    private void selectAllValidators() {
    }

    /**
     * Following is the definition for a pointcut to select all the methods
     * available in energy consumption services
     */
    @Pointcut("within(com.process.energy.consumption.cache.service..*)")
    private void selectAllServices() {
    }

   
   @Before("selectAllControllers() || selectAllValidators() || selectAllServices()")
   public void logAdvice(JoinPoint jp) {
       log.info(ENTERING_LOGS + jp.getTarget().getClass() + jp.getSignature().getName());
   }

   /**
    * Info Exit logs for all rest apis and service layer.
    *
    * @param jp
    *            the join point
    */
   @After("selectAllControllers() || selectAllValidators() || selectAllServices()")
   public void afterAdvice(JoinPoint jp) {
       log.info(EXITING_lOGS + jp.getTarget().getClass() + jp.getSignature().getName());
   }    

}

