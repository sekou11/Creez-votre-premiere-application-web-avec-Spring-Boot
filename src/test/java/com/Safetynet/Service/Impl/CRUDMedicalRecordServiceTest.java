package com.Safetynet.Service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Safetynet.Model.MedicalRecords;
import com.Safetynet.Repository.Impl.MedicalRecordsDAO;

@SpringBootTest
class CRUDMedicalRecordServiceTest {

	@MockBean
	MedicalRecordsDAO medicalRecordsDAO;
	@Autowired
	MedicalRecordService medicalRecordService;

	@Test
	void testFindByName() {
		// GIVEN
		String firstName = "John";
		String lastName = "Boyd";
		// WHEN

		when(medicalRecordsDAO.findByName(firstName, lastName)).thenReturn(new MedicalRecords("John", "Boyd",
				"03/06/1984", List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan")));
		// THEN
		assertEquals(medicalRecordsDAO.findByName(firstName, lastName),
				medicalRecordService.findByName(firstName, lastName));
	}

	@Test
	void testIfFindByNamewillCalled() {
		// GIVEN
		String firstName = "John";
		String lastName = "Boyd";
		// WHEN

		medicalRecordService.findByName(firstName, lastName);
		// THEN
		verify(medicalRecordsDAO, Mockito.times(1)).findByName(firstName, lastName);

	}

	@Test
	void testAddMedicalRecords() {
		// GIVEN
		MedicalRecords medicalRecords = new MedicalRecords("John", "Boyd", "03/06/1984",
				List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan"));
		// WHEN
		when(medicalRecordsDAO.addMedicalRecords(medicalRecords)).thenReturn(medicalRecords);
		// THEN
		assertEquals(medicalRecordsDAO.addMedicalRecords(medicalRecords),
				medicalRecordService.addMedicalRecords(medicalRecords));
	}

	@Test
	void testIfAddMedicalRecordwillCalled() {
		// GIVEN

		MedicalRecords medicalRecords = new MedicalRecords("John", "Boyd", "03/06/1984",
				List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan"));
		// WHEN

		medicalRecordService.addMedicalRecords(medicalRecords);
		// THEN
		verify(medicalRecordsDAO, Mockito.times(1)).addMedicalRecords(medicalRecords);

	}

	@Test
	void testEditMedicalRecords() {
		// GIVEN
		MedicalRecords medicalRecords = new MedicalRecords("John", "Boyd", "03/06/1984",
				List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan"));
		// WHEN
		when(medicalRecordsDAO.editMedicalRecords(medicalRecords)).thenReturn(medicalRecords);
		// THEN
		assertEquals(medicalRecordsDAO.editMedicalRecords(medicalRecords),
				medicalRecordService.editMedicalRecords(medicalRecords));
	}

	@Test
	void testIfEditMedicalRecordwillCalled() {
		// GIVEN

		MedicalRecords medicalRecords = new MedicalRecords("John", "Boyd", "03/06/1984",
				List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan"));
		// WHEN

		medicalRecordService.editMedicalRecords(medicalRecords);
		// THEN
		verify(medicalRecordsDAO, Mockito.times(1)).editMedicalRecords(medicalRecords);

	}

	@Test
	void testDeleteMedicalRecords() {

		// GIVEN

		MedicalRecords medicalRecords = new MedicalRecords("John", "Boyd", "03/06/1984",
				List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan"));
		// WHEN

		medicalRecordService.deleteMedicalRecords(medicalRecords);
		// THEN
		verify(medicalRecordsDAO, Mockito.times(1)).deleteMedicalRecords(medicalRecords);

	}

}
