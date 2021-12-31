package com.Safetynet.Controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.Safetynet.Model.MedicalRecords;
import com.Safetynet.Service.Impl.MedicalRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = MedicalRecordsController.class)
class MedicalRecordsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MedicalRecordService medicalRecordsControllerService;
	
	MedicalRecords medicalRecords = new MedicalRecords("John", "Boyd", "03/06/1984", List.of("aznol:350mg", "hydrapermazol:100mg"), List.of("nillacilan"));

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
  
    @Test
	void testGetAllMedicalRecords() throws Exception {
		mockMvc.perform(get("/medicalRecords"))
		.andExpect(status().isOk());
	}

	@Test
	void testGetMedicalRecordByName() throws Exception {
		mockMvc.perform(get("/medicalRecord")
				.param("firstName", "John")
				.param("lastName","Boyd"))
		.andExpect(status().isOk());
	}

	@Test
	void testAddMedicalRecord() throws Exception {
		mockMvc.perform(post("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(medicalRecords)))
				.andExpect(status().isCreated());
	}

	@Test
	void testEditMedicalRecord() throws Exception {
		mockMvc.perform(put("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(medicalRecords)))
				.andExpect(status().isOk());
	}

	@Test
	void testDeleteMedicalRecord() throws Exception {
		mockMvc.perform(delete("/medicalRecord")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(medicalRecords)))
				.andExpect(status().isOk());
	}

}
