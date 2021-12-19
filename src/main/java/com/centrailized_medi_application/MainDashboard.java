package com.centrailized_medi_application;

/*Importing Modules*/

import java.util.Scanner;

/**
 * @author Aditya Jain
 * @description : MainDashboard is an abstract class which extends Dashboard
 * It contains function declaration such as displayPatientLogin(), displayPatientRegistration(),
 * displayDoctorRegistration(), displayDoctorLogin()
 */
public abstract class MainDashboard extends Dashboard {
  public abstract void displayPatientLogin();

  public abstract void displayDoctorLogin();

  public abstract void displayDoctorRegistration();

  public abstract void displayPatientRegistration();

  protected boolean flag = false;
  protected Scanner sc = new Scanner(System.in);

  public void display() {

    System.out.println("\n*****Centralized Medi-Application*****\n");
    System.out.println("-----------Main Menu-------------");
    System.out.println(" 1.Register as a Patient");
    System.out.println(" 2.Login as a Patient");
    System.out.println(" 3.Register as a Doctor");
    System.out.println(" 4.Login as a Doctor");
    System.out.println(" 5.Exit");
    System.out.println("----------------------------------");
    System.out.println(" Enter from above options to proceed:");

    while (flag != true) {
      int option = sc.nextInt();
      if (option == 1) {
        this.displayPatientRegistration();        // Directs to Patient Registration
        flag = true;
      } else if (option == 2) {
        this.displayPatientLogin();               // Directs to Patient Login
        flag = true;
      } else if (option == 3) {
        this.displayDoctorRegistration();         // Directs to Doctor Registration
        flag = true;
      } else if (option == 4) {
        this.displayDoctorLogin();                // Directs to Doctor Login
        flag = true;
      } else if (option == 5) {                   // Exits the program
        System.exit(0);
      } else {
        System.out.println("Enter the correct options to proceed");
      }
    }
  }
}