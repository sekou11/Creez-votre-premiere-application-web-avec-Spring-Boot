package com.Safetynet.Service;

import com.Safetynet.Model.Person;

import java.util.List;

public interface IPersonService {

	 /**
     * Method who find all the persons from the list
     * @return list of all the persons (List of persons)
     */
	 public List<Person> findAll();
	 /**
	     * Method who search a person in the list for a given firstname and lastname
	     * @param firstName firstname of the wanted person
	     * @param lastName lastname of the wanted person
	     * @return person that we were searching for
	     */
    
	 public Person findByName(String firstName, String lastName);
	 /**
	     * Method who add a person, based on firstname & lastname
	     * @param person person that we want to add
	     * @return added person
	     */
   
	 public Person addPerson(Person person);
	  /**
	     * Method who edit a person, based on firstname & name
	     * @param person that we want to edit
	     * @return edited person
	     */
   
	 public Person editPerson(Person person);
	 /**
	     * Method who delete a person
	     * @param person person that we want to delete
	     */
  
	 public void deletePerson(Person person);
}
