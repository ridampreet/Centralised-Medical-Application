package com.centrailized_medi_application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

/**
 * @author Monisha J, Ridampreet Singh, Neelay Jayantbharti Goswami
 * @description : This program extends DoctorDashboard.
 * Holds abstract methods to define actual routing classes of tabs.
 * @params : int doctor_id : passed after successful login
 */
public class DoctorPage extends DoctorDashboard {

  protected String doctorUsername; //gives id of logged in doctor


  /**
   * Constructor that maintains the username session of the doctor logged in
   *
   * @param username
   */
  DoctorPage(String username) {
    this.doctorUsername = username;
  }

  /**
   * This method navigates to About tab of the Doctor logged in.
   * Displays details of the particular doctor.
   */
  @Override
  public void display_about_doctor() {
    AboutDoctor abtDr = new AboutDoctor(this.doctorUsername, this); //testing commit
    AboutDoctorPage abtDrPage = new AboutDoctorPage(abtDr);
    abtDrPage.display();
  }

  // navigates to Add Patients Tab
  @Override
  public void display_add_patients() {
    try {
      System.out.println("Enter a Patient Name to register");
      Scanner sc = new Scanner(System.in);
      String patient_name = sc.next();
      AddPatient newEntry = new AddPatient(this.doctorUsername);
      newEntry.link_patient(patient_name);
      this.display();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * This method navigates to Patients Tab and displays the list of past consultations
   * Patients and their details consulted the particular doctor.
   */
  @Override
  public void display_pastConsultations() {
    try {
      PastConsultations pastPatients = new PastConsultations(new DB_Connection(), this.doctorUsername);
      System.out.println(pastPatients.getPreviousConsultations());
      this.display();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }



  /**
   * Method gets a list of students who have agreed to donate the organs and other types of donation swhile registering.
   */
  @Override
  public void display_donors() {
    try {
      DB_Layer layer = DB_Layer.singleConnection();
      List<Object> resultState = layer.fetchDonors();
      ResultSet exec_get_donors = (ResultSet) resultState.get(0);
      PreparedStatement prdStatement = (PreparedStatement) resultState.get(1);
      while (exec_get_donors.next()) {
        System.out.println(exec_get_donors.getInt("Id") + "\t" + exec_get_donors.getString("firstname") + "\t" + exec_get_donors.getString("lastname") + "\t" + exec_get_donors.getString("bloodGroup"));
      }

      exec_get_donors.close();
      prdStatement.close();
      layer.close();
      this.display();
    } catch (Exception e) {
      System.out.println("Display donors" + e.getMessage());
    }
  }

  /**
   * Method displays the family history of a patient
   */

  @Override
  public void display_patient_family_history() {
    FamilyInfo family_info = new FamilyInfo();
    family_info.getFamilyInfo();
    this.display();

  }

  @Override
  public void display_add_prescription(){
    Prescription prescription = new Prescription();
    PrescriptionPL.addPrescription(prescription);
    PrescriptionDL prescriptionDL = new PrescriptionDL();
    prescription.saveMedicationList(prescriptionDL);
    this.display();

  }

  //Logs out the currently signed in user out of the portal
  /**
   * Logs the currently logged in user and redirects to the main dashboard of the application.
   */
  @Override
  public void Logout() {
    System.out.println("User has been successfuly logged out !");
    WelcomePage back_to_menu = new WelcomePage();
    back_to_menu.display();
  }


}
