package com.Safetynet.Repository;

import com.Safetynet.Model.Firestations;

public interface IFirestationDAO {

    
    public Firestations findByNumber(Integer firestationNumber);

   
    public Firestations addFirestations(Firestations firestations);

   
    public Firestations editFirestations(Firestations firestations);

  
    void deleteFirestations(Firestations firestations);
}
