package com.centrailized_medi_application;

/**
 * @author Monisha J
 * @description : This program has the concrete class.
 * This initiates execution.
 * @params: AboutDoctor abtDr : gets instance of the class holding actual functionality.
 */
public class AboutDoctorPage {
  private AboutDoctor about_doctor;

  AboutDoctorPage(AboutDoctor abtDr) {
    this.about_doctor = abtDr;
  }

  // initiates actual execution for About doctor Tab
  public void display() {
    this.about_doctor.fetchDetails();
    this.about_doctor.displayDetails();
    this.about_doctor.back();
  }
}
