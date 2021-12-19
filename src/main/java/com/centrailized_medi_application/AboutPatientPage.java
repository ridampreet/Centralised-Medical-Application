package com.centrailized_medi_application;

/**
 * @author Neelay Jayantbharti Goswami
 * @description : This program has the concrete class.
 * This initiates execution.
 * @params: AboutPatient abtPatient : gets instance of the class holding actual
 * functionality.
 */
public class AboutPatientPage {
  private AboutPatient about_patient;

  AboutPatientPage(AboutPatient abtPatient) {
    this.about_patient = abtPatient;
  }

  // initiates actual execution for About Patient Tab
  public void display() {
    this.about_patient.fetchDetails();
    this.about_patient.displayDetails();
    this.about_patient.back();
  }
}
