package com.process.energy.consumption.vo.request;

import java.io.Serializable;
import java.util.List;

import com.process.energy.consumption.annotations.ValidRuleSet;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@ValidRuleSet
public class CreateOrUpdateRuleSetRequest implements Serializable{
	
	private static final long serialVersionUID = -3327373449431670958L;
	
	private List<RuleSetData> ruleSetData;
	

}
