package com.Safetynet.Service.Impl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Safetynet.Data.AlertsServiceTestData;
import com.Safetynet.Data.GeneralData;
import com.Safetynet.Model.Data;
import com.Safetynet.Model.Specific.ChildAlert;
import com.Safetynet.Model.Specific.Fire;
import com.Safetynet.Model.Specific.Flood;
import com.Safetynet.Model.Specific.ListByFirestation;
import com.Safetynet.Model.Specific.utils.FullInfoPerson;
import com.Safetynet.Utils.Impl.Dataloader;

@SpringBootTest
class AlertsServiceTest {

	@Autowired
	AlertsService alertsService;

	@Autowired
	Dataloader dataloader;

	@BeforeEach
	public void setUp() {
		Data dataTest = new Data(GeneralData.getPersonList(), GeneralData.getFirestationList(),
				GeneralData.getMedicalRecordsList());
		((Dataloader) dataloader).setDataFile(dataTest);
	}

	@Test
	void testGetEmailListByCity() {
		 //Given
        String city = "Culver";
        List<String> expected = AlertsServiceTestData.getEmailsFromCityList();
        List<String> result;
        //When
        result = alertsService.getEmailListByCity(city);
        //Then
        assertEquals(expected,result);
	}

	@Test
	void testGetPersonsListByFirestation() {
		// GIVEN
		Integer firestation = 3;
		ListByFirestation expected = AlertsServiceTestData.getPersonsListByFirestation();
		// WHEN

		ListByFirestation result = alertsService.getPersonsListByFirestation(firestation);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	void testGetChildsAndAdultsByAddress() {
		// GIVEN

		String address = "1509 Culver St";
		ChildAlert expected = AlertsServiceTestData.getChildAlertData();
		// WHEN
	ChildAlert result = alertsService.getChildsAndAdultsByAddress(address);
	// THEN
		assertEquals(expected, result);
	}

	@Test
	void testGetAllPhonesByFirestationNumber() {
		// GIVEN
		Integer firestationNumber=3;
		List<String> expected = AlertsServiceTestData.getPhoneListFromFirestationNumber();
		// WHEN
		
		List<String> actual =alertsService.getAllPhonesByFirestationNumber(firestationNumber);
		// THEN
		assertEquals(expected, actual);
		
		
	}

	@Test
	void testGetPersonByAddress() {
		String address="1509 Culver St";
		Fire expected = AlertsServiceTestData.getFiredAlertData();
		// GIVEN
		// WHEN
		Fire actual = alertsService.getPersonByAddress(address);
		// THEN
		assertEquals(expected, actual);
	}
	@Test
	void testGetPersonsAndAddressByFirestationNumber() {
		//Given
        List<Integer> firestationNumberList = new ArrayList<>(Arrays.asList(2,3));
    List<Flood> expected = AlertsServiceTestData.getFloodDataList();
       //When
        List<Flood> result = alertsService.getPersonsAndAddressByFirestationNumber(firestationNumberList);
        //Then
        assertEquals(expected,result);
	}

	
	 
	

	@Test
	void testGetFullInfoPersonByName() {
		// GIVEN
		String firstName="John";
		String lastName="Boyd";
		List<FullInfoPerson>expected = AlertsServiceTestData.getFullInfoPersonByNameList();
		// WHEN
		List<FullInfoPerson>actual = alertsService.getFullInfoPersonByName(firstName, lastName);
		// THEN
		assertEquals(expected, actual);
	}

}
