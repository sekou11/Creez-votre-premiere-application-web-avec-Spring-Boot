package com.Safetynet.Repository;

import com.Safetynet.Model.MedicalRecords;

public interface IMedicalRecordDAO {

	 /**
     * Method who add a medicalrecord, based on firstname & name
     * @param medicalRecords medicalrecord that we want to add
     * @return added medicalrecord
     */
     public MedicalRecords addMedicalRecords(MedicalRecords medicalRecords);

     /**
      * Method who edit a medicalrecord, based on firstname & name
      * @param medicalRecords medicalrecord that we want to edit
      * @return edited medicalrecord
      */
     public MedicalRecords editMedicalRecords(MedicalRecords medicalRecords);
     /**
      * Method who delete a medicalrecord
      * @param medicalRecords medicalrecord that we want to delete
      */
    
    public  void deleteMedicalRecords(MedicalRecords medicalRecords);
}
