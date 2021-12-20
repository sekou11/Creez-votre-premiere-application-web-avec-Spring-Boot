package com.Safetynet.Repository.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Safetynet.Data.GeneralData;
import com.Safetynet.Data.MedicalRecordsDAOTestData;
import com.Safetynet.Exceptions.CustomExceptions.MedicalRecordsAlreadyExistsException;
import com.Safetynet.Exceptions.CustomExceptions.MedicalRecordsNotFoundException;
import com.Safetynet.Model.Data;
import com.Safetynet.Model.MedicalRecords;
import com.Safetynet.Utils.Impl.Dataloader;
@SpringBootTest
class MedicalRecordsDAOTest {
	@Autowired
	Dataloader dataloader;
	@Autowired
	MedicalRecordsDAO medicalRecordsDAO ; 
	
	@BeforeEach
	public void setUp() {
		Data dataTest = new Data(GeneralData.getPersonList(), GeneralData.getFirestationList(), GeneralData.getMedicalRecordsList());
		((Dataloader) dataloader).setDataFile(dataTest);
	}

	@Test
	void testFindByName() {
		//GIVEN
		
		String firstName = "John";
		String lastName = "Boyd";
		MedicalRecords expected= new MedicalRecords("John", "Boyd", "03/06/1984", List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan"));
		//WHEN
		 MedicalRecords result=medicalRecordsDAO.findByName(firstName, lastName);
		//THEN
		assertEquals(expected,result);
	}

	@Test
	void testAddMedicalRecords() {
		//GIVEN
		MedicalRecords medicalRecordsAdd=MedicalRecordsDAOTestData.medicalRecordsToAdd();
		List<MedicalRecords>expected = MedicalRecordsDAOTestData.getMedicalRecordsListWithAddedMedicalRecord();
		
		//WHEN
		medicalRecordsDAO.addMedicalRecords(medicalRecordsAdd);
		//THAT
		assertEquals(expected, medicalRecordsDAO.getMedicalRecordsList());
	}

	@Test
	void testEditMedicalRecords() {
		//GIVEN
				MedicalRecords medicalRecordsEdit=MedicalRecordsDAOTestData.medicalRecordsToEdit();
				List<MedicalRecords>expected = MedicalRecordsDAOTestData.getMedicalRecordsListWithEditedMedicalRecord();
;
				
				//WHEN
				medicalRecordsDAO.editMedicalRecords(medicalRecordsEdit);
				//THAT
				assertEquals(expected, medicalRecordsDAO.getMedicalRecordsList());
	}

	@Test
	void testDeleteMedicalRecords() {
		//GIVEN
		MedicalRecords medicalRecordsDelete=MedicalRecordsDAOTestData.medicalRecordsToDelete();
		List<MedicalRecords>expected = MedicalRecordsDAOTestData.getMedicalRecordsListWithDeletedMedicalRecord();
;
		
		//WHEN
		medicalRecordsDAO.deleteMedicalRecords(medicalRecordsDelete);;
		//THAT
		assertEquals(expected, medicalRecordsDAO.getMedicalRecordsList());
	}
	 @Test
	    public void medicalRecordNotFoundFindByNameTest(){
	        //Given
	        String firstName = "a";
	        String lastName = "b";
	        //When & Then
	        assertThrows(MedicalRecordsNotFoundException.class, () -> medicalRecordsDAO.findByName(firstName,lastName));
	    }
	 @Test
	    public void addAlreadyInMedicalRecordTest(){
	        //Given
	       MedicalRecords alreadyInMedicalRecord =  new MedicalRecords("John", "Boyd", "03/06/1984", List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan"));
	        //When & Then
	        assertThrows(MedicalRecordsAlreadyExistsException.class, () -> medicalRecordsDAO.addMedicalRecords(alreadyInMedicalRecord));
	    }
	 @Test
	    public void medicalRecordNotFoundEditTest(){
	        //Given
	        MedicalRecords medicalRecordNotInList = MedicalRecordsDAOTestData.medicalRecordsToAdd();
	        //When & Then
	        assertThrows(MedicalRecordsNotFoundException.class, () -> medicalRecordsDAO.editMedicalRecords(medicalRecordNotInList));
	    }
	 @Test
	    public void medicalRecordNotFoundDeleteTest(){
	        //Given
	        MedicalRecords medicalRecordNotInList = MedicalRecordsDAOTestData.medicalRecordsToAdd();
	        //When & Then
	        assertThrows(MedicalRecordsNotFoundException.class, () -> medicalRecordsDAO.deleteMedicalRecords(medicalRecordNotInList));
	    }

}
