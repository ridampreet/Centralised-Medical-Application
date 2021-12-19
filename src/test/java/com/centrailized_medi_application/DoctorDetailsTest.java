package com.centrailized_medi_application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoctorDetailsTest {
  private DoctorDetails docReg = new DoctorDetails();

  @Test
  @DisplayName("To get doctor speciality")
  void getSpeciality() {
    docReg.setSpeciality("neurologist");
    assertEquals("neurologist", docReg.getSpeciality(), "speciality getter fails");
  }

  @Test
  @DisplayName("To set doctor speciality")
  void setSpeciality() {
    docReg.setSpeciality("neurologist");
    assertEquals("neurologist", docReg.getSpeciality(), "speciality setter fails");
  }

  @Test
  @DisplayName("To get registration number")
  void getRegistrationNumber() {
    docReg.setRegistrationNumber(1234566);
    assertEquals(1234566, docReg.getRegistrationNumber(), "registration getter fails");
  }

  @Test
  @DisplayName("To set registration number")
  void setRegistrationNumber() {
    docReg.setRegistrationNumber(1234566);
    assertEquals(1234566, docReg.getRegistrationNumber(), "registration setter fails");
  }
}