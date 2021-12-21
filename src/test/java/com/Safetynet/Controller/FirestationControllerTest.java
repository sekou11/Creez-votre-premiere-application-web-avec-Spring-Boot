package com.Safetynet.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.Safetynet.Model.Firestations;
import com.Safetynet.Service.Impl.FirestationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = FirestationController.class)
class FirestationControllerTest {
	@Autowired
	MockMvc mockMvc;

	@MockBean
	FirestationService firestationService;

	Firestations firestations = new Firestations("29 15th St", 2);

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void testGetAllFirestations() throws Exception {
		mockMvc.perform(get("/firestations")).andExpect(status().isOk());
	}

	@Test
	void testGetFirestationsByNumber() throws Exception {
		mockMvc.perform(get("/firestation/3")).andExpect(status().isOk());
	}

	@Test
	void testAddFirestation() throws Exception {
		mockMvc.perform(
				post("/firestation").contentType(MediaType.APPLICATION_JSON).content(asJsonString(firestations)))
				.andExpect(status().isCreated());
	}

	@Test
	void testEditFirestation() throws Exception {
		mockMvc.perform(put("/firestation").contentType(MediaType.APPLICATION_JSON).content(asJsonString(firestations)))
				.andExpect(status().isOk());
	}

	@Test
	void testDeleteFirestation() throws Exception {
		mockMvc.perform(
				delete("/firestation").contentType(MediaType.APPLICATION_JSON).content(asJsonString(firestations)))
				.andExpect(status().isOk());
	}

}
