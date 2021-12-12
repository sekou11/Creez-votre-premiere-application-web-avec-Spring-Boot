package com.Safetynet.Service;

import com.Safetynet.Model.Person;

import java.util.List;

public interface IPersonService {

    
	 public List<Person> findAll();

    
	 public Person findByName(String firstName, String lastName);

   
	 public Person addPerson(Person person);

   
	 public Person editPerson(Person person);

  
	 public void deletePerson(Person person);
}
