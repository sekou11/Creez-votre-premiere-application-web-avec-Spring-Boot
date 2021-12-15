package com.Safetynet.Repository.Impl;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.Safetynet.Data.GeneralData;
import com.Safetynet.Data.PersonDAOTestData;
import com.Safetynet.Exceptions.CustomExceptions.PersonAlreadyExistsException;
import com.Safetynet.Exceptions.CustomExceptions.PersonNotFoundException;
import com.Safetynet.Model.Data;
import com.Safetynet.Model.Person;
import com.Safetynet.Utils.Impl.Dataloader;

@SpringBootTest
class PersonDAOTest {
	@Autowired
	Dataloader dataloader;

	@Autowired
	PersonDAO personDAO;

	@BeforeEach
	public void setUp() {
		Data dataTest = new Data(GeneralData.getPersonList(), GeneralData.getFirestationList(),
				GeneralData.getMedicalRecordsList());
		((Dataloader) dataloader).setDataFile(dataTest);
	}

	@Test
	void testFindByName() {
		// GIVEN
		String firstName = "John";
		String lastName = "Boyd";
		Person expected = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"jaboyd@email.com");
		// WHEN
		Person result = personDAO.findByName(firstName, lastName);
		// THEN
		assertEquals(expected, result);
	}

	@Test
	void testAddPerson() {
		// GIVEN
		Person personToAdded = PersonDAOTestData.personToAdded();
		List<Person> expected = PersonDAOTestData.getPersonListToadded();
		// WHEN

		personDAO.addPerson(personToAdded);
		// THEN
		assertEquals(expected, personDAO.getPersonList());
	}

	@Test
	void testEditPerson() {
		// GIVEN
		Person personToEdit = PersonDAOTestData.personToEdit();
		List<Person> expected = PersonDAOTestData.getPersonListWithEditedPerson();
		// WHEN

		personDAO.editPerson(personToEdit);
		// THEN
		assertEquals(expected, personDAO.getPersonList());
	}

	@Test
	void testDeletePerson() {
		// GIVEN
		Person personToDelete = PersonDAOTestData.personToDelete();
		List<Person> expected = PersonDAOTestData.getPersonListWithDeletedPerson();
		// WHEN

		personDAO.deletePerson(personToDelete);
		// THEN
		assertEquals(expected, personDAO.getPersonList());
	}

	@Test
	public void personNotFoundFindByNameTest() {
		// Given
		String firstName = "toto";
		String lastName = "tata";
		// When & Then
		assertThrows(PersonNotFoundException.class, () -> personDAO.findByName(firstName, lastName));
	}

	@Test
	public void AlreadyPersonsExistingTest() {
		// GIVEN
		Person personExist = new Person("Roger", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512",
				"jaboyd@email.com");
		// WHEN &THEN
		assertThrows(PersonAlreadyExistsException.class, () -> personDAO.addPerson(personExist));

	}
	 @Test
	    public void personNotFoundEditTest(){
	        //Given
	        Person personNotInList = PersonDAOTestData.personToAdded();
	        //When & Then
	        assertThrows(PersonNotFoundException.class, () -> personDAO.editPerson(personNotInList));
	    }

	    @Test
	    public void personNotFoundDeleteTest(){
	        //Given
	        Person personNotInList = PersonDAOTestData.personToAdded();
	        //When & Then
	        assertThrows(PersonNotFoundException.class, () -> personDAO.deletePerson(personNotInList));
	    }
}
