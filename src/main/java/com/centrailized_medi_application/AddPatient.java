package com.centrailized_medi_application;

/*Importing Modules*/

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Aditya Jain & Ridampreet Singh
 * @description : This class implements the the feature of linking a patient with doctor's name.
 * Whenever a Patient visits the doctor, the doctor will add a patient (through his portal), which will record the
 * date and time of the consultation. The will later help the patient to rate the doctor as per their
 * experience.
 */

public class AddPatient {
  private boolean patientExist = false;
  private boolean[] credentials = new boolean[2];
  private boolean registered = false;
  private String patientUserName = null;
  private String doctorUserName;
  DB_Layer layer;

  ResultSet login_name;
  ResultSet login_pass;
  ResultSet res_check_for_doc;
  PreparedStatement p1;
  PreparedStatement p2;
  PreparedStatement check_for_doc;

  /**
   * Constructor with DbConnection and String as input parameters
   *
   * @Param connection as DbConnection Interface
   * @Param docName as doctor's username
   */
  public AddPatient(String docName) {
    doctorUserName = docName;
  }

  /**
   * This method checks whether a Patient is registered to the system or not.
   * Because only registered patient can be linked under doctor's profile.
   *
   * @return boolean
   * @Param patientName
   */
  boolean patientPresence(String patientName) {
    try {
      patientUserName = patientName;
      this.layer = DB_Layer.singleConnection();
      List<Object> resultState = layer.getCredStatus(patientUserName, null);
      this.credentials = (boolean[]) resultState.get(0);
      login_name = (ResultSet) resultState.get(1);
      login_pass = (ResultSet) resultState.get(2);
      res_check_for_doc = (ResultSet) resultState.get(3);
      p1 = (PreparedStatement) resultState.get(4);
      p2 = (PreparedStatement) resultState.get(5);
      check_for_doc = (PreparedStatement) resultState.get(6);
      login_name.close();
      if (login_pass != null) {
        login_pass.close();
      }
      if (res_check_for_doc != null) {
        res_check_for_doc.close();
      }
      p1.close();
      if (p2 != null) {
        p2.close();
      }
      if (check_for_doc != null) {
        check_for_doc.close();
      }
      layer.close();

      if (credentials[0] != false) {
        this.patientExist = true;
      }
    } catch (SQLException | IOException | ClassNotFoundException e) {
      System.out.println("Add Patient error " + e.getMessage());
    }
    return patientExist;
  }

  /**
   * This method checks whether a Patient is registered to the system or not.
   * Because only registered patient can be linked under doctor's profile.
   *
   * @return boolean
   * @Param patientName
   */
  public boolean link_patient(String patient_name) {
    try {
      DateTimeFormatter consultation_date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
      LocalDateTime current_time = LocalDateTime.now();
      if (this.patientPresence(patient_name)) {
        this.layer = DB_Layer.singleConnection();
        layer.insertConsultations(doctorUserName, patientUserName, consultation_date, current_time);
        System.out.println("Added Successfully!");
        registered = true;
        layer.close();
      } else {
        System.out.println("Error Patient is not registered into the system!");
        layer.close();
      }
    } catch (SQLException | IOException | ClassNotFoundException e) {
      System.out.println("Add Patient error " + e.getMessage());
    }
    return registered;
  }
}
