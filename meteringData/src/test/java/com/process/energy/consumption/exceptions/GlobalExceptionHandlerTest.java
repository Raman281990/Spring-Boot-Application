package com.process.energy.consumption.exceptions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GlobalExceptionHandlerTest {
	
	@InjectMocks
	GlobalExceptionHandler globalExceptionHandler;
	
	@Test
	public void testNullPointerHandler() {
		
		globalExceptionHandler.handleNullPointerException();
		
		
	}

}
