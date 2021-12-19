package com.centrailized_medi_application;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author Monisha J and Ridampreet Singh
 * @description : This program performs the actual registration of Doctor.
 * @params : Details grouped as BasicDetails, DoctorDetails, SecurityQuestions.
 * MainDashboard, which are used to handle changes after a login failure or success respectively.
 */
public class NewDoctor implements Details, Registration {

  protected boolean hasRegisteredSuccessfully = false;
  MainDashboard init;
  private BasicDetails basicDetails;
  private DoctorDetails doctorDetails;
  private SecurityQuestions securityQuestions;

  NewDoctor(BasicDetails bd, DoctorDetails dd, SecurityQuestions sq, MainDashboard main) {
    this.basicDetails = bd;
    this.doctorDetails = dd;
    this.securityQuestions = sq;
    this.init = main;
  }

  /**
   * get the registration status.
   * @return
   */

  public boolean getRegistrationStatus() {
    return this.hasRegisteredSuccessfully;
  }

  /**
   * This method get all categories of details from the new user.
   */
  @Override
  public void getDetails() {
    this.basicDetails.getDetails();
    this.doctorDetails.getDetails();
    this.securityQuestions.getDetails();
  }

  /**
   * This method updates the entered details on registering
   */
  @Override
  public void update() {
    try {
      DB_Layer layer = DB_Layer.singleConnection();
      layer.insertNewDoctor(basicDetails, doctorDetails, securityQuestions);
      this.hasRegisteredSuccessfully = true;
      layer.close();
    } catch (SQLException | IOException | ClassNotFoundException e) {
      System.out.println("Doctor Registration Error " + e.getMessage());
    }
  }

  /**
   * This method check if the details were updated successfully.
   * If yes, moves to Doctor Login page.
   * Else moves to Main Menu.
   */
  @Override
  public void action() {
    if (this.hasRegisteredSuccessfully) {
      String firstName = this.basicDetails.getFirstName();
      System.out.println(firstName + " you have registered successfully. You can now login and access your dashboard"); // name to be replaced with the actual name stored in db
      this.init.displayDoctorLogin();
    } else {
      System.out.println("Unable to register. Please try again!");
      System.out.println("Navigating to main menu...");
      this.init.display();
    }
  }
}
