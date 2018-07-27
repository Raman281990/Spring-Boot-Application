package com.process.energy.consumption.vo.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MeterCacheData implements Serializable{

	private static final long serialVersionUID = -8763378923000039000L;
	
	private String profile;
	
	private Double meteringData;
	
	private Double consumption;

}
