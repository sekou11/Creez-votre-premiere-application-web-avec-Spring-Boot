package com.Safetynet.Service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Safetynet.Data.GeneralData;
import com.Safetynet.Exceptions.CustomExceptions.MedicalRecordsNotFoundException;
import com.Safetynet.Model.Data;
import com.Safetynet.Model.MedicalRecords;
import com.Safetynet.Utils.Impl.Dataloader;

@SpringBootTest
class MedicalRecordServiceTest {
	@Autowired
	MedicalRecordService medicalRecordService;

	@Autowired
	Dataloader dataloader;

	@BeforeEach
	public void setUp() {
		Data dataTest = new Data(GeneralData.getPersonList(), GeneralData.getFirestationList(),
				GeneralData.getMedicalRecordsList());
		((Dataloader) dataloader).setDataFile(dataTest);
	}

	@Test
	void testFindAll() {
		// GIVEN
		List<MedicalRecords> expected = GeneralData.getMedicalRecordsList();
		// WHEN
		List<MedicalRecords> result = medicalRecordService.findAll();
		// THEN
		assertEquals(expected, result);
	}

	@Test
	void testFindAgeFromName() {
		// GIVEN

		String firstName = "Felicia";
		String lastName = "Boyd";

		Integer expected = 35;
		// WHEN
		Integer result;
		result = medicalRecordService.findAgeFromName(firstName, lastName);

		// THEN
		assertEquals(expected, result);
	}

	@Test
	void testFindAgeFromNameByUnknownNameThrowExceptions() {
		// GIVEN

		String firstName = "D";
		String lastName = "S";

		Integer expected = 35;
		// WHEN && THEN
		assertThrows(MedicalRecordsNotFoundException.class,
				() -> medicalRecordService.findAgeFromName(firstName, lastName));
	}

	@Test
	void testFindAgeFromBirthdate() {
		// GIVEN
		String birthdate = "01/29/1987";
		Integer expected = 34;
		// WHEN
		Integer result = medicalRecordService.findAgeFromBirthdate(birthdate);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	void testFindAllergiesByName() {
		// GIVEN
		String firstName = "Felicia";
		String lastName = "Boyd";
		List<String> expected = List.of("xilliathal");

		// WHEN
		List<String> result = medicalRecordService.findAllergiesByName(firstName, lastName);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	void testFindAllergiesByUnknownName() {
		// GIVEN
		String firstName = "A";
		String lastName = "B";
		List<String> expected = Collections.emptyList();

		// WHEN
		List<String> result = medicalRecordService.findAllergiesByName(firstName, lastName);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	void testFindMedicationsByName() {

		String firstName = "John";
		String lastName = "Boyd";
		List<String> expected = List.of("aznol:350mg", "hydrapermazol:100mg");
		// GIVEN
		// WHEN
		List<String> result = medicalRecordService.findMedicationsByName(firstName, lastName);
		// THEN
		assertEquals(expected, result);

	}

	@Test
	void testFindMedicationsByUnknownName() {
		// GIVEN
		String firstName = "A";
		String lastName = "B";
		List<String> expected = Collections.emptyList();

		// WHEN
		List<String> result = medicalRecordService.findMedicationsByName(firstName, lastName);
		// THEN
		assertEquals(expected, result);
	}

}
