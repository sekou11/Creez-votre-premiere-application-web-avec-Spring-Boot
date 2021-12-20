package com.Safetynet.Service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Safetynet.Model.Firestations;
import com.Safetynet.Model.Person;
import com.Safetynet.Repository.Impl.FirestationDAO;

@SpringBootTest
class CRUDFirestationServiceTest {

	@MockBean
	FirestationDAO firestationDAO;

	@Autowired
	FirestationService firestationService;

	@Test
	void testFindByNumber() {

		// GIVEN
		Integer firestationNumber = 2;
		// WHEN

		when(firestationDAO.findByNumber(firestationNumber)).thenReturn(new Firestations("29 15th St", 2));
		// THEN
		assertEquals(firestationDAO.findByNumber(firestationNumber),
				firestationService.findByNumber(firestationNumber));
	}

	@Test
	void testAddFirestation() {
		// GIVEN
		Firestations firestations = new Firestations("29 15th St", 2);
		// WHEN
		when(firestationDAO.addFirestations(firestations)).thenReturn(firestations);
		// THEN
		assertEquals(firestationDAO.addFirestations(firestations), firestationService.addFirestation(firestations));
	}
	
	@Test
	void testIfAddFireStationwillCalled() {
		
		// GIVEN
		Firestations firestations = new Firestations("29 15th St", 2);
		// WHEN
		firestationService.addFirestation(firestations);
		// THEN
		verify(firestationDAO,Mockito.times(1)).addFirestations(firestations);
	}

	@Test
	void testEditFirestation() {
		// GIVEN
		Firestations firestations = new Firestations("29 15th St", 2);
		// WHEN
		when(firestationDAO.editFirestations(firestations)).thenReturn(firestations);
		// THEN
		assertEquals(firestationDAO.editFirestations(firestations), firestationService.editFirestation(firestations));
	}
	@Test
	void testIfEditFireStationwillCalled() {
		
		// GIVEN
		Firestations firestations = new Firestations("29 15th St", 2);
		// WHEN
		firestationService.editFirestation(firestations);
		// THEN
		verify(firestationDAO,Mockito.times(1)).editFirestations(firestations);
	}

	@Test
	void testDeleteFirestation() {
		// GIVEN
		Firestations firestations = new Firestations("29 15th St", 2);
		// WHEN
		firestationService.deleteFirestation(firestations);
		verify(firestationDAO, Mockito.times(1)).deleteFirestations(firestations);

	}
	
	
	

}
