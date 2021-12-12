package com.Safetynet.Utils.Impl;

import com.Safetynet.Model.Data;
import com.Safetynet.Repository.Impl.FirestationDAO;
import com.Safetynet.Repository.Impl.MedicalRecordsDAO;
import com.Safetynet.Repository.Impl.PersonDAO;
import com.Safetynet.Utils.IDataloader;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class Dataloader implements IDataloader{

    @Autowired
    PersonDAO personDAO;
    @Autowired
    MedicalRecordsDAO medicalRecordsDAO;
    @Autowired
    FirestationDAO firestationDAO;

    Data data = null;

    static ObjectMapper mapper = new ObjectMapper();

    public void setDataFile(Data data) {
        this.data = data;
        loadData();
    }

    public void loadData(){
        if (data != null) {
            daoConstruct();
        }else {
            try {
                data = mapper.readValue(new File("data.json"), Data.class);
                daoConstruct();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void daoConstruct(){
        personDAO.setPersonList(data.getPersons());
        medicalRecordsDAO.setMedicalRecordsList(data.getMedicalrecords());
        firestationDAO.setFirestationsList(data.getFirestations());
    }

    @PostConstruct
    private void postConstruct(){
        loadData();
    }
}
