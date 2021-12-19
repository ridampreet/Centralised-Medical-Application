package com.centrailized_medi_application;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class DoctorLoginTest {

  Doctor dr = new Doctor(new WelcomePage(), new DoctorPage("monisha@yahoo.com"), new DB_Connection(),new LoginAuthorisation());
  DoctorLogin docLogin = new DoctorLogin(dr,new WelcomePage());

  DoctorLoginTest() {

  }

  @Test
  @DisplayName("To get doctor username")
  void getDoctorUsername() {
    docLogin.setDoctorName("docUsername@gmail.com");
    assertEquals("docUsername@gmail.com", docLogin.getDoctorName(), "doctor username getter fails");
  }


  @Test
  @DisplayName("To set doctor username")
  void setDoctorUsername()  {
    docLogin.setDoctorName("docUsername@gmail.com");
    assertEquals("docUsername@gmail.com", docLogin.getDoctorName(), "doctor username setter fails");
  }

  @Test
  @DisplayName("To get doctor password")
  void getDoctorPassword() {
    docLogin.setDoctorPassword("password");
    assertEquals("password", docLogin.getDoctorPassword(), "doctor password getter fails");
  }

  @Test
  @DisplayName("To set doctor password")
  void setDoctorPassword() {
    docLogin.setDoctorPassword("password");
    assertEquals("password", docLogin.getDoctorPassword(), "doctor password setter fails");
  }

  @Test
  @DisplayName("To execute doctor login")
  void execute() {
    String username="Kazi@gmail.com";
    String pass="k1234";
    Doctor dr = new Doctor(new WelcomePage(), new DoctorPage(username), new DB_Connection(username,pass), new LoginAuthorisation());
    DoctorLogin docLogin = new DoctorLogin(dr,new WelcomePage());
    docLogin.setDoctorName(username);
    docLogin.setDoctorPassword(pass);


    //Fetch and Validate
    dr.fetch(docLogin.getDoctorName(),docLogin.getDoctorPassword());
    dr.validate();

    assertAll("Checking Execute Function",
        () -> assertEquals("Kazi@gmail.com", dr.getUsername()),
        () -> assertEquals("k1234", dr.getPassword()),
        () -> assertTrue(dr.getUsernameStatus()),
        () -> assertTrue(dr.getPasswordStatus()));
  }
}