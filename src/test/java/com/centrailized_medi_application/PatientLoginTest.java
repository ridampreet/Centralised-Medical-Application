package com.centrailized_medi_application;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class PatientLoginTest {


  @Test
  void setPatient_name()  {
    String userName = "Aditya@hotmail.com";
    String password = "a1234";
    Patient p1 = new Patient(new WelcomePage(), new PatientPage(userName), new DB_Connection(userName, password), new LoginAuthorisation());
    PatientLogin p_login = new PatientLogin(p1, new WelcomePage());  // Passing the object to the patient login
    p_login.setPatientName(userName);
    assertEquals(userName, p_login.getPatientName(), "Error: Incorrect Name");
  }

  /*Check against empty string for username*/
  @Disabled("setPatient_name_only_char->Calls user input after it succeeds,hence ignored")
  @Test
  void setPatient_name_only_char()  {
    String userName = "Aditya1234";
    String password = "a1234";
    Patient p1 = new Patient(new WelcomePage(), new PatientPage(userName), new DB_Connection(userName,password), new LoginAuthorisation());
    PatientLogin p_login = new PatientLogin(p1, new WelcomePage());  // Passing the object to the patient login
    p_login.setPatientName(userName);
    assertNull(p_login.getPatientName());
  }

  /* To verify the set func for login Patient (password)*/
  @Test
  void setPatient_pass() {
    String userName = "Aditya1234";
    String password = "a1234";
    Patient p1 = new Patient(new WelcomePage(), new PatientPage(userName), new DB_Connection(userName,password), new LoginAuthorisation());
    PatientLogin p_login = new PatientLogin(p1, new WelcomePage());  // Passing the object to the patient login
    p_login.setPatientPass(userName);
    assertEquals(userName, p_login.getPatientPass(), "Error: Incorrect Password");
  }

  @Test
  void execute() {
    String userName = "Aditya@hotmail.com";
    String password = "a1234";
    Patient patient = new Patient(new WelcomePage(), new PatientPage(userName), new DB_Connection(userName,password), new LoginAuthorisation());
    PatientLogin p_login = new PatientLogin(patient, new WelcomePage());  // Passing the object to the patient login
    p_login.setPatientName(userName);
    p_login.setPatientPass(password);


    //Fetch
    patient.fetch(p_login.getPatientName(), p_login.getPatientPass());
    // Validate
    patient.validate();

    assertAll("Checking Execute Function",
        () -> assertEquals(userName, patient.getUsername()),
        () -> assertEquals(password, patient.getPassword()),
        () -> assertTrue(patient.getUsernameStatus()),
        () -> assertTrue(patient.getPassStatus()));

  }
}