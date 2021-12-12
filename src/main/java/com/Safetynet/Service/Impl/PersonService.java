package com.Safetynet.Service.Impl;

import com.Safetynet.Model.Person;
import com.Safetynet.Repository.Impl.PersonDAO;
import com.Safetynet.Service.IPersonService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IPersonService {

    @Autowired
    PersonDAO personDAO;

   

    public void setPersonDAO(PersonDAO personDAO) {
       
        this.personDAO = personDAO;
    }

    @Override
    public List<Person> findAll(){
        
        return personDAO.getPersonList();
    }

    @Override
    public Person findByName(String firstName, String lastName){
        
        return personDAO.findByName(firstName,lastName);
    }

    @Override
    public Person addPerson(Person person) {
        
        return personDAO.addPerson(person);
    }

    @Override
    public Person editPerson(Person person) {
       
        return personDAO.editPerson(person);
    }

    @Override
    public void deletePerson(Person person) {
        
        personDAO.deletePerson(person);
    }
}
