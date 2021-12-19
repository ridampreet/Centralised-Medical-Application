package com.centrailized_medi_application;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

  // Fetch test case
  @Test
  void fetch() {
    String userName = "Aditya@hotmail.com";
    String password = "a1234";
    Patient patient = new Patient(new WelcomePage(), new PatientPage(userName), new DB_Connection(userName,password), new LoginAuthorisation());
    PatientLogin p_login = new PatientLogin(patient, new WelcomePage());
    p_login.setPatientName(userName);
    p_login.setPatientPass(password);
    patient.fetch(p_login.getPatientName(), p_login.getPatientPass());
    assertAll("Fetch Function",
        () -> assertEquals(userName, patient.getUsername()),
        () -> assertEquals(password, patient.getPassword()));
  }

  // Validation test case
  @Test
  void validate() {
    String userName = "Aditya@hotmail.com";
    String password = "a1234";
    Patient patient = new Patient(new WelcomePage(), new PatientPage(userName), new DB_Connection(userName, password), new LoginAuthorisation());
    PatientLogin p_login = new PatientLogin(patient, new WelcomePage());
    p_login.setPatientName(userName);
    p_login.setPatientPass(password);
    patient.fetch(p_login.getPatientName(), p_login.getPatientPass());
    patient.validate();
    assertAll("Validate Function",
        () -> assertTrue(patient.getUsernameStatus()),
        () -> assertTrue(patient.getPassStatus()));
  }

  // Correct Id and pass
  @Disabled("authenticate->Calls user input after it succeeds,hence ignored")
  @Test
  void authenticate()  {
    String userName = "Aditya@hotmail.com";
    String password = "a1234";
    Patient patient = new Patient(new WelcomePage(), new PatientPage(userName), new DB_Connection(userName,password), new LoginAuthorisation());
    PatientLogin p_login = new PatientLogin(patient, new WelcomePage());
    p_login.setPatientName(userName);
    p_login.setPatientPass(password);
    patient.fetch(p_login.getPatientName(), p_login.getPatientPass());
    patient.validate();
    patient.authenticate();
    assertTrue(patient.getAuthStatus());
  }


  // Incorrect Password but correct id
  @Disabled("authenticate_pass_incorrect_pass->Calls user input after it succeeds,hence ignored")
  @Test
  void authenticate_pass_incorrect_pass()  {
    String userName = "Aditya";
    String password = "a12";
    Patient patient = new Patient(new WelcomePage(), new PatientPage(userName), new DB_Connection(userName,password), new LoginAuthorisation());
    PatientLogin p_login = new PatientLogin(patient, new WelcomePage());
    p_login.setPatientName(userName);
    p_login.setPatientPass(password);
    patient.fetch(p_login.getPatientName(), p_login.getPatientPass());
    patient.validate();
    patient.authenticate();
    assertTrue(patient.getAuthStatus());
  }

  // Incorrect password and id
  @Disabled("authenticate_pass_invalid->Calls user input after it succeeds,hence ignored")
  @Test
  void authenticate_pass_invalid() {
    String user_name = "test";
    String password = "a12";
    Patient patient = new Patient(new WelcomePage(), new PatientPage(user_name), new DB_Connection(), new LoginAuthorisation());
    PatientLogin p_login = new PatientLogin(patient, new WelcomePage());
    p_login.setPatientName(user_name);
    p_login.setPatientPass(password);
    patient.fetch(p_login.getPatientName(), p_login.getPatientPass());
    patient.validate();
    patient.authenticate();
    assertTrue(patient.getAuthStatus());
  }
}