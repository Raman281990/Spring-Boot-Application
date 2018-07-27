package com.process.energy.consumption.vo.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CreateOrUpdateRuleSetResponse implements Serializable {
	

		private static final long serialVersionUID = -7658355027254848510L;
		
		private boolean success;
		
		private BusinessError errors;
		
		public CreateOrUpdateRuleSetResponse(boolean success) {
			this.success = success;
		}
		
}
