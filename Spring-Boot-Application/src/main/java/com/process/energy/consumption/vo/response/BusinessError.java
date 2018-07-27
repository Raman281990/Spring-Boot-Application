package com.process.energy.consumption.vo.response;

/**BusinessError class provides error message.
 * 
 * @author GAGAN
 *
 */
public class BusinessError {
	
	public BusinessError(String message) {
		super();
		this.message = message;
	}

	private String message;
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
}