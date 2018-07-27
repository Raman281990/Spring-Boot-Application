package com.process.energy.consumption.vo.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RemoveRuleSetResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7424650081797777874L;
	
	private boolean success;
	
	private BusinessError errors;
	
	public RemoveRuleSetResponse(boolean success) {
		this.success = success;
	}

}
