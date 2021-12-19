package com.centrailized_medi_application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/**
 * @author Monisha J
 * @description : This program implements About Interface.
 * To fetch and display details of a doctor.
 * Overrides the methods defining structure of execution.
 * @params : int doctor_id : gets username of the doctor who logged in.
 * DoctorDashboard doctor_dashboard : this triggers return to users dashboard.
 */
public class AboutDoctor implements About {

  DoctorDashboard init;
  private String doctorUsername; //holds the give doctor username.
  private ResultSet currentDoctorDetails; // holds the db results fetched
  private PreparedStatement prepStmt;
  DB_Layer layer;

  AboutDoctor(String doctor_id, DoctorDashboard doctor_dashboard) {
    this.doctorUsername = doctor_id;
    this.init = doctor_dashboard;
  }

  //fetch user details from DB
  @Override
  public void fetchDetails() {
    try {
      this.layer = DB_Layer.singleConnection();
      System.out.println("Loading About...\n");
      List<Object> resultState = layer.getUserDetails(doctorUsername, "Doctor");//call to the DB layer.

      this.currentDoctorDetails = (ResultSet) resultState.get(0);
      this.prepStmt = (PreparedStatement) resultState.get(1);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  //display user details
  @Override
  public void displayDetails() {
    try {
      while (this.currentDoctorDetails.next()) {
        System.out.println("**************************** About You **************************");
        System.out.println("Username(E-mail): " + this.currentDoctorDetails.getString("emailId"));
        System.out.println("Id: " + this.currentDoctorDetails.getString("id"));
        System.out.println("Firstname: " + this.currentDoctorDetails.getString("firstname"));
        System.out.println("Lastname: " + this.currentDoctorDetails.getString("lastname"));
        System.out.println("Gender: " + this.currentDoctorDetails.getString("gender"));
        System.out.println("Date of birth: " + this.currentDoctorDetails.getString("dateOfBirth"));
        System.out.println("Registration Number: " + this.currentDoctorDetails.getString("registrationNumber"));
        System.out.println("Specilaity: " + this.currentDoctorDetails.getString("speciality"));
        System.out.println("Address: " + this.currentDoctorDetails.getString("address"));
        System.out.println("Contact: " + this.currentDoctorDetails.getString("contactNo"));
        System.out.println("*****************************************************************");
      }

      currentDoctorDetails.close();
      prepStmt.close();
      layer.close();
    } catch (SQLException s) {
      System.out.println(s.getMessage());
    }
  }

  //handles navigation to dashboard
  @Override
  public void back() {
    try {
      System.out.println("Press 1 to move to your Dashboard");
      Scanner sc = new Scanner(System.in);
      int option = sc.nextInt();
      if (option == 1) {
        System.out.println("Returning to your Dashboard...");
        this.init.display();
      } else {
        System.out.println("Please provide a valid entry");
        this.back();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}