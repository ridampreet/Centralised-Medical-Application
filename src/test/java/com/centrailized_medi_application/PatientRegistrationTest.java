package com.centrailized_medi_application;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
public class PatientRegistrationTest {
    BasicDetails tempbasic = new BasicDetails();
    PatientDetails tempdetails = new PatientDetails();
    SecurityQuestions tempsecurity = new SecurityQuestions();

    @Test
    void RegistrationTest()
    {

        tempbasic.setFirstName("Superman");
        tempbasic.setLastName("human");
        tempbasic.setDob("01/01/2002");
        tempbasic.setGender("Male");
        tempbasic.setPassword("Super@123");
        tempbasic.setEmailId("superman@gmail.com");
        tempbasic.setAddress("Newyork");
        tempbasic.setContactNo("3382111120");
        tempdetails.setBloodGroup("O-VE");
        tempdetails.setAllergy("Cryptonite");
        tempdetails.setChronicDisease("NULL");
        tempdetails.setInsuranceNo("INSURE2002");
        tempdetails.setDonorCardNo("SDFS00324");
        tempdetails.setFamilyMemberCode("2000");
        tempdetails.setVolunteer("yes");
        tempbasic.setLatitude(tempbasic.getRandomNumber(0,200));
        tempbasic.setLongitude(tempbasic.getRandomNumber(0,200));
        tempsecurity.setAnswer1("NEWYORK HIGHSCHOOL");
        tempsecurity.setAnswer2("FLYING");
        tempsecurity.setAnswer3("NONEED");
    }
    @Disabled
    @Test
    @DisplayName("To execute doctor registration")
    void execute() {
        RegistrationTest();
        NewPatient newone = new NewPatient(tempbasic,tempdetails,tempsecurity, new WelcomePage());
        PatientRegistration patienttest = new PatientRegistration(newone,new WelcomePage());
        patienttest.execute();
        assertEquals(true,newone.getRegistrationStatus(), "Already registered patient execution " +
                "should return false");
    }
}

