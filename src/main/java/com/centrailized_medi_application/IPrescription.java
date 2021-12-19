package com.centrailized_medi_application;

import java.util.ArrayList;

/**
 * @author Kazi Hasan
 * @description: holds methods required to handle prescriptions written by doctors and displayed by patients
 * getPatientUserName()-used to retrieve patient user name
 * setPatientUserName()-used to declare patient user name
 * setMedicationList()-used to set current list of medications
 * getPrescriptionList()-used to call database layer class to get prescription
 * getMedicationList()-used to get current list of medications
 * getMedicationsByDoctor()-used to get medication information from doctor
 * setMedicationsByDoctor()-used to set current medications from doctor
 */
public interface IPrescription {

  String getPatientUserName();

  void setPatientUserName(String patientUserName);

  void getPrescriptionList(IPrescriptionPersistence prescriptionPersistence);

  ArrayList<ArrayList<String>> getMedicationList();

  void setMedicationList(ArrayList<ArrayList<String>> medicationList);

  ArrayList<ArrayList<String>> getMedicationsByDoctor();

  void setMedicationsByDoctor(ArrayList<ArrayList<String>> medications);

}
