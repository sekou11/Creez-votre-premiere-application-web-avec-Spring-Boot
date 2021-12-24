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

import com.Safetynet.Model.Person;
import com.Safetynet.Service.Impl.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = PersonController.class)
class PersonControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonService personService;
	Person person = new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void testNullPerson() throws Exception {
		PersonController pc = new PersonController();

		pc.setPersonService(null);

		pc.addPerson(null);
		pc.editPerson(null);
		pc.deletePerson(null);

	}

	@Test
	void testGetAllPersons() throws Exception {
		mockMvc.perform(get("/persons")).andExpect(status().isOk());
	}

	@Test
	void testGetPersonByName() throws Exception {
		mockMvc.perform(get("/person").param("firstName", "John").param("lastName", "Boyd")).andExpect(status().isOk());
	}

	@Test
	void testAddPerson() throws Exception {
		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(asJsonString(person)))
				.andExpect(status().isCreated());
	}

	@Test
	void testEditPerson() throws Exception {
		mockMvc.perform(put("/person").contentType(MediaType.APPLICATION_JSON).content(asJsonString(person)))
				.andExpect(status().isOk());
	}

	@Test
	void testDeletePerson() throws Exception {
		mockMvc.perform(delete("/person").contentType(MediaType.APPLICATION_JSON).content(asJsonString(person)))
				.andExpect(status().isOk());
	}

}
