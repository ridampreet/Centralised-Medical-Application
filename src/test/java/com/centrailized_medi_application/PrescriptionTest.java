package com.centrailized_medi_application;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class PrescriptionTest {

  /* PatientPrescription retrieves the correct prescription from the database for current patient with one medication*/
  @Test
  void PatientPrescription_oneMedication() {
    String expectedMedicationList = "Amodis, Metronidazole BP, Oral, 400, 1, Twice a day, Morning, ";

    Prescription prescription = new Prescription();
    prescription.setPatientUserName("antor@gmail.com");
    PrescriptionDL prescriptionDL = new PrescriptionDL();
    prescription.getPrescriptionList(prescriptionDL);
    ArrayList<ArrayList<String>> medicationList = prescription.getMedicationList();
    StringBuilder actualMedicationList = new StringBuilder();
    for (ArrayList<String> medication : medicationList) {
      StringBuilder singleMedicationDetails = new StringBuilder();
      for (String medicationDetails : medication) {
        singleMedicationDetails.append(medicationDetails);
        singleMedicationDetails.append(", ");
      }
      actualMedicationList.append(singleMedicationDetails);
    }

    assertEquals(expectedMedicationList, actualMedicationList.toString(),
        "PatientPrescription did not process the correct prescription for patient with one medication");
  }

  /* PatientPrescription retrieves the correct prescription from the database for current patient with
   * a total of four medications prescribed for all three times of the day */
  @Test
  void PatientPrescription_manyMedications() {
    String expectedMedicationList = "Amodis, Metronidazole BP, Oral, 400, 1, Twice a day, Morning, " +
        "Flonase, Fluticasone, Oral, 200, 1, Once a day, Morning, " +
        "Fosamax, Alendronate, Oral, 400, 1, Twice a day, Evening, " +
        "Glucophage, Melformin, Oral, 100, 1, Once a day, Night, ";

    Prescription prescription = new Prescription();
    prescription.setPatientUserName("Ridam@gmail.com");
    PrescriptionDL prescriptionDL = new PrescriptionDL();
    prescription.getPrescriptionList(prescriptionDL);
    ArrayList<ArrayList<String>> medicationList = prescription.getMedicationList();
    StringBuilder actualMedicationList = new StringBuilder();
    for (ArrayList<String> medication : medicationList) {
      StringBuilder singleMedicationDetails = new StringBuilder();
      for (String medicationDetails : medication) {
        singleMedicationDetails.append(medicationDetails);
        singleMedicationDetails.append(", ");
      }
      actualMedicationList.append(singleMedicationDetails);
    }

    assertEquals(expectedMedicationList, actualMedicationList.toString(),
        "PatientPrescription did not process the correct prescription for patient with many medications");
  }

  /* formatPrescription() structures the DB results into suitable format for user with one medication */
  @Test
  void formatPrescription_oneMedication() {
    String expectedPrescriptionFormat =
        "PRESCRIPTIONS\n" +
            "*******************************************\n" +
            "Time of day: Morning\n" +
            "   MEDICATION\n" +
            "   Brand Name : Amodis\n" +
            "   Generic Name : Metronidazole BP\n" +
            "   Route : Oral\n" +
            "   Strength : 400 mg\n" +
            "   Amount : 1 Tablet\n" +
            "   Frequency : Twice a day\n" +
            "\n" +
            "*******************************************\n" +
            "Time of day: Evening\n" +
            "No Medications\n" +
            "*******************************************\n" +
            "Time of day: Night\n" +
            "No Medications\n";

    Prescription prescription = new Prescription();
    prescription.setPatientUserName("antor@gmail.com");
    PrescriptionDL prescriptionDL = new PrescriptionDL();
    prescription.getPrescriptionList(prescriptionDL);
    String actualPrescriptionFormat = PrescriptionPL.displayPrescription(prescription);
    assertEquals(expectedPrescriptionFormat, actualPrescriptionFormat,
        "formatPrescription() did not structure the prescription for patient with one medication");
  }

  /* formatPrescription() structures the DB results into suitable format for user with many medications */
  @Test
  void formatPrescription_manyMedications() {
    String expectedPrescriptionFormat =
        "PRESCRIPTIONS\n" +
            "*******************************************\n" +
            "Time of day: Morning\n" +
            "   MEDICATION\n" +
            "   Brand Name : Amodis\n" +
            "   Generic Name : Metronidazole BP\n" +
            "   Route : Oral\n" +
            "   Strength : 400 mg\n" +
            "   Amount : 1 Tablet\n" +
            "   Frequency : Twice a day\n" +
            "\n" +
            "   MEDICATION\n" +
            "   Brand Name : Flonase\n" +
            "   Generic Name : Fluticasone\n" +
            "   Route : Oral\n" +
            "   Strength : 200 mg\n" +
            "   Amount : 1 Tablet\n" +
            "   Frequency : Once a day\n" +
            "\n" +
            "*******************************************\n" +
            "Time of day: Evening\n" +
            "   MEDICATION\n" +
            "   Brand Name : Fosamax\n" +
            "   Generic Name : Alendronate\n" +
            "   Route : Oral\n" +
            "   Strength : 400 mg\n" +
            "   Amount : 1 Tablet\n" +
            "   Frequency : Twice a day\n" +
            "\n" +
            "*******************************************\n" +
            "Time of day: Night\n" +
            "   MEDICATION\n" +
            "   Brand Name : Glucophage\n" +
            "   Generic Name : Melformin\n" +
            "   Route : Oral\n" +
            "   Strength : 100 mg\n" +
            "   Amount : 1 Tablet\n" +
            "   Frequency : Once a day\n" +
            "\n";

    Prescription prescription = new Prescription();
    prescription.setPatientUserName("Ridam@gmail.com");
    PrescriptionDL prescriptionDL = new PrescriptionDL();
    prescription.getPrescriptionList(prescriptionDL);
    String actualPrescriptionFormat = PrescriptionPL.displayPrescription(prescription);
    assertEquals(expectedPrescriptionFormat, actualPrescriptionFormat,
        "formatPrescription() did not structure the prescription for patient with many medications");
  }


}