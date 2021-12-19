package com.Safetynet.Service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Safetynet.Data.GeneralData;
import com.Safetynet.Exceptions.CustomExceptions.FirestationNotFoundByAddressException;
import com.Safetynet.Exceptions.CustomExceptions.FirestationNotFoundExceptions;
import com.Safetynet.Model.Data;
import com.Safetynet.Model.Firestations;
import com.Safetynet.Utils.Impl.Dataloader;
@SpringBootTest
class FirestationServiceTest {

	@Autowired
	FirestationService firestationService;

	@Autowired
	Dataloader dataloader;

	private Integer firestationNumberUnknown;

	@BeforeEach
	public void setUp() {
		Data dataTest = new Data(GeneralData.getPersonList(), GeneralData.getFirestationList(),
				GeneralData.getMedicalRecordsList());
		((Dataloader) dataloader).setDataFile(dataTest);
	}

	@Test
	void testFindAll() {
		// GIVEN
		  List<Firestations>expected = GeneralData.getFirestationList();
		// WHEN
		List<Firestations> result =firestationService.findAll();
		// THEN
		assertEquals(expected, result);
	}

	@Test
	void testFindAddressByNumber() {
		// GIVEN
		Integer firestationNumber=3;
		String expected = "1509 Culver St";
		// WHEN
		String result= firestationService.findAddressByNumber(firestationNumber);
		// THEN
		assertEquals(expected, result);
	}
	@Test
	void testFindAddressByNumberUnknownThrowExcecptions() {
		// GIVEN
		Integer firestationNumberUnknown=100;
		
		// WHEN && THEN
		assertThrows(FirestationNotFoundExceptions.class,() ->firestationService.findAddressByNumber(firestationNumberUnknown));
	}

	@Test
	void testFindNumberByAddress() {
		// GIVEN
		String address="29 15th St";
		Integer expected=2;
		
		// WHEN
		Integer result =firestationService.findNumberByAddress(address);
		// THEN
		assertEquals(expected, result);
	}
	
	@Test
	void testFindNumberByUnknownAddressThrowExcecptions() {
		// GIVEN
		String addressUnknown="addressUnknown";
		
		// WHEN && THEN
		assertThrows(FirestationNotFoundByAddressException.class,() ->firestationService.findNumberByAddress(addressUnknown));
	}

}
