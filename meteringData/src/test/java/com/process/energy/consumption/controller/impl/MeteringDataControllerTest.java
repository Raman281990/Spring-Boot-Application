package com.process.energy.consumption.controller.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.process.energy.consumption.cache.MeterReadingsCacheService;
import com.process.energy.consumption.vo.request.GetMeterReadingsRequest;
import com.process.energy.consumption.vo.request.MeterCacheData;
import com.process.energy.consumption.vo.request.MeterReadingData;
import com.process.energy.consumption.vo.request.RemoveMeterReadingsRequest;
import com.process.energy.consumption.vo.request.SaveOrUpdateMeterReadingRequest;
import com.process.energy.consumption.vo.response.GetMeterReadingsResponse;
import com.process.energy.consumption.vo.response.RemoveMeterReadingResponse;
import com.process.energy.consumption.vo.response.SaveOrUpdateMeterReadingResponse;


@RunWith(MockitoJUnitRunner.class)
public class MeteringDataControllerTest {
	
	
	@InjectMocks
	MeterReadingsControllerImpl meteringDataControllerImpl;
	
	@Mock
	MeterReadingsCacheService meteringDataCacheService;
	
	
	@Test
	public void testCreateMeteringDataForCustomerForSuceessScenario() {
		
		Mockito.when(meteringDataCacheService.addOrUpdateMeteringReadings(
				Mockito.any(SaveOrUpdateMeterReadingRequest.class))).thenReturn(true);
		
		SaveOrUpdateMeterReadingRequest createMeteringDataRequest = new SaveOrUpdateMeterReadingRequest();
		List<MeterReadingData> listOfMeteringData = new ArrayList<MeterReadingData>();
		
		MeterReadingData meterReadingData = new MeterReadingData();
		meterReadingData.setProfile("A");
		meterReadingData.setMonth("JAN");
		meterReadingData.setMeterId("0001");
		meterReadingData.setMeterReading(1000.00);
		
		listOfMeteringData.add(meterReadingData);
		createMeteringDataRequest.setMeterReadingData(listOfMeteringData);
		
		BindingResult mocked = Mockito.mock(BindingResult.class); 
		ResponseEntity<SaveOrUpdateMeterReadingResponse> reponse = meteringDataControllerImpl.saveMeterReadings(createMeteringDataRequest, mocked);
		Assert.notNull(reponse);
		Assert.isNull(reponse.getBody().getErrors());
		
	}
	
	
	@Test
	public void testCreateMeteringDataForCustomerForFailureOfCache() {
		
		Mockito.when(meteringDataCacheService.addOrUpdateMeteringReadings(
				Mockito.any(SaveOrUpdateMeterReadingRequest.class))).thenReturn(false);
		
		SaveOrUpdateMeterReadingRequest createMeteringDataRequest = new SaveOrUpdateMeterReadingRequest();
		List<MeterReadingData> listOfMeteringData = new ArrayList<MeterReadingData>();
		
		MeterReadingData meterReadingData = new MeterReadingData();
		meterReadingData.setProfile("A");
		meterReadingData.setMonth("JAN");
		meterReadingData.setMeterId("0001");
		meterReadingData.setMeterReading(1000.00);
		
		listOfMeteringData.add(meterReadingData);
		createMeteringDataRequest.setMeterReadingData(listOfMeteringData);
		
		BindingResult mocked = Mockito.mock(BindingResult.class); 
		ResponseEntity<SaveOrUpdateMeterReadingResponse> reponse = meteringDataControllerImpl.saveMeterReadings(createMeteringDataRequest, mocked);
		Assert.notNull(reponse);
		Assert.notNull(reponse.getStatusCode().INTERNAL_SERVER_ERROR);
		
	}
	
	@Test
	public void removeMeterReadings() {
		
		Mockito.when(meteringDataCacheService
				.removeMeterReadingsFromCache(Mockito.any(RemoveMeterReadingsRequest.class))).thenReturn(true);
		
		
		RemoveMeterReadingsRequest removeMeterReadingsRequest = new RemoveMeterReadingsRequest();
		removeMeterReadingsRequest.setMeterId("0001");
		removeMeterReadingsRequest.setMonth("JAN");
		ResponseEntity<RemoveMeterReadingResponse> response = meteringDataControllerImpl.removeMeterReadings(removeMeterReadingsRequest);
		Assert.isTrue(response.getBody().isSuccess());
		
	}
	
