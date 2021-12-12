package com.Safetynet.Repository;

import com.Safetynet.Model.MedicalRecords;

public interface IMedicalRecordDAO {

    
     public MedicalRecords addMedicalRecords(MedicalRecords medicalRecords);

   
     public MedicalRecords editMedicalRecords(MedicalRecords medicalRecords);

    
    public  void deleteMedicalRecords(MedicalRecords medicalRecords);
}
