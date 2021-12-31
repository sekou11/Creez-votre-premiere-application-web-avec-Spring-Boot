package com.Safetynet.Controller;



import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import com.Safetynet.Model.MedicalRecords;
import com.Safetynet.Service.Impl.MedicalRecordService;

@RestController
public class MedicalRecordsController {
	 @Autowired
	    MedicalRecordService medicalRecordService;

	    public void setMedicalRecordService(MedicalRecordService medicalRecordService) {
	        this.medicalRecordService = medicalRecordService;
	    }

	    private static final Logger LOGGER = LogManager.getLogger(MedicalRecordsController.class);
	    
	    
	    @GetMapping(value="/medicalRecords")
	    public List<MedicalRecords> getAllMedicalRecords(){
	        LOGGER.info("Requête Get All medicalRecords à /medicalRecords");
	        return medicalRecordService.findAll();
	    }

	    @GetMapping(value ="/medicalRecord")
	    public MedicalRecords getMedicalRecordByName(@RequestParam String firstName,@RequestParam String lastName){
	        LOGGER.info("Requête GET By FirstName and lastName à /medicalRecord");
	        return medicalRecordService.findByName(firstName, lastName);
	    }

	    @PostMapping(value = "/medicalRecord", consumes = "application/json", produces = "application/json")
	    public ResponseEntity<String> addMedicalRecord(@RequestBody MedicalRecords medicalRecords){
	        LOGGER.info("Requête Post  à /medicalRecord");
	      
	       
	             medicalRecordService.addMedicalRecords(medicalRecords);
	            return new ResponseEntity<>("Content added successfully", HttpStatus.CREATED);
	        
	    }

	    @PutMapping(value = "/medicalRecord", consumes = "application/json", produces = "application/json")
	    public MedicalRecords editMedicalRecord(@RequestBody MedicalRecords medicalRecords){
	        LOGGER.info("Requête Put  à /medicalRecord");
	       
	        
	           MedicalRecords medicalRecordUpdated= medicalRecordService.editMedicalRecords(medicalRecords);
	            return medicalRecordUpdated;
	        

	    }
	    @DeleteMapping(value = "/medicalRecord", consumes = "application/json")
	    public void deleteMedicalRecord(@RequestBody MedicalRecords medicalRecords){
	        LOGGER.info("Requête Delete reçue à /medicalRecord");
	       
	        
	         medicalRecordService.deleteMedicalRecords(medicalRecords);
	            
	    }

}
