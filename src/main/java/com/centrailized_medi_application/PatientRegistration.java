package com.centrailized_medi_application;

import java.util.Scanner;

/**
 * @author Neelay Jayantbharti Goswami
 * @description : This program initiates registration of New Patient.
 * @params : MainDashboard which are used to handle changes after a confirmation.
 * NewPatient instance used to perform functionality.
 */
public class PatientRegistration extends LoginCommand {
  NewPatient patient;
  MainDashboard init;

  public PatientRegistration(NewPatient patient_obj, MainDashboard init) {
    this.patient = patient_obj;
    this.init = init;
  }

  /**
   * This method deals with starting the process of taking information from
   * patient for there details through getDetails method of NewPatient Class
   */
  public void start() {
    this.patient.getDetails();
    this.confirmation();
  }

  /**
   * If user confirms that they are satisfied with the entered details
   * from confirmation method they will be directed here and here update
   * method and action method will be called to complete registration process
   */
  @Override
  public void execute() {
    this.patient.update();
    this.patient.action();
  }

  /**
   * This method gets confirmation from the user to proceed with the action.
   * If user enters 1 and confirms, he will be registered.
   * Else function will aborted and user will be navigated to Main Menu.
   */
  @Override
  public void confirmation() {
    System.out.println("Please enter 1 to register or any other option to revert");
    Scanner sc = new Scanner(System.in);
    if (sc.nextInt() == 1) {
      this.execute();
    } else {
      System.out.println("Are you sure you want to cancel registration, please enter y/n to confirm");
      sc = new Scanner(System.in);
      if (sc.nextLine().equals("y")) {
        System.out.println("Navigating to main menu...");
        this.init.display();
      } else {
        System.out.println("Registration in progress....");
        this.execute();
      }
    }
  }
}





