package com.Safetynet.Service;

import com.Safetynet.Model.MedicalRecords;

import java.util.List;

public interface IMedicalRecordService {

   
	 public List<MedicalRecords> findAll();

    
	 public List<String>findAllergiesByName(String firstName, String lastName);

    
     
	 public List<String>findMedicationsByName(String firstName, String lastName);

 
	 public MedicalRecords addMedicalRecords(MedicalRecords medicalRecords);

    
	 public MedicalRecords editMedicalRecords(MedicalRecords medicalRecords);

    
    void deleteMedicalRecords(MedicalRecords medicalRecords);
}
