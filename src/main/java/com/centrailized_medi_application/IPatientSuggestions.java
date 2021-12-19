package com.centrailized_medi_application;

import java.util.ArrayList;

/**
 * @author Kazi Hasan
 * @description: holds methods required to handle retrieval of suggested doctors list
 * getPatientUserName()-used to retrieve patient user name
 * getLatitude()-used to get current patient latitude
 * getLongitude()-used to get current patient longitude
 * setLatitude()-used to set current patient latitude after retrieval from database
 * setLongitude()-used to set current patient longitude after retrieval from database
 * getSpecialization()-used to get current specialization set by patient
 * setSpecialization()-used to set current specialization to search for
 * getDoctorsList()-get current list of doctors that are suggested
 * setDoctorsList()-used to set retrieved doctor list from database to current doctor list
 */
public interface IPatientSuggestions {

  String getPatientUserName();

  double getLatitude();

  void setLatitude(double latitude);

  double getLongitude();

  void setLongitude(double longitude);

  String getSpecialization();

  void setSpecialization(String specialization);

  ArrayList<ArrayList<String>> getDoctorsList();

  void setDoctorsList(ArrayList<ArrayList<String>> doctorsList);


}
