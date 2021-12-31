package com.Safetynet.Controller;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Safetynet.Model.Person;
import com.Safetynet.Service.Impl.PersonService;

@RestController
public class PersonController {

	private static final Logger LOGGER = LogManager.getLogger(PersonController.class);

	@Autowired
	PersonService personService;

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping(value = "/persons")
	public List<Person> getAllPersons() {
		LOGGER.info("Requête envoyé à /persons");
		return personService.findAll();
	}

	@GetMapping(value = "/person")
	public Person getPersonByName(@RequestParam String firstName, @RequestParam String lastName) {
		LOGGER.info("Requête GET envoye à /person");
		return personService.findByName(firstName, lastName);
	}

	@PostMapping(value="/person", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
    	
        
            personService.addPerson(person);
            return new ResponseEntity<>("Person added successfully", HttpStatus.CREATED);
        
    }

	@PutMapping(value = "/person", consumes = "application/json", produces = "application/json")
	public Person editPerson(@RequestBody Person person) {
		LOGGER.info("Requête Put  à /person");

		Person personUpdated = personService.editPerson(person);
		return personUpdated;
	}

	@DeleteMapping(value = "/person", consumes = "application/json")
	public void deletePerson(@RequestBody Person person) {
		LOGGER.info("Requête Delete reçue à /person");
		personService.deletePerson(person);

	}
}