	@Test
	public void removeMeterReadingsFailureScenario() {
		
		Mockito.when(meteringDataCacheService
				.removeMeterReadingsFromCache(Mockito.any(RemoveMeterReadingsRequest.class))).thenReturn(false);
		
		
		RemoveMeterReadingsRequest removeMeterReadingsRequest = new RemoveMeterReadingsRequest();
		removeMeterReadingsRequest.setMeterId("0001");
		removeMeterReadingsRequest.setMonth("JAN");
		ResponseEntity<RemoveMeterReadingResponse> response = meteringDataControllerImpl.removeMeterReadings(removeMeterReadingsRequest);
		Assert.notNull(response);
		Assert.notNull(response.getStatusCode().INTERNAL_SERVER_ERROR);
		
	}
	
	
	@Test
	public void testUpdateMeteringDataForCustomerForSuceessScenario() {
		
		Mockito.when(meteringDataCacheService.addOrUpdateMeteringReadings(
				Mockito.any(SaveOrUpdateMeterReadingRequest.class))).thenReturn(true);
		
		SaveOrUpdateMeterReadingRequest createMeteringDataRequest = new SaveOrUpdateMeterReadingRequest();
		List<MeterReadingData> listOfMeteringData = new ArrayList<MeterReadingData>();
		
		MeterReadingData meterReadingData = new MeterReadingData();
		meterReadingData.setProfile("A");
		meterReadingData.setMonth("JAN");
		meterReadingData.setMeterId("0001");
		meterReadingData.setMeterReading(1000.00);
		
		listOfMeteringData.add(meterReadingData);
		createMeteringDataRequest.setMeterReadingData(listOfMeteringData);
		
		BindingResult mocked = Mockito.mock(BindingResult.class); 
		ResponseEntity<SaveOrUpdateMeterReadingResponse> reponse = meteringDataControllerImpl.updateMeteringDataConsumptionReadings(createMeteringDataRequest, mocked);
		Assert.notNull(reponse);
		Assert.isNull(reponse.getBody().getErrors());
		
	}
	
	@Test
	public void testUpdateMeteringDataForCustomerForFailureOfCacheService() {
		
		Mockito.when(meteringDataCacheService.addOrUpdateMeteringReadings(
				Mockito.any(SaveOrUpdateMeterReadingRequest.class))).thenReturn(false);
		
		SaveOrUpdateMeterReadingRequest createMeteringDataRequest = new SaveOrUpdateMeterReadingRequest();
		List<MeterReadingData> listOfMeteringData = new ArrayList<MeterReadingData>();
		
		MeterReadingData meterReadingData = new MeterReadingData();
		meterReadingData.setProfile("A");
		meterReadingData.setMonth("JAN");
		meterReadingData.setMeterId("0001");
		meterReadingData.setMeterReading(1000.00);
		
		listOfMeteringData.add(meterReadingData);
		createMeteringDataRequest.setMeterReadingData(listOfMeteringData);
		
		BindingResult mocked = Mockito.mock(BindingResult.class); 
		ResponseEntity<SaveOrUpdateMeterReadingResponse> reponse = meteringDataControllerImpl.updateMeteringDataConsumptionReadings(createMeteringDataRequest, mocked);
		Assert.notNull(reponse);
		Assert.notNull(reponse.getStatusCode().INTERNAL_SERVER_ERROR);
		
	}
	
	@Test
	public void testCreateMeteringDataForCustomerValidationErrors() {
		
		Mockito.when(meteringDataCacheService.addOrUpdateMeteringReadings(
				Mockito.any(SaveOrUpdateMeterReadingRequest.class))).thenReturn(true);
		
		SaveOrUpdateMeterReadingRequest createMeteringDataRequest = new SaveOrUpdateMeterReadingRequest();
		List<MeterReadingData> listOfMeteringData = new ArrayList<MeterReadingData>();
		
		MeterReadingData meterReadingData = new MeterReadingData();
		meterReadingData.setProfile("A");
		meterReadingData.setMonth("JAN");
		meterReadingData.setMeterId("0001");
		meterReadingData.setMeterReading(1000.00);
		
		listOfMeteringData.add(meterReadingData);
		createMeteringDataRequest.setMeterReadingData(listOfMeteringData);
		
		BindingResult mocked = Mockito.mock(BindingResult.class); 
		
		Mockito.when(mocked.hasErrors()).thenReturn(true);
		List<ObjectError> listOfError = new ArrayList<ObjectError>();
		listOfError.add(new ObjectError("INVALID DATA", "INVALID DATA"));
		Mockito.when(mocked.getAllErrors()).thenReturn(listOfError);
		
		
		ResponseEntity<SaveOrUpdateMeterReadingResponse> reponse = meteringDataControllerImpl.saveMeterReadings(createMeteringDataRequest, mocked);
		Assert.notNull(reponse);
		Assert.notNull(reponse.getBody().getErrors());
		
	}
	
