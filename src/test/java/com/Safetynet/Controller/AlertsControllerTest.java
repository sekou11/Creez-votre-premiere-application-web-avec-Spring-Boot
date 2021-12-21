package com.Safetynet.Controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.Safetynet.Service.Impl.AlertsService;

@WebMvcTest(controllers = AlertsController.class)
class AlertsControllerTest {
	@Autowired
	MockMvc mockMvc ;
	
	@MockBean
	AlertsService alertsService ;
	
	@Test
	void testShowEmailsByCity() throws Exception {
		String city="Culver";
		mockMvc.perform(get("/communityEmail")
		.param("city", city))
		.andExpect(status().isOk());
	}

	@Test
	void testShowPhoneByFirestationNumber() throws Exception {
		Integer fireStationNumber=3;
		mockMvc.perform(get("/phoneAlert")
		.param("firestation", String.valueOf(fireStationNumber)))
		.andExpect(status().isOk());
	}

	@Test
	void testShowPersonInfoByNameAndLastName() throws Exception{
		String firstName = "John";
        String lastName = "Boyd";
		mockMvc.perform(get("/personInfo")
	   .param("firstName", firstName)
	   .param("lastName",lastName))
		.andExpect(status().isOk());
	}

	@Test
	void testShowPersonsListByFirestation() throws Exception {
		Integer stationNumber=1;
		mockMvc.perform(get("/firestation")
		.param("stationNumber", String.valueOf(stationNumber)))
		.andExpect(status().isOk());
	}

	@Test
	void testShowChildrensAndAdultsByAdress() throws Exception {
		String address ="29 15th St";
		mockMvc.perform(get("/childAlert")
		.param("address", address))
		.andExpect(status().isOk());
	}

	@Test
	void testShowPersonsListAndFirestationNumberByAdress() throws Exception {
		String address ="1509 Culver St";
		mockMvc.perform(get("/fire")
		.param("address", address))
		.andExpect(status().isOk());
	}

	@Test
	void testShowPersonsAndAddressByFireStationNumber() throws Exception {
		 String stationsNumberList = "1";
	        mockMvc.perform(get("/flood/stations")
	        .param("station_numbers", stationsNumberList))
	        .andExpect(status().isOk());
	}

}
