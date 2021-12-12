package com.Safetynet.Repository;

import com.Safetynet.Model.Person;


public interface IPersonDAO {

    
    public Person findByName(String firstName, String lastName);

   
    public Person addPerson(Person person);

   
     
    public Person editPerson(Person person);

    
    void deletePerson(Person person);
}
