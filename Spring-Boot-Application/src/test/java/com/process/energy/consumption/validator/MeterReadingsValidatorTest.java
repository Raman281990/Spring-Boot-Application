package com.process.energy.consumption.validator;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.guava.GuavaCache;

import com.process.energy.consumption.config.CacheConfig;
import com.process.energy.consumption.vo.request.MeterReadingData;
import com.process.energy.consumption.vo.request.SaveOrUpdateMeterReadingRequest;

@RunWith(MockitoJUnitRunner.class)
public class MeterReadingsValidatorTest {
	
	@InjectMocks
	MeterReadingsValidator  meterReadingsValidator;
	
	@Mock
	CacheConfig cacheConfig;
	
	
	@Test
	public void validateMeterReadings() {
		
		SaveOrUpdateMeterReadingRequest saveOrUpdateMeterReadingRequest = new SaveOrUpdateMeterReadingRequest();
		
		List<MeterReadingData> listOfMeterReadings = new ArrayList<MeterReadingData>();
		MeterReadingData meterReadingData = new MeterReadingData();
		meterReadingData.setMeterId("0001");
		meterReadingData.setMeterReading(10000.00);
		meterReadingData.setMonth("JAN");
		meterReadingData.setProfile("A");
		
		MeterReadingData meterReadingData2 = new MeterReadingData();
		meterReadingData2.setMeterId("0001");
		meterReadingData2.setMeterReading(20000.00);
		meterReadingData2.setMonth("FEB");
		meterReadingData2.setProfile("A");
		
		
		MeterReadingData meterReadingData3 = new MeterReadingData();
		meterReadingData3.setMeterId("0001");
		meterReadingData3.setMeterReading(20000.00);
		meterReadingData3.setMonth("MAR");
		meterReadingData3.setProfile("A");
		
		
		MeterReadingData meterReadingData4 = new MeterReadingData();
		meterReadingData4.setMeterId("0001");
		meterReadingData4.setMeterReading(40000.00);
		meterReadingData4.setMonth("APR");
		meterReadingData4.setProfile("A");
		
		MeterReadingData meterReadingData5 = new MeterReadingData();
		meterReadingData5.setMeterId("0001");
		meterReadingData5.setMeterReading(40000.00);
		meterReadingData5.setMonth("JUN");
		meterReadingData5.setProfile("A");
		
		
		MeterReadingData meterReadingData6 = new MeterReadingData();
		meterReadingData6.setMeterId("0001");
		meterReadingData6.setMeterReading(50000.00);
		meterReadingData6.setMonth("JUL");
		meterReadingData6.setProfile("A");
		
		MeterReadingData meterReadingData7 = new MeterReadingData();
		meterReadingData7.setMeterId("0001");
		meterReadingData7.setMeterReading(60000.00);
		meterReadingData7.setMonth("AUG");
		meterReadingData7.setProfile("A");
		

		MeterReadingData meterReadingData8 = new MeterReadingData();
		meterReadingData8.setMeterId("0001");
		meterReadingData8.setMeterReading(70000.00);
		meterReadingData8.setMonth("SEP");
		meterReadingData8.setProfile("A");
		
		MeterReadingData meterReadingData9 = new MeterReadingData();
		meterReadingData9.setMeterId("0001");
		meterReadingData9.setMeterReading(100000.00);
		meterReadingData9.setMonth("DEC");
		meterReadingData9.setProfile("A");
		
		
		MeterReadingData meterReadingData10 = new MeterReadingData();
		meterReadingData10.setMeterId("0001");
		meterReadingData10.setMeterReading(90000.00);
		meterReadingData10.setMonth("OCT");
		meterReadingData10.setProfile("A");
		
		MeterReadingData meterReadingData11= new MeterReadingData();
		meterReadingData11.setMeterId("0001");
		meterReadingData11.setMeterReading(95000.00);
		meterReadingData11.setMonth("NOV");
		meterReadingData11.setProfile("A");
		
		listOfMeterReadings.add(meterReadingData);
		listOfMeterReadings.add(meterReadingData2);
		listOfMeterReadings.add(meterReadingData3);
		listOfMeterReadings.add(meterReadingData4);
		listOfMeterReadings.add(meterReadingData5);
		listOfMeterReadings.add(meterReadingData6);
		listOfMeterReadings.add(meterReadingData7);
		listOfMeterReadings.add(meterReadingData8);
		listOfMeterReadings.add(meterReadingData9);
		listOfMeterReadings.add(meterReadingData10);
		listOfMeterReadings.add(meterReadingData11);
		
		
		saveOrUpdateMeterReadingRequest.setMeterReadingData(listOfMeterReadings);
		
		ConstraintValidatorContext constraintValidatorContext  =Mockito.mock(ConstraintValidatorContext.class);
		
		ConstraintViolationBuilder builder = Mockito.mock(ConstraintViolationBuilder.class);
		
		Mockito.when(constraintValidatorContext.buildConstraintViolationWithTemplate(Mockito.anyString())).thenReturn(builder);
		ConstraintValidatorContext context = Mockito.mock(ConstraintValidatorContext.class);
		
		
		Mockito.when(builder.addConstraintViolation()).thenReturn(context);
		
        GuavaCache cache = Mockito.mock(GuavaCache.class);
		
		CacheManager cacheManager =Mockito.mock(CacheManager.class);
		
		 Mockito.when(cacheConfig.cacheManager()).thenReturn(cacheManager);
		
		Mockito.when(cacheConfig.cacheManager().getCache(Mockito.anyString())).thenReturn(cache);
		
		ValueWrapper valueWrapper = Mockito.mock(ValueWrapper.class);
	
		Mockito.when(cache.get(Mockito.anyString())).thenReturn(valueWrapper);
		Mockito.when(valueWrapper.get()).thenReturn(new Double(0.4));
		Boolean response = meterReadingsValidator.isValid(saveOrUpdateMeterReadingRequest,
				constraintValidatorContext);
		Assert.assertFalse(response);
		
	}
	
	

}
