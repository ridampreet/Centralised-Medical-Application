package com.centrailized_medi_application;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Kazi Hasan
 * @description: concrete class to contain all information needed for suggesting doctors
 */
public class PatientSuggestions implements IPatientSuggestions {
  private String patientUserName;
  private double latitude;
  private double longitude;
  private String specialization;
  private ArrayList<ArrayList<String>> doctorsList = new ArrayList<ArrayList<String>>();

  public PatientSuggestions(String patientUserName) {
    this.patientUserName = patientUserName;
  }

  /**
   * This method is used for getting current patient user name
   *
   * @return None
   * @Param prescriptionPersistence- interface used for loading prescription from database
   */
  public String getPatientUserName() {
    return patientUserName;
  }

  /**
   * This method is used for getting current patient latitude
   *
   * @return latitude- current patient latitude
   * @Param None
   */
  public double getLatitude() {
    return latitude;
  }

  /**
   * This method is used for setting current patient latitude
   *
   * @return None
   * @Param latitude- current patient latitude
   */
  public void setLatitude(double latitude) {
    this.latitude = latitude;
  }

  /**
   * This method is used for getting current patient longitude
   *
   * @return longitude- current patient longitude
   * @Param None
   */
  public double getLongitude() {
    return longitude;
  }

  /**
   * This method is used for setting current patient longitude
   *
   * @return None
   * @Param latitude- current patient longitude
   */
  public void setLongitude(double longitude) {
    this.longitude = longitude;
  }

  /**
   * This method is used for setting current patient lat/lon list
   *
   * @return None
   * @Param suggestionsPersistence- interface which is used for loading current patient
   * lat/lon from database
   */
  public void getPatientLatLon(ISuggestionsPersistence suggestionsPersistence) {
    suggestionsPersistence.loadPatientLatLon(this);
  }

  /**
   * This method is used for getting specialization required by current patient
   *
   * @return specialization- current specialization required by current paient
   * @Param None
   */
  public String getSpecialization() {
    return specialization;
  }

  /**
   * This method is used for setting specialization required by current patient
   *
   * @return None
   * @Param specialization- current specialization required by current paient
   */
  public void setSpecialization(String specialization) {
    this.specialization = specialization;
  }

  /**
   * This method is used for getting list of doctors set for current patient
   *
   * @return doctorsList- current doctor list
   * @Param None
   */
  public ArrayList<ArrayList<String>> getDoctorsList() {
    return doctorsList;
  }

  /**
   * This method is used for setting current list of doctors
   *
   * @return None
   * @Param doctorsList- list of doctors for current patient
   */
  public void setDoctorsList(ArrayList<ArrayList<String>> doctorsList) {
    this.doctorsList = doctorsList;
  }

  /**
   * This method is used for retrieving suggested list of doctors from database
   *
   * @return suggestionsPersistence- interface to filter doctors
   * @Param None
   */
  public void getSuggestedDoctors(ISuggestionsPersistence suggestionsPersistence) {
    suggestionsPersistence.loadDoctorSuggestions(this);
  }

  public boolean rateDoctor() {
    try {
      DB_Layer layer = DB_Layer.singleConnection();
      List<Object> resultState = layer.feedRatings(patientUserName);
      ResultSet result1 = (ResultSet) resultState.get(0);
      ResultSet result2 = (ResultSet) resultState.get(1);
      PreparedStatement prepStmt = (PreparedStatement) resultState.get(2);

      PatientPage pd = new PatientPage(this.patientUserName);
      pd.display();

      result1.close();
      result2.close();
      prepStmt.close();
      layer.close();
    } catch (SQLException | IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return true;
  }

}