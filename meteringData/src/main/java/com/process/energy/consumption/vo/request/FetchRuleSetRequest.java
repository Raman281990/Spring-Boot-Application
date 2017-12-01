package com.process.energy.consumption.vo.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class FetchRuleSetRequest implements Serializable {

	private static final long serialVersionUID = -3289880872689362742L;

	private String profile;
	
	private String month;

}

