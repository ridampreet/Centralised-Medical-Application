package com.centrailized_medi_application;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class NewPatientTest {
    BasicDetails tempbasic = new BasicDetails();
    PatientDetails tempdetails = new PatientDetails();
    SecurityQuestions tempsecurity = new SecurityQuestions();


    //mocks the doctor data that is to be inserted
    void NewPatientTestOne() {
        tempbasic.setFirstName("one");
        tempbasic.setLastName("two");
        tempbasic.setDob("06/07/1999");
        tempbasic.setGender("Male");
        tempbasic.setPassword("test@ONE23");
        tempbasic.setEmailId("two@gmail.com");
        tempbasic.setAddress("Sector-29 Gandhinagar");
        tempbasic.setContactNo("4234234232");
        tempdetails.setBloodGroup("o+ve");
        tempdetails.setAllergy("pollengrains");
        tempdetails.setChronicDisease("tonsilitis");
        tempdetails.setInsuranceNo("IN00221133");
        tempdetails.setDonorCardNo("NG06071999");
        tempdetails.setFamilyMemberCode("GG002218831");
        tempdetails.setVolunteer("yes");
        tempbasic.setLatitude(80);
        tempbasic.setLongitude(90);
        tempsecurity.setAnswer1("SG");
        tempsecurity.setAnswer2("SWIM");
        tempsecurity.setAnswer3("Honda");
    }
    @Disabled
    @Test
    @DisplayName("Patient Register successfully")
    void update() {
        NewPatientTestOne();
        NewPatient newone = new NewPatient(tempbasic,tempdetails,tempsecurity, new WelcomePage());
        assertEquals(true,newone.getRegistrationStatus(), "unregistered Patient would return false");
    }
}

