package com.Safetynet.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Safetynet.Model.Firestations;
import com.Safetynet.Service.Impl.FirestationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
public class FirestationController {
	private static final Logger LOGGER = LogManager.getLogger(MedicalRecordsController.class);

	@Autowired
	FirestationService firestationService;

	public void setFirestationService(FirestationService firestationService) {
		this.firestationService = firestationService;
	}

	@GetMapping(value = "/firestations")
	public List<Firestations> getAllFirestations() {
		LOGGER.info("Requête GET All firestation à /firestations");
		return firestationService.findAll();
	}

	@GetMapping(value = "/firestation/{stationNumber}")
	public Firestations getFirestationsByNumber(@PathVariable Integer stationNumber) {
		LOGGER.info("Requête GET ONE firestion à /firestations/{stationNumber}");

		return firestationService.findByNumber(stationNumber);

	}

	@PostMapping(value = "/firestation", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> addFirestation(@RequestBody Firestations firestations) {
		LOGGER.info("Requête Post reçue à /firestation");

		firestationService.addFirestation(firestations);
		return new ResponseEntity<>("Content added successfully", HttpStatus.CREATED);

	}

	@PutMapping(value = "/firestation", consumes = "application/json", produces = "application/json")
	public Firestations editFirestation(@RequestBody Firestations firestations) {
		LOGGER.info("Requête Put reçue à /firestation");

		Firestations firestationUpdated = firestationService.editFirestation(firestations);
		return firestationUpdated;

	}

	@DeleteMapping(value = "/firestation", consumes = "application/json")
	public void deleteFirestation(@RequestBody Firestations firestations) {
		LOGGER.info("Requête Delete reçue à /firestation");

		firestationService.deleteFirestation(firestations);

	}

}