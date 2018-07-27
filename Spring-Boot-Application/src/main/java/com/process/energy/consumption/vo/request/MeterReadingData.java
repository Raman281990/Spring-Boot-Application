package com.process.energy.consumption.vo.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class MeterReadingData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7553197647744233417L;

	private String meterId;
	
	private String profile;
			
	private String month;
	
	private Double meterReading;
	
	private Double consumption;

}

