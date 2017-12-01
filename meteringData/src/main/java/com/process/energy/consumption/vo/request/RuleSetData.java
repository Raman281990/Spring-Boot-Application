package com.process.energy.consumption.vo.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RuleSetData implements Serializable{
	
	private static final long serialVersionUID = 2229962312179715294L;
	
	private String profile;
	private String month;
	private Double fraction;
	
}
