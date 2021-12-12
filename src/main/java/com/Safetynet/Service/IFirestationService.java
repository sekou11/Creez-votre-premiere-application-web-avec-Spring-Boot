package com.Safetynet.Service;

import com.Safetynet.Model.Firestations;

import java.util.List;

public interface IFirestationService {

    
     public List<Firestations> findAll();

   
     public Firestations findByNumber(Integer firestationNumber);

    
     public String findAddressByNumber(Integer firestationNumber);

   
     
    public Integer findNumberByAddress(String address);

  
     
    public  Firestations addFirestation(Firestations firestations);

    
    public Firestations editFirestation(Firestations firestations);

    
    void deleteFirestation(Firestations firestations);
}
