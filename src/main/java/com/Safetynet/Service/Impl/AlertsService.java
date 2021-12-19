package com.Safetynet.Service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Safetynet.Model.Person;
import com.Safetynet.Model.Specific.ChildAlert;
import com.Safetynet.Model.Specific.Fire;
import com.Safetynet.Model.Specific.Flood;
import com.Safetynet.Model.Specific.ListByFirestation;
import com.Safetynet.Model.Specific.utils.FullInfoPerson;
import com.Safetynet.Model.Specific.utils.PersonWithNameAdressPhone;
import com.Safetynet.Model.Specific.utils.PersonWithNameAge;
import com.Safetynet.Model.Specific.utils.PersonWithNameAgeMedRecs;
import com.Safetynet.Service.IAlertsService;
@Service
public class AlertsService implements IAlertsService {
	private static final Logger LOGGER = LogManager.getLogger(AlertsService.class);
	@Autowired
    PersonService personService;

    @Autowired
    FirestationService firestationService;

    @Autowired
    MedicalRecordService medicalRecordService;
    
    

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setFirestationService(FirestationService firestationService) {
		this.firestationService = firestationService;
	}

	public void setMedicalRecordService(MedicalRecordService medicalRecordService) {
		this.medicalRecordService = medicalRecordService;
	}

	@Override
	public List<String> getEmailListByCity(String city) {
		 List<String> emailList = new ArrayList<String>();
		for (Person person : personService.findAll()) {
			if (person.getCity().equals(city)) {
				emailList.add( person.getEmail() );
			} 
		}
		return emailList;
	}

	@Override
	public ListByFirestation getPersonsListByFirestation(Integer firestation) {
		List<PersonWithNameAdressPhone>personWithNameAdressPhonesList=new ArrayList<PersonWithNameAdressPhone>();
		int adultsCounter=0;
		int childrenCounter=0;
		String firestationAddress = firestationService.findAddressByNumber(firestation);
		for (Person person : personService.findAll()) {
			if (person.getAddress().equals(firestationAddress)) {
				personWithNameAdressPhonesList.add(new PersonWithNameAdressPhone(
						person.getFirstName(),
						person.getLastName(),
						person.getAddress(),
						person.getPhone()));
				if (medicalRecordService.findAgeFromName(person.getFirstName(), person.getLastName())<18) {
					childrenCounter++;
				} else if (medicalRecordService.findAgeFromName(person.getFirstName(), person.getLastName())>18) {
					adultsCounter++;
				} {

				}
			}
		}
		return new ListByFirestation(personWithNameAdressPhonesList,adultsCounter,childrenCounter);
		
	}

	@Override
	public ChildAlert getChildsAndAdultsByAddress(String address) {
		LOGGER.debug("Initialize adults & Childrenlists");
        List<PersonWithNameAge> adultsList = new ArrayList<>();
        List<PersonWithNameAge> childrenList = new ArrayList<>();

        LOGGER.debug("start loop on personService.findAll()");
        for(Person person : personService.findAll()){
            if(person.getAddress().equals(address)){
                LOGGER.debug("Person address" + person.getAddress()+ "is matching");
                PersonWithNameAge personToAdd = new PersonWithNameAge(person.getFirstName(), person.getLastName(), medicalRecordService.findAgeFromName(person.getFirstName(), person.getLastName()));
                if(personToAdd.getAge() < 18){
                    LOGGER.debug("Add person to childrenList beacause age is "+ personToAdd.getAge());
                    childrenList.add(personToAdd);
                }else if(personToAdd.getAge() > 18){
                    LOGGER.debug("Add person to adultsList beacause age is "+ personToAdd.getAge());
                    adultsList.add(personToAdd);
                }
            }
        }
        if (childrenList.size() != 0) {
            LOGGER.debug("return ChildAlert");
            return new ChildAlert(childrenList, adultsList);
        }else {
            LOGGER.debug("no childrens in the list, return empty ChildAlert");
            return new ChildAlert();
        }
		
		
	}

	@Override
	public List<String> getAllPhonesByFirestationNumber(Integer firestationNumber) {
		String firestationAddress = firestationService.findAddressByNumber(firestationNumber);
        List<String> phoneNumberList = new ArrayList<>();
        for (Person person : personService.findAll()) {
			if (person.getAddress().equals(firestationAddress)) {
				phoneNumberList.add(person.getPhone());
			}
		}
		return  phoneNumberList;
	}

	@Override
	public Fire getPersonByAddress(String address) {
		
		Integer firestationNumber = firestationService.findNumberByAddress(address);
        
        List<PersonWithNameAgeMedRecs> personWithNameAgeMedRecsList = new ArrayList<>();

        for(Person person : personService.findAll()){
            if (person.getAddress().equals(address)){
                
                personWithNameAgeMedRecsList.add(new PersonWithNameAgeMedRecs(
                        person.getFirstName(),
                        person.getLastName(),
                        person.getPhone(),
                        medicalRecordService.findAgeFromName(person.getFirstName(), person.getLastName()),
                        medicalRecordService.findMedicationsByName(person.getFirstName(), person.getLastName()),
                        medicalRecordService.findAllergiesByName(person.getFirstName(), person.getLastName())
                ));
            }
        }
        return new Fire(firestationNumber,personWithNameAgeMedRecsList);
    }

	@Override
	public List<Flood> getPersonsAndAddressByFirestationNumber(List<Integer> firestationNumberList) {
		List<Flood> floodList = new ArrayList<>();

        for(Integer firestationNumber : firestationNumberList){
            String firestationAddress = firestationService.findAddressByNumber(firestationNumber);
            LOGGER.debug("FirestationAddress is " +firestationAddress);
            List<PersonWithNameAgeMedRecs> personWithNameAgeMedRecsList = new ArrayList<>();

            for (Person person : personService.findAll()){
                if(person.getAddress().equals(firestationAddress)){
                    LOGGER.debug("Person address match ! "+ person.getAddress());
                    personWithNameAgeMedRecsList.add(new PersonWithNameAgeMedRecs(
                            person.getFirstName(),
                            person.getLastName(),
                            person.getPhone(),
                            medicalRecordService.findAgeFromName(person.getFirstName(), person.getLastName()),
                            medicalRecordService.findMedicationsByName(person.getFirstName(), person.getLastName()),
                            medicalRecordService.findAllergiesByName(person.getFirstName(), person.getLastName())
                    ));
                }
            }
            floodList.add(new Flood(firestationAddress,personWithNameAgeMedRecsList));
            LOGGER.debug("Flood : "+new Flood(firestationAddress,personWithNameAgeMedRecsList)+" added to floodList");
        }
        return floodList;
	}

	@Override
	public List<FullInfoPerson> getFullInfoPersonByName(String firstName, String lastName) {
		List<FullInfoPerson>fullInfoPersonsList = new ArrayList<FullInfoPerson>();
		for (Person person : personService.findAll()) {
			if (person.getFirstName().equals(firstName)&&person.getLastName().equals(lastName)) {
				fullInfoPersonsList.add(new FullInfoPerson
						(person.getFirstName(), 
						 person.getLastName(), 
						 person.getAddress(),
						 medicalRecordService.findAgeFromName(person.getFirstName(), person.getLastName()), 
						 medicalRecordService.findMedicationsByName(person.getFirstName(), person.getLastName()), 
						 medicalRecordService.findAllergiesByName(person.getFirstName(), person.getLastName())));
			}
		}
		return fullInfoPersonsList;
	}

}
