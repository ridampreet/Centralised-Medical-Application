package com.centrailized_medi_application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * @author Kazi Hasan
 * @description: loads required information from database for suggesting required list of doctors
 */
public class PatientSuggestionsDL implements ISuggestionsPersistence {

  /**
   * This method is used for loading patient latitude and longitude from database
   *
   * @return None
   * @Param patientSuggestions- interface for database layer
   */
  @Override
  public void loadPatientLatLon(IPatientSuggestions patientSuggestions) {
    String patientUserName = patientSuggestions.getPatientUserName();

    try {
      DbConnection db_connection = new DB_Connection();
      Connection connection = db_connection.createConnection();
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("SELECT * FROM patient_info\n" +
          "WHERE patient_info.emailID = '" + patientUserName + "';");

      while (resultSet.next()) {
        patientSuggestions.setLatitude(resultSet.getDouble("latitude"));
        patientSuggestions.setLongitude(resultSet.getDouble("longitude"));
      }

      resultSet.close();
      statement.close();
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  /**
   * This method is used for retrieving a list of doctors ordered by their average rating
   * who are specialized in a field that is set by current patient and are in a radius of 10km
   *
   * @return None
   * @Param patientSuggestions- interface to handle database operattions
   */
  @Override
  public void loadDoctorSuggestions(IPatientSuggestions patientSuggestions) {
    try {
      ArrayList<ArrayList<String>> doctorsList = new ArrayList<ArrayList<String>>();

      DbConnection db_connection = new DB_Connection();
      Connection connection = db_connection.createConnection();

      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery("WITH D_table AS\n" +
          "\t(SELECT firstname,lastname,speciality,address, SQRT(POWER(ABS(latitude-" + patientSuggestions.getLatitude() +
          ")*110.574,2)+POWER(ABS(111.320*COS(latitude*0.0174532925)-111.320*COS(" + patientSuggestions.getLatitude() +
          "*0.0174532925)),2)) as distance,rating,contactNo from doctor_info\n" +
          "\tWHERE speciality = '" + patientSuggestions.getSpecialization() + "') \n" +
          "SELECT * FROM D_table\n" +
          "WHERE distance < 10\n" +
          "ORDER BY rating DESC;");

      NumberFormat formatter = new DecimalFormat("#0.0");
      while (resultSet.next()) {
        ArrayList<String> resultRow = new ArrayList<String>();
        resultRow.add(resultSet.getString("firstname"));
        resultRow.add(resultSet.getString("lastname"));
        resultRow.add(resultSet.getString("speciality"));
        resultRow.add(resultSet.getString("address"));
        resultRow.add(String.valueOf((formatter.format(resultSet.getDouble("distance")))));
        resultRow.add(String.valueOf(resultSet.getDouble("rating")));
        resultRow.add(resultSet.getString("contactNo"));
        doctorsList.add(resultRow);
      }

      resultSet.close();
      statement.close();
      connection.close();

      patientSuggestions.setDoctorsList(doctorsList);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }


}
