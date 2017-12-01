package com.process.energy.consumption.vo.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RemoveMeterReadingResponse implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1907455784955891177L;
	
	private boolean success;

}