	@Test
	public void testUpdateMeteringDataForCustomerValidationErrors() {
		
		Mockito.when(meteringDataCacheService.addOrUpdateMeteringReadings(
				Mockito.any(SaveOrUpdateMeterReadingRequest.class))).thenReturn(true);
		
		SaveOrUpdateMeterReadingRequest createMeteringDataRequest = new SaveOrUpdateMeterReadingRequest();
		List<MeterReadingData> listOfMeteringData = new ArrayList<MeterReadingData>();
		
		MeterReadingData meterReadingData = new MeterReadingData();
		meterReadingData.setProfile("A");
		meterReadingData.setMonth("JAN");
		meterReadingData.setMeterId("0001");
		meterReadingData.setMeterReading(1000.00);
		
		listOfMeteringData.add(meterReadingData);
		createMeteringDataRequest.setMeterReadingData(listOfMeteringData);
		
		BindingResult mocked = Mockito.mock(BindingResult.class); 
		
		
		Mockito.when(mocked.hasErrors()).thenReturn(true);
		List<ObjectError> listOfError = new ArrayList<ObjectError>();
		listOfError.add(new ObjectError("INVALID DATA", "INVALID DATA"));
		Mockito.when(mocked.getAllErrors()).thenReturn(listOfError);
		
		ResponseEntity<SaveOrUpdateMeterReadingResponse> reponse = meteringDataControllerImpl.updateMeteringDataConsumptionReadings(createMeteringDataRequest, mocked);
		Assert.notNull(reponse);
		Assert.notNull(reponse.getBody().getErrors());
		
	}
	
	@Test
	public void testCreateMeteringDataForCustomerValidationErrorsAndNoValidData() {
		
		Mockito.when(meteringDataCacheService.addOrUpdateMeteringReadings(
				Mockito.any(SaveOrUpdateMeterReadingRequest.class))).thenReturn(true);
		
		SaveOrUpdateMeterReadingRequest createMeteringDataRequest = new SaveOrUpdateMeterReadingRequest();
		List<MeterReadingData> listOfMeteringData = new ArrayList<MeterReadingData>();
		
		createMeteringDataRequest.setMeterReadingData(listOfMeteringData);
		
		BindingResult mocked = Mockito.mock(BindingResult.class); 
		
		Mockito.when(mocked.hasErrors()).thenReturn(true);
		List<ObjectError> listOfError = new ArrayList<ObjectError>();
		listOfError.add(new ObjectError("INVALID DATA", "INVALID DATA"));
		Mockito.when(mocked.getAllErrors()).thenReturn(listOfError);
		
		
		ResponseEntity<SaveOrUpdateMeterReadingResponse> reponse = meteringDataControllerImpl.saveMeterReadings(createMeteringDataRequest, mocked);
		Assert.notNull(reponse);
		Assert.notNull(reponse.getStatusCode().BAD_REQUEST);
		
	}
	
	
	@Test
	public void testGetMeteringDataForCustomerForSuceessScenario() {
		
		
		MeterCacheData meterCacheData = new MeterCacheData();
		meterCacheData.setMeteringData(1000.00);
		meterCacheData.setProfile("A");
		Mockito.when(meteringDataCacheService.getMeterReadingsFromCache(
				Mockito.any(GetMeterReadingsRequest.class))).thenReturn(meterCacheData);
		
		BindingResult mocked = Mockito.mock(BindingResult.class); 
		ResponseEntity<GetMeterReadingsResponse> reponse = meteringDataControllerImpl.getMeterReadings("0001", "JAN");
		Assert.notNull(reponse);
		Assert.notNull(reponse.getBody().getMeterReading());
		
	}
	
	@Test
	public void testGetMeteringDataNoDataFound() {
		
		
		Mockito.when(meteringDataCacheService.getMeterReadingsFromCache(
				Mockito.any(GetMeterReadingsRequest.class))).thenReturn(null);
		
		
		BindingResult mocked = Mockito.mock(BindingResult.class); 
		ResponseEntity<GetMeterReadingsResponse> reponse = meteringDataControllerImpl.getMeterReadings("0001", "JAN");
		Assert.notNull(reponse);
		Assert.notNull(reponse.getStatusCode().NO_CONTENT);
		
	}
	
	
	
}
