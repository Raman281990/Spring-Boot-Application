package com.process.energy.consumption.vo.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class GetMeterReadingsResponse implements Serializable{
	
	
	private static final long serialVersionUID = -2740715124892823350L;

	private String profile;
	
	private Double meterReading;
	
	private BusinessError businessError;

	
}
