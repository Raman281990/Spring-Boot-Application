package com.process.energy.consumption.vo.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetMeterReadingsRequest implements Serializable {

	
	private static final long serialVersionUID = 4739883314832025296L;
	
	private String meterId;
	
	private String month;

	
}


