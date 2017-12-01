package com.process.energy.consumption.vo.response;

import java.io.Serializable;

public class FetchRuleSetResponse implements Serializable{

	

	public FetchRuleSetResponse(String fraction, BusinessError errors) {
		super();
		this.fraction = fraction;
		this.errors = errors;
	}

	public FetchRuleSetResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3153297518724510388L;
	
	
	private String fraction;
	
	private BusinessError errors;
	
	public String getFraction() {
		return fraction;
	}

	public void setFraction(String fraction) {
		this.fraction = fraction;
	}

	public BusinessError getErrors() {
		return errors;
	}

	public void setErrors(BusinessError errors) {
		this.errors = errors;
	}
	

}

