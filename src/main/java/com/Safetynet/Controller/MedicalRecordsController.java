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
	    public ResponseEntity<List<MedicalRecords>> getAllMedicalRecords(){
	        LOGGER.info("Requête Get All medicalRecords à /medicalRecords");
	        return new ResponseEntity<>(medicalRecordService.findAll(),HttpStatus.OK);
	    }

	    @GetMapping(value ="/medicalRecord")
	    public ResponseEntity<MedicalRecords> getMedicalRecordByName(@RequestParam String firstName,@RequestParam String lastName){
	        LOGGER.info("Requête GET By FirstName and lastName à /medicalRecord");
	        return new ResponseEntity<>(medicalRecordService.findByName(firstName, lastName), HttpStatus.OK);
	    }

	    @PostMapping(value = "/medicalRecord", consumes = "application/json", produces = "application/json")
	    public ResponseEntity<String> addMedicalRecord(@RequestBody MedicalRecords medicalRecords){
	        LOGGER.info("Requête Post  à /medicalRecord");
	        if(medicalRecords == null) {
	            LOGGER.error("pas de body fourni");
	            return new ResponseEntity<>("Content is empty", HttpStatus.NO_CONTENT);
	        }
	        else {
	            medicalRecordService.addMedicalRecords(medicalRecords);
	            return new ResponseEntity<>("Content added successfully", HttpStatus.CREATED);
	        }
	    }

	    @PutMapping(value = "/medicalRecord", consumes = "application/json", produces = "application/json")
	    public ResponseEntity<String> editMedicalRecord(@RequestBody MedicalRecords medicalRecords){
	        LOGGER.info("Requête Put  à /medicalRecord");
	        if(medicalRecords == null) {
	            LOGGER.error("pas de body fourni");
	            return new ResponseEntity<>("Content is empty", HttpStatus.NO_CONTENT);
	        }
	        else {
	            medicalRecordService.editMedicalRecords(medicalRecords);
	            return new ResponseEntity<>("Content updated successfully", HttpStatus.OK);
	        }

	    }
	    @DeleteMapping(value = "/medicalRecord", consumes = "application/json")
	    public ResponseEntity<String> deleteMedicalRecord(@RequestBody MedicalRecords medicalRecords){
	        LOGGER.info("Requête Delete reçue à /medicalRecord");
	        if(medicalRecords == null) {
	            LOGGER.error("pas de body fourni");
	            return new ResponseEntity<>("Content is empty", HttpStatus.NO_CONTENT);
	        }
	        else {
	            medicalRecordService.deleteMedicalRecords(medicalRecords);
	            return new ResponseEntity<>("Content deleted successfully", HttpStatus.OK);
	        }
	    }

}
