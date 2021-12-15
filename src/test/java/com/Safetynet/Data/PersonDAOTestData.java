package com.Safetynet.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.Safetynet.Model.Person;

public class PersonDAOTestData {
	
	public static Person personToAdded() {
		return new Person("dioubate", "sekou", "1509 Culver St", "Culver", "97451", "841-874-6512", "ds@email.com");
	}

	public static List<Person> getPersonListToadded() {

		return new ArrayList<Person>(Arrays.asList(
				new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"),
				new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com"),
				new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com"),
				new Person("Roger", "Boyd", "29 15th St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"),
				new Person("Felicia", "Boyd", "29 15th St", "Culver", "97451", "841-874-6544", "jaboyd@email.com"),
				new Person("dioubate", "sekou", "1509 Culver St", "Culver", "97451", "841-874-6512", "ds@email.com")));
	}

	public static Person personToEdit() {
		return new Person("John", "Boyd", "29 15th St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
	}

	public static List<Person> getPersonListWithEditedPerson() {
		return new ArrayList<>(Arrays.asList(
				new Person("John", "Boyd", "29 15th St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"),
				new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com"),
				new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com"),
				new Person("Roger", "Boyd", "29 15th St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"),
				new Person("Felicia", "Boyd", "29 15th St", "Culver", "97451", "841-874-6544", "jaboyd@email.com")));
	}

	public static Person personToDelete() {
		return new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
	}

	public static List<Person> getPersonListWithDeletedPerson() {
		return new ArrayList<>(Arrays.asList(
				new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com"),
				new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com"),
				new Person("Roger", "Boyd", "29 15th St", "Culver", "97451", "841-874-6512", "jaboyd@email.com"),
				new Person("Felicia", "Boyd", "29 15th St", "Culver", "97451", "841-874-6544", "jaboyd@email.com")));
	}
}
