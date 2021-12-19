package com.centrailized_medi_application;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

/**
 * @author Neelay Jayantbharti Goswami
 * @description :This class return all the family member details whose patient
 * email id passed by the registered doctor in our system.
 * This class has methods named getFamilyInfo() displayCurrentPatient()
 * displayMembers()
*/
public class FamilyInfo {
  private ResultSet currentPatientDetails;
  private ResultSet familyDetails;
  private Connection c;
  private String familyCode = "";
  private PreparedStatement prpStmt = null;
  private PreparedStatement prepstmt2 = null;
  DB_Layer layer = null;

  /**
   * This method asks the patient email and on the basis of email
   * patient family code is being fetched.
   * @param email
   * @return family code
   */
  public String getFamilyInfo(String... email) {
    try {
      String patientEmail = "";
      if (email.length == 0) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Patient Email :");
        patientEmail = sc.nextLine();
        //System.out.println(patientEmail);
      } else {
        patientEmail = email[0];
      }
      layer = DB_Layer.singleConnection();
      List<Object> resultState = layer.displayPatientInfo(patientEmail);
      this.currentPatientDetails = (ResultSet) resultState.get(0);
      prpStmt = (PreparedStatement) resultState.get(1);
      displayCurrentPatient();
      List<Object> resultState1 = layer.displayFamilyinfo(familyCode, patientEmail);
      this.familyDetails = (ResultSet) resultState1.get(0);
      prepstmt2 = (PreparedStatement) resultState1.get(1);
      displayMembers();

    } catch (Exception e) {
      System.out.println("Doctor Login error" + e.getMessage());
    }
    return familyCode;
  }

  /**
   * This method displays information related to patient whose email id is
   * passed
   */
  public void displayCurrentPatient() {
    try {
      if (this.currentPatientDetails.next()) {
        System.out.println("**************************** Patient Family Information **************************");
        familyCode = this.currentPatientDetails.getString("familyMemberCode");
        System.out.println("Firstname: " + this.currentPatientDetails.getString("firstname"));
        System.out.println("Lastname: " + this.currentPatientDetails.getString("lastname"));
        System.out.println("Date of birth: " + this.currentPatientDetails.getString("dateofbirth"));
        System.out.println("Gender: " + this.currentPatientDetails.getString("gender"));
        System.out.println("EmailId: " + this.currentPatientDetails.getString("emailId"));
        System.out.println("Address: " + this.currentPatientDetails.getString("address"));
        System.out.println("BloodGroup: " + this.currentPatientDetails.getString("bloodGroup"));
        System.out.println("Allergy: " + this.currentPatientDetails.getString("allergy"));
        System.out.println("ChronicDisease: " + this.currentPatientDetails.getString("chronicDisease"));
        System.out.println("InsuranceNo: " + this.currentPatientDetails.getString("insuranceNo"));
        System.out.println("DonorCardNo: " + this.currentPatientDetails.getString("insuranceNo"));
        System.out.println("Volunteer: " + this.currentPatientDetails.getString("volunteer"));
        System.out.println("*****************************************************************");
      }
      currentPatientDetails.close();
      prpStmt.close();
      layer.close();
    } catch (Exception e) {
      System.out.println("Family Info error" + e.getMessage());
    }
  }

  /**
   * This method displays the family members of the patient who are registered
   * in our system
   */
  public void displayMembers() {
    try {
      int count = 1;
      while (this.familyDetails.next()) {
        System.out.println("**************************** Member " + count + " **************************");
        System.out.println("Firstname: " + this.familyDetails.getString("firstname"));
        System.out.println("Lastname: " + this.familyDetails.getString("lastname"));
        System.out.println("Date of birth: " + this.familyDetails.getString("dateofbirth"));
        System.out.println("Gender: " + this.familyDetails.getString("gender"));
        System.out.println("EmailId: " + this.familyDetails.getString("emailId"));
        System.out.println("Address: " + this.familyDetails.getString("address"));
        System.out.println("BloodGroup: " + this.familyDetails.getString("bloodGroup"));
        System.out.println("Allergy: " + this.familyDetails.getString("allergy"));
        System.out.println("ChronicDisease: " + this.familyDetails.getString("chronicDisease"));
        System.out.println("InsuranceNo: " + this.familyDetails.getString("insuranceNo"));
        System.out.println("DonorCardNo: " + this.familyDetails.getString("insuranceNo"));
        System.out.println("FamilyMemberCode: " + this.familyDetails.getString("familyMemberCode"));
        System.out.println("Volunteer: " + this.familyDetails.getString("volunteer"));
        System.out.println("*****************************************************************");
        count++;
      }

      familyDetails.close();
      prepstmt2.close();
      layer.close();
    } catch (
        Exception e) {
      System.out.println("Family Info error" + e.getMessage());
    }
  }
}
