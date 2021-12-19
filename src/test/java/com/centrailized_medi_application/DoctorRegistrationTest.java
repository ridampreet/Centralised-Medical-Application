package com.centrailized_medi_application;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class DoctorRegistrationTest {
  BasicDetails bc = new BasicDetails();
  SecurityQuestions sq = new SecurityQuestions();
  DoctorDetails dd = new DoctorDetails();

  //mocks the doctor data that is to be inserted
  void mockData() {
    bc.setFirstName("Monisha");
    bc.setLastName("J");
    bc.setGender("Female");
    bc.setDob("Dec 31 2000");
    bc.setAddress("Street1 city1");
    bc.setLatitude(80);
    bc.setLongitude(90);
    bc.setEmailId("monishaa@test.com");
    bc.setPassword("password");
    dd.setSpeciality("neurologist");
    dd.setRegistrationNumber(1234567);
    sq.setAnswer1("school1");
    sq.setAnswer2("hobby1");
    sq.setAnswer3("Scooty");
  }

  @Disabled
  @Test
  @DisplayName("To execute doctor registration")
  void execute()  {
    mockData();
    NewDoctor dr = new NewDoctor(bc,dd,sq, new WelcomePage());
    DoctorRegistration docReg = new DoctorRegistration(dr,new WelcomePage());

    //Fetch and Validate
    dr.update();
    assertTrue(dr.getRegistrationStatus(), "registered doctor execution should have it true");
  }
}