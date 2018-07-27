package com.process.energy.consumption.vo.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RemoveMeterReadingsRequest implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1997346706180138752L;
	
	private String meterId;
	
	private String month;
	

}
