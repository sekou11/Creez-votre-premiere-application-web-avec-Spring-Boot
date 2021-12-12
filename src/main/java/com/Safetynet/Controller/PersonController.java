package com.Safetynet.Controller;

import java.util.List;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    @GetMapping(value="/persons")
    public ResponseEntity<List<Person>> getAllPersons(){
    	LOGGER.info("Requête envoyé à /persons");
        return new ResponseEntity<>(personService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value="/person")
    public ResponseEntity<Person> getPersonByName(@RequestParam String firstName,@RequestParam String lastName){
    	LOGGER.info("Requête GET envoye à /person");
            return new ResponseEntity<>(personService.findByName(firstName, lastName), HttpStatus.OK);
    }

    @PostMapping(value="/person", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> addPerson(@RequestBody Person person) {
    	LOGGER.info("Requête Post à /person");
        if(person == null) {
        	 LOGGER.error("pas de body fourni");
            return new ResponseEntity<>("Content is empty", HttpStatus.NO_CONTENT);
        }
        else {
            personService.addPerson(person);
            return new ResponseEntity<>("Person added successfully", HttpStatus.CREATED);
        }
    }

    @PutMapping(value="/person", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> editPerson(@RequestBody Person person) {
    	  LOGGER.info("Requête Put  à /person");
        if(person == null) {
        	LOGGER.error("pas de body fourni");
            return new ResponseEntity<>("Content is empty", HttpStatus.NO_CONTENT);
        }
        else {
            personService.editPerson(person);
            return new ResponseEntity<>("Person updated successfully", HttpStatus.OK);
        }
    }

    @DeleteMapping(value="/person", consumes = "application/json")
    public ResponseEntity<String> deletePerson(@RequestBody Person person) {
    	 LOGGER.info("Requête Delete reçue à /person");
        if(person == null) {
        	LOGGER.error("pas de body fourni");
            return new ResponseEntity<>("Content is empty", HttpStatus.NO_CONTENT);
        }
        else {
            personService.deletePerson(person);
            return new ResponseEntity<>("Person deleted successfully", HttpStatus.OK);
        }
    }
}
