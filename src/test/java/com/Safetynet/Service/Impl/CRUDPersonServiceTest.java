package com.Safetynet.Service.Impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.Safetynet.Model.Person;
import com.Safetynet.Repository.Impl.PersonDAO;

@SpringBootTest
class CRUDPersonServiceTest {
	@MockBean
	PersonDAO personDAO;
	@Autowired
	PersonService personService;

	@Test
	void testFindByName() {
		// GIVEN
		String firstName = "John";
		String lastName = "Boyd";
		// WHEN

		when(personDAO.findByName(firstName, lastName)).thenReturn(
				new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"));
		// THEN
		assertEquals(personDAO.findByName(firstName, lastName), personService.findByName(firstName, lastName));
	}

	@Test
	void testIfFindByNameWillCalled() {
		// GIVEN
		String firstName="John";
		String lastName="Boyd";
		// WHEN
		
		personService.findByName(firstName, lastName);
	
	 //THEN
		verify(personDAO,Mockito.times(1)).findByName(firstName, lastName);
	
	}  
	@Test
	void testAddPerson() {
		
		// GIVEN
		Person person = new Person("Roger", "Boyd", "29 15th St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		// WHEN
		 when(personDAO.addPerson(person)).thenReturn(person);
		// THEN
		assertEquals(personDAO.addPerson(person), personService.addPerson(person));
	}

	@Test
	void testIfAddPersonwillCalled() {
		
		// GIVEN
		Person person = new Person("Roger", "Boyd", "29 15th St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		// WHEN
		personService.addPerson(person);
		// THEN
		verify(personDAO,Mockito.times(1)).addPerson(person);
	}
	


	@Test
	void testEditPerson() {
		// GIVEN
		Person person = new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com");
	
		// WHEN
		 when(personDAO.editPerson(person)).thenReturn(person);
		// THEN
		assertEquals(personDAO.editPerson(person), personService.editPerson(person));
	}
	@Test
	void testIfEditPersonwillCalled() {
		
		// GIVEN
		Person person = new Person("Roger", "Boyd", "29 15th St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
		// WHEN
		personService.editPerson(person);
		// THEN
		verify(personDAO,Mockito.times(1)).editPerson(person);
	}

	@Test
	void testifDeletePersonwillCalled() {
		// GIVEN
		Person person = new Person("dioubate", "sekou", "1509 Culver St", "Culver", "97451", "841-874-6512", "ds@email.com");
		//WHEN
		  personService.deletePerson(person);
		//THEN
		  verify(personDAO,Mockito.times(1)).deletePerson(person);
	}

}
