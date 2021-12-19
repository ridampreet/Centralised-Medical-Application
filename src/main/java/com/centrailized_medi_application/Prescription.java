package com.centrailized_medi_application;

import java.util.ArrayList;

/**
 * @author Kazi Hasan
 * @description: Implements methods required to contain all prescription related information
 */
public class Prescription implements IPrescription {
  private ArrayList<ArrayList<String>> medicationList;
  private String patientUserName;
  private ArrayList<ArrayList<String>> medications;

  /* given patientID, return prescription details from DB
   * connect to DB
   * execute sql query
   * return DB query output */
  public Prescription() {
  }

  /**
   * This method is used for retrieving current patient user name
   *
   * @return patientUserName- current patient user name
   * @Param None
   */
  public String getPatientUserName() {
    return patientUserName;
  }

  /**
   * This method is used for setting patient username. This is needed by doctors
   * to give specific patients the required prescription.
   *
   * @return None
   * @Param patientUserName- current patient user name
   */
  public void setPatientUserName(String patientUserName) {
    this.patientUserName = patientUserName;
  }

  /**
   * This method is used for getting current patient list of medications
   *
   * @return medicationList- current medications as a list
   * @Param None
   */
  public ArrayList<ArrayList<String>> getMedicationList() {
    return medicationList;
  }

  /**
   * This method is used for referencing the current medication list for patient
   *
   * @return None
   * @Param medicationList- medication list retrieved from the database
   */
  public void setMedicationList(ArrayList<ArrayList<String>> medicationList) {
    this.medicationList = medicationList;
  }

  /**
   * This method is used for retrieving the list of medications from database
   *
   * @return None
   * @Param prescriptionPersistence- interface used for loading prescription from database
   */
  public void getPrescriptionList(IPrescriptionPersistence prescriptionPersistence) {
    prescriptionPersistence.loadPrescription(this);
  }

  /**
   * This method is used for retrieving the list of medications from database
   *
   * @return medications- list of medications from doctor user input
   * @Param None
   */
  public ArrayList<ArrayList<String>> getMedicationsByDoctor() {
    return medications;
  }

  /**
   * This method is used for setting the current medication list by a doctor
   *
   * @return None
   * @Param medications- list of medications from doctor user input
   */
  public void setMedicationsByDoctor(ArrayList<ArrayList<String>> medications) {
    this.medications = medications;
  }

  /**
   * This method is used for saving prescription given by doctor
   *
   * @return None
   * @Param prescriptionPersistence- interface used for saving prescription into database
   */
  public void saveMedicationList(IPrescriptionPersistence prescriptionPersistence) {
    prescriptionPersistence.savePrescription(this);
  }

}
