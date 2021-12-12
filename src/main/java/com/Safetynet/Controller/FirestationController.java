package com.Safetynet.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Safetynet.Model.Firestations;
import com.Safetynet.Service.Impl.FirestationService;

@RestController
public class FirestationController {

	 @Autowired
	    FirestationService firestationService;

	    public void setFirestationService(FirestationService firestationService) {
	        this.firestationService = firestationService;
	    }
	    
	    @GetMapping(value="/firestations")
	    public ResponseEntity<List<Firestations>> getAllFirestations(){
	        
	        return new ResponseEntity<>(firestationService.findAll(), HttpStatus.OK);
	    }

	    @GetMapping(value="/firestations/{stationNumber}")
	    public ResponseEntity<Firestations> getFirestationsByNumber(@PathVariable Integer stationNumber){
	        
	        if(stationNumber == null){
	           
	            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	        }
	        else {
	            return new ResponseEntity<>(firestationService.findByNumber(stationNumber), HttpStatus.OK);
	        }
	    }

	    @PostMapping(value="/firestation", consumes = "application/json", produces = "application/json")
	    public ResponseEntity<String> addFirestation(@RequestBody Firestations firestations){
	        
	        if(firestations == null) {
	           
	            return new ResponseEntity<>("Content is empty", HttpStatus.NO_CONTENT);
	        }
	        else {
	            firestationService.addFirestation(firestations);
	            return new ResponseEntity<>("Content added successfully", HttpStatus.CREATED);
	        }
	    }

	    @PutMapping(value="/firestation", consumes = "application/json", produces = "application/json")
	    public ResponseEntity<String> editFirestation(@RequestBody Firestations firestations){
	       
	        if(firestations == null) {
	           
	            return new ResponseEntity<>("Content is empty", HttpStatus.NO_CONTENT);
	        }
	        else {
	            firestationService.editFirestation(firestations);
	            return new ResponseEntity<>("Content update successfully", HttpStatus.OK);
	        }
	    }

	    @DeleteMapping(value="/firestation", consumes = "application/json")
	    public ResponseEntity<String> deleteFirestation(@RequestBody Firestations firestations){
	        
	        if(firestations == null) {
	            
	            return new ResponseEntity<>("Content is empty", HttpStatus.NO_CONTENT);
	        }
	        else {
	            firestationService.deleteFirestation(firestations);
	            return new ResponseEntity<>("Content deleted successfully", HttpStatus.OK);
	        }
	    }

    
}