package com.centrailized_medi_application;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author Monisha J
 * @description : This class in responsible to handle Patients.
 * This retrieves and displays the list of patients consulted the doctor.
 */
public class PastConsultations {
  DbConnection db_access;
  private String docterUsername = null;
  private ArrayList<ArrayList<String>> patientsList = new ArrayList<ArrayList<String>>();
  Connection local = null;

  public PastConsultations(DbConnection connection, String doc_name) {
    db_access = connection;
    docterUsername = doc_name;
    local = db_access.createConnection();
  }

  public void retrievePatientDetails(String patientName, String date) {
    try {
      PreparedStatement prep_statement = local.prepareStatement("SELECT * from " +
          "patient_info WHERE emailId ='" + patientName + "'");
      ResultSet resultSet = prep_statement.executeQuery();
      while (resultSet.next()) {
        ArrayList<String> resultRow = new ArrayList<String>();
        resultRow.add(resultSet.getString("firstname"));
        resultRow.add(resultSet.getString("lastname"));
        resultRow.add(date);
        resultRow.add(resultSet.getString("id"));
        resultRow.add(resultSet.getString("bloodGroup"));
        resultRow.add(resultSet.getString("contactNo"));
        patientsList.add(resultRow);
      }

      resultSet.close();
      prep_statement.close();
    } catch (Exception e) {
      System.out.println("Past Consultation " + e.getMessage());
    }
  }

  public void retrievePatients() {
    try {
      PreparedStatement prep_statement = local.prepareStatement("SELECT consultation_date_and_time, patient_username " +
          "from " +
          "consultations WHERE doctor_username ='" + docterUsername + "' GROUP BY patient_username");
      ResultSet resultSet = prep_statement.executeQuery();
      while (resultSet.next()) {
        retrievePatientDetails(resultSet.getString("patient_username"), resultSet.getString("consultation_date_and_time"));
      }

      resultSet.close();
      prep_statement.close();
    } catch (Exception e) {
      System.out.println("Past Consultations error " + e.getMessage());
    }
  }

  public String getPreviousConsultations() {
    StringBuilder pList = new StringBuilder();
    try {
      this.retrievePatients();
      pList.append("PAST CONSULTATIONS\n");

      for (ArrayList<String> patient : patientsList) {
        String singlePatient = "*******************************************" + "\n" +
            "First Name: " + patient.get(0) + "\n" +
            "Last Name: " + patient.get(1) + "\n" +
            "Last Consultation: " + patient.get(2) + "\n" +
            "Patient ID: " + patient.get(3) + "\n" +
            "Blood Group: " + patient.get(4) + "\n" +
            "Contact No: " + patient.get(5) + "\n" +
            "\n";
        pList.append(singlePatient);
      }
      this.local.close();
    } catch (Exception throwables) {
      throwables.printStackTrace();
    }
    return pList.toString();
  }

}
