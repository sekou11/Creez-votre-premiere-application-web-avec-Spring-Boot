package com.Safetynet.Service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Safetynet.Data.GeneralData;
import com.Safetynet.Model.Data;
import com.Safetynet.Model.Person;
import com.Safetynet.Utils.Impl.Dataloader;
 @SpringBootTest
class PersonServiceTest {
	@Autowired
	 PersonService personService;
	
	@Autowired
	Dataloader  dataloader;
	

	
	@BeforeEach
	public void setUp() {
		Data dataTest = new Data(GeneralData.getPersonList(), GeneralData.getFirestationList(),
				GeneralData.getMedicalRecordsList());
		((Dataloader) dataloader).setDataFile(dataTest);
	}
	

	@Test
	void testFindAll() {
		//GIVEN
		List<Person>expected = GeneralData.getPersonList();
		//WHEN
		List<Person> result= personService.findAll();
		//THEN
		assertEquals(expected, result);
	}


}
