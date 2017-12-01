package com.process.energy.consumption.vo.request;

import java.io.Serializable;
import java.util.List;

import com.process.energy.consumption.annotations.ValidMeterReadings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ValidMeterReadings
@Getter
@Setter
@NoArgsConstructor
public class SaveOrUpdateMeterReadingRequest implements Serializable{

	private static final long serialVersionUID = -6608189635177457679L;
	
	private List<MeterReadingData> meterReadingData;

}


