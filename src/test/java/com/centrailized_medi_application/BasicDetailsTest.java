package com.centrailized_medi_application;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class BasicDetailsTest {

  private BasicDetails bd = new BasicDetails();

  @Test
  @DisplayName("To get firstName")
  void getFirstName() {
    bd.setFirstName("testFirstName1");
    assertEquals("testFirstName1", bd.getFirstName(), "firstName getter fails");
  }

  @Test
  @DisplayName("To set firstName")
  void setFirstName() {
    bd.setFirstName("testFirstName2");
    assertEquals("testFirstName2", bd.getFirstName(), "firstName setter fails");
  }

  @Test
  @DisplayName("To get firstName")
  void getLastName() {
    bd.setLastName("testLastName1");
    assertEquals("testLastName1", bd.getLastName(), "lastName getter fails");
  }

  @Test
  @DisplayName("To set lastName")
  void setLastName() {
    bd.setLastName("testLastName2");
    assertEquals("testLastName2", bd.getLastName(), "lastName setter fails");
  }

  @Test
  @DisplayName("To get gender")
  void getGender() {
    bd.setGender("Male");
    assertEquals("Male", bd.getGender(), "gender getter fails");
  }

  @Test
  @DisplayName("To set gender")
  void setGender() {
    bd.setGender("Female");
    assertEquals("Female", bd.getGender(), "gender setter fails");
  }

  @Test
  @DisplayName("To get DOB")
  void getDob() {
    bd.setDob("MAY 3 2000");
    assertEquals("MAY 3 2000", bd.getDob(), "DOB getter fails");
  }

  @Test
  @DisplayName("To set DOB")
  void setDob() {
    bd.setDob("May 4 2001");
    assertEquals("May 4 2001", bd.getDob(), "DOB setter fails");
  }

  @Test
  @DisplayName("To get address")
  void getAddress() {
    bd.setAddress("Address 1 street 1 city");
    assertEquals("Address 1 street 1 city", bd.getAddress(), "address getter fails");
  }

  @Test
  @DisplayName("To set address")
  void setAddress() {
    bd.setAddress("Address 2 street 2 city");
    assertEquals("Address 2 street 2 city", bd.getAddress(), "address setter fails");
  }

  @Test
  @DisplayName("To get latitude")
  void getLatitude() {
    bd.setLatitude(11);
    assertEquals(11, bd.getLatitude(), "latitude getter fails");
  }

  @Test
  @DisplayName("To set latitude")
  void setLatitude() {
    bd.setLatitude(12);
    assertEquals(12, bd.getLatitude(), "latitude setter fails");
  }

  @Test
  @DisplayName("To get longitude")
  void getLongitude() {
    bd.setLongitude(10);
    assertEquals(10, bd.getLongitude(), "longitude getter fails");
  }

  @Test
  @DisplayName("To set longitude")
  void setLongitude() {
    bd.setLongitude(21);
    assertEquals(21, bd.getLongitude(), "longitude setter fails");
  }

  @Test
  @DisplayName("To get contact")
  void getContactNo() {
    bd.setContactNo("9966223344");
    assertEquals("9966223344", bd.getContactNo(), "contact getter fails");
  }

  @Test
  @DisplayName("To set contact")
  void setContactNo() {
    bd.setContactNo("8844335566");
    assertEquals("8844335566", bd.getContactNo(), "contact setter fails");
  }

  @Test
  @DisplayName("To get email")
  void getEmailId() {
    bd.setEmailId("testEmail@123.in");
    assertEquals("testEmail@123.in", bd.getEmailId(), "email getter fails");
  }

  @Test
  @DisplayName("To set email")
  void setEmailId() {
    bd.setEmailId("testEmail@123.in");
    assertEquals("testEmail@123.in", bd.getEmailId(), "email setter fails");
  }

  @Test
  @DisplayName("To get password")
  void getPassword() {
    bd.setPassword("testPassword");
    assertEquals("testPassword", bd.getPassword(), "password getter fails");
  }

  @Test
  @DisplayName("To set password")
  void setPassword() {
    bd.setPassword("testPassword");
    assertEquals("testPassword", bd.getPassword(), "password setter fails");
  }

  @Test
  @DisplayName("To get confirm password")
  void getConfirmPassword() {
    bd.setConfirmPassword("testConfirmPassword");
    assertEquals("testConfirmPassword", bd.getConfirmPassword(), "ConfirmPassword getter fails");
  }

  @Test
  @DisplayName("To set confirm password")
  void setConfirmPassword() {
    bd.setConfirmPassword("testConfirmPassword");
    assertEquals("testConfirmPassword", bd.getConfirmPassword(), "ConfirmPassword setter fails");
  }
}