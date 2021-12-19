package com.centrailized_medi_application;

import java.util.Scanner;

/**
 * @author Monisha J and Ridampreet Singh
 * @description : This program receives input for navigation
 * inside a Doctor Dashboard and routes the pages accordingly
 * It holds the abstract methods that are to be defined to show
 * details specific to the tab selected.
 */

public abstract class DoctorDashboard extends Dashboard {

  //abstract methods that define tab navigations
  public abstract void display_about_doctor();

  public abstract void display_add_patients();

  public abstract void display_donors();

  public abstract void display_pastConsultations();

  public abstract void display_patient_family_history();

  public abstract void display_add_prescription();

  public abstract void Logout();

  protected boolean flag = false;
  protected boolean logout = false;
  protected Scanner sc = new Scanner(System.in);

  /**
   * Method the displays the available tabs and corresponding keys
   * Reads integer option from console and reroutes accordingly
   */
  @Override
  public void display() {
    System.out.println("-----------Dashboard-------------");
    System.out.println("1.About");
    System.out.println("2.Add Patients");
    System.out.println("3.Past Consultations");
    System.out.println("4.Donors");
    System.out.println("5.Patient Family History");
    System.out.println("6.Add Patient Prescription");
    System.out.println("7.Logout");
    System.out.println("----------------------------------");
    System.out.println(" Enter from above options to proceed:");

    while (flag != true) {
      int option = sc.nextInt();
      if (option == 1) {
        this.display_about_doctor();  // navigates to display About Doctor Tab.
        flag = true;
      } else if (option == 2) {
        this.display_add_patients();
        flag = true;
      } else if (option == 3) {
        this.display_pastConsultations();
        flag = true;
      } else if (option == 4) {
        this.display_donors();
        flag = true;
      } else if (option == 5) {
        this.display_patient_family_history();
        flag = true;
      }else if (option == 6) {
        this.display_add_prescription();
        flag = true;
      } else if (option == 7) {
        this.Logout();
        logout = true; //set the logout status true for this user.
        flag = true;
      } else {
        System.out.println("Enter the correct options to proceed");
      }
    }
  }
}
