package com.process.energy.consumption.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import springfox.documentation.spring.web.plugins.Docket;

@RunWith(MockitoJUnitRunner.class)
public class SwaggerConfigTest {
	
	@InjectMocks
	SwaggerConfig swaggerConfig;
	
	@Test
	public void testAPI(){
		
		Docket response = swaggerConfig.api();
		Assert.assertNotNull(response);
		
	}

}
