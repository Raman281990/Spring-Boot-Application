package com.process.energy.consumption.vo.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class EnergyConsumptionResponse implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5312709041204445448L;

	private Double consumption;
	
	private String message;

}
