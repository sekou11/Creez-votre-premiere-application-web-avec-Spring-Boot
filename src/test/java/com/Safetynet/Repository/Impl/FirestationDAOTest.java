package com.Safetynet.Repository.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Safetynet.Data.FireStationDAOTestData;
import com.Safetynet.Data.GeneralData;
import com.Safetynet.Exceptions.CustomExceptions.FirestationAlreadyExistsException;
import com.Safetynet.Exceptions.CustomExceptions.FirestationNotFoundByAddressException;
import com.Safetynet.Model.Data;
import com.Safetynet.Model.Firestations;
import com.Safetynet.Utils.Impl.Dataloader;

@SpringBootTest
class FirestationDAOTest {
	@Autowired
	FirestationDAO firestationDAO ;
	
	@Autowired
	Dataloader dataloader;
	
	@BeforeEach
	public void setUp() {
		Data dataTest = new Data(GeneralData.getPersonList(), GeneralData.getFirestationList(),
				GeneralData.getMedicalRecordsList());
		((Dataloader) dataloader).setDataFile(dataTest);

	}
	
	@Test
	void testFindByNumber() {
		//GIVEN
		Integer number=3;
		Firestations expected = new Firestations("1509 Culver St", 3);
		
		//WHEN
		Firestations actual =firestationDAO.findByNumber(number);
		//THEN
		
		
		assertEquals(expected, actual);
	}

	 @Test
	    public void addFirestationTest(){
	        //Given
	        Firestations firestationToAdd = FireStationDAOTestData.firestationToAdd();
	        List<Firestations> expected = FireStationDAOTestData.getFirestationsListWithAddedStation();
	        //When
	        firestationDAO.addFirestations(firestationToAdd);
	        //Then
	        assertEquals(expected,firestationDAO.getFirestationsList());
	    }
	

	    @Test
	    public void addAlreadyInFirestationTest(){
	        //Given
	        Firestations alreadyInFirestations = new Firestations("1509 Culver St", 3);
	        //When & Then
	        assertThrows(FirestationAlreadyExistsException.class, ()->firestationDAO.addFirestations(alreadyInFirestations));
	    }

	    @Test
	    public void editFirestationTest(){
	        //Given
	        Firestations firestationToEdit = FireStationDAOTestData.firestationToEdit();
	        List<Firestations> expected = FireStationDAOTestData.getFirestationsListWithEditedStation();
	        //When
	        firestationDAO.editFirestations(firestationToEdit);
	        //Then
	        assertEquals(expected,firestationDAO.getFirestationsList());
	    }

	    @Test
	    public void firestationNotFoundEditTest(){
	        //Given
	        Firestations firestationNotInList = FireStationDAOTestData.firestationToAdd();
	        //When & Then
	        assertThrows(FirestationNotFoundByAddressException.class, () -> firestationDAO.editFirestations(firestationNotInList));
	    }

	    @Test
	    public void deleteFirestationTest(){
	        //Given
	        Firestations firestationToDelete = FireStationDAOTestData.firestationToDelete();
	        List<Firestations> expected = FireStationDAOTestData.getFirestationsListWithDeletedStation();
	        //When
	        firestationDAO.deleteFirestations(firestationToDelete);
	        //Then
	        assertEquals(expected,firestationDAO.getFirestationsList());
	    }

	    @Test
	    public void firestationNotFoundDeleteTest(){
	        //Given
	        Firestations firestationNotInList = FireStationDAOTestData.firestationToAdd();
	        //When & Then
	        assertThrows(FirestationNotFoundByAddressException.class, () -> firestationDAO.deleteFirestations(firestationNotInList));
	    }

}
