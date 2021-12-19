package com.centrailized_medi_application;

import java.util.Scanner;

/**
 * @author Monisha J
 * @description : This program initiates registration of Doctor.
 * @params : MainDashboard which are used to handle changes after a confirmation.
 * New Doctor instance used to perform functionality.
 */
public class DoctorRegistration extends LoginCommand {

  NewDoctor doctor;
  MainDashboard init;

  public DoctorRegistration(NewDoctor doc, MainDashboard init) {
    this.doctor = doc;
    this.init = init;
  }

  public void start() {
    this.doctor.getDetails();
    this.confirmation();
  }

  @Override
  public void execute() {
    this.doctor.update();
    this.doctor.action();
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


