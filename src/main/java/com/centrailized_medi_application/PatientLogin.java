package com.centrailized_medi_application;

/*Importing modules*/

import java.util.Scanner;

/**
 * @author Aditya Jain
 * @description: This class in responsible to handle Patient login
 * PatientLogin implements from LoginCommand (Part of Command Design Pattern)
 * The confirmation() handles the control based on User's input. It may trigger execute()
 * or trigger dashboard event based on the logic.The execute() encapsulates events like fetching details,
 * validation details and finally authenticating it.
 */
public class PatientLogin extends LoginCommand {

  private Login patientLogin;      // Login Patient interface
  private MainDashboard mainPage;  // Main Dashboard interface
  private String patientName;      // Stores patient name
  private String patientPass;      // Stores patient pass

  /**
   * Constructor with Login and MainDashboard as input parameters
   *
   * @Param loginInterface as Login Interface
   * @Param mainInterface as MainDashboard Interface
   */
  public PatientLogin(Login loginInterface, MainDashboard mainInterface) {
    patientLogin = loginInterface;
    mainPage = mainInterface;
  }

  /**
   * This method sets the Patient Username (emailId).
   * If the username is valid, it sets the name else it will ask to re-enter the details
   *
   * @return void
   * @Param String patientUsername - This is the only input parameter
   */
  public void setPatientName(String patientUsername) {

    try {
      if (patientUsername.contains("@") && patientUsername.contains(".com")) {
        patientName = patientUsername;
      } else {
        System.out.println("Expecting Email id");
        System.out.println("Re-enter you details");
        mainPage.displayPatientLogin();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * This method sets the Patient Password.
   *
   * @return void
   * @Param String patientPasswd This is the only input parameter
   */
  public void setPatientPass(String patientPasswd) {
    patientPass = patientPasswd;
  }

  /**
   * This method returns the Patient Name (emailId).
   *
   * @return String patientName Name of the Patient
   * @Param None
   */
  public String getPatientName() {
    return patientName;
  }

  /**
   * This method returns the Patient Name (emailId).
   *
   * @return String patientPass Password of the Patient
   * @Param None
   */
  public String getPatientPass() {
    return patientPass;
  }

  /**
   * This method triggers the functionality like fetch(), validate() and authenticate().
   *
   * @return None
   * @Param None
   */
  @Override
  protected void execute() {
    try {
      patientLogin.fetch(this.patientName, this.patientPass);
      patientLogin.validate();
      patientLogin.authenticate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method directs the patient to his/her dashboard if credentials are correct
   * based on their confirmation else they are directed to the main welcome page
   *
   * @return None
   * @Param None
   */
  @Override
  public void confirmation() {
    System.out.println("Please enter 1 to submit or any other option to revert");
    Scanner sc = new Scanner(System.in);
    try {
      if (sc.nextInt() == 1) {
        this.execute();
      } else {
        System.out.println("Are you sure you want to cancel action, please enter y/n to confirm");
        sc = new Scanner(System.in);
        if (sc.nextLine().equals("y")) {
          System.out.println("Navigating to main menu...");
          this.mainPage.display();
        } else {
          System.out.println("Logging in....");
          this.execute();
        }
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      ;
    }
  }
}
