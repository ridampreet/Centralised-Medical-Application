package com.centrailized_medi_application;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Neelay Jayantbharti Goswami
 * @description : This program performs the actual registration of Patient.
 * @params : Details grouped as BasicDetails, PatientDetails,
 * SecurityQuestions.
 * MainDashboard, which are used to handle changes after a login failure or
 * success respectively.
 */
public class NewPatient implements Details, Registration {
  protected boolean hasRegisteredSuccessfully = false;
  MainDashboard init;
  private BasicDetails basicDetails;
  private PatientDetails patientDetails;
  private SecurityQuestions securityQuestions;

  NewPatient(BasicDetails basic, PatientDetails pdetails, SecurityQuestions securityqs, MainDashboard main) {
    this.basicDetails = basic;
    this.patientDetails = pdetails;
    this.securityQuestions = securityqs;
    this.init = main;
  }

  public boolean getRegistrationStatus() {
    return this.hasRegisteredSuccessfully;
  }

  /**
   * This method will take input for all types of details from the new
   * user.
   */
  @Override
  public void getDetails() {
    this.basicDetails.getDetails();
    this.patientDetails.getDetails();
    this.securityQuestions.getDetails();
  }

  /**
   * This method updates the entered details on successfully registering
   */
  @Override
  public void update() {
    // Update details to patient table
    try {
      DB_Layer layer = DB_Layer.singleConnection();
      layer.insertNewPatient(basicDetails, patientDetails, securityQuestions);
      this.hasRegisteredSuccessfully = true;
      layer.close();
    } catch (SQLException | IOException | ClassNotFoundException e) {
      System.out.println("Patient Registration Error " + e.getMessage());
    }
  }

  /**
   * This method check if the details were updated successfully.
   * If yes, moves to Pateint Login page.
   * Else moves to Main Menu.
   */
  @Override
  public void action() {
    // check if the details were updated successfully
    if (this.hasRegisteredSuccessfully) {
      String firstName = this.basicDetails.getFirstName();
      System.out.println(firstName + " you have registered successfully. " +
          "You can now login and access your dashboard"); // name to be replaced with the actual name stored in db
      this.init.displayPatientLogin();
    } else {
      System.out.println("Unable to register. Please try again!");
      System.out.println("Navigating to main menu...");
      this.init.display();
    }
  }
}
