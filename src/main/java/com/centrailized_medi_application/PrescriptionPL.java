package com.centrailized_medi_application;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Kazi Hasan
 * @description: displays prescription related views
 */
public class PrescriptionPL {

  /**
   * This method is used for displaying the prescription to patient
   *
   * @return None
   * @Param prescription- interface for representing patient prescription
   */
  public static String displayPrescription(IPrescription prescription) {
    StringBuilder formattedPrescription = new StringBuilder();
    formattedPrescription.append("PRESCRIPTIONS\n");

    // format headings for medication of each time of day
    StringBuilder morningMedications = new StringBuilder();
    morningMedications.append("*******************************************\n" +
        "Time of day: Morning\n");
    StringBuilder eveningMedications = new StringBuilder();
    eveningMedications.append("*******************************************\n" +
        "Time of day: Evening\n");
    StringBuilder nightMedications = new StringBuilder();
    nightMedications.append("*******************************************\n" +
        "Time of day: Night\n");

    // counters to keep track of number of medications for each time of day
    int morningCounter = 0;
    int eveningCounter = 0;
    int nightCounter = 0;

    // iterate over medication list retrieved from DB and add to appropriate time of day stringBuilder
    for (ArrayList<String> medication : prescription.getMedicationList()) {
      StringBuilder singleMedication = new StringBuilder();
      singleMedication.append("   MEDICATION\n");
      singleMedication.append("   Brand Name : ").append(medication.get(0)).append("\n");
      singleMedication.append("   Generic Name : ").append(medication.get(1)).append("\n");
      singleMedication.append("   Route : ").append(medication.get(2)).append("\n");
      singleMedication.append("   Strength : ").append(medication.get(3)).append(" mg\n");
      singleMedication.append("   Amount : ").append(medication.get(4)).append(" Tablet\n");
      singleMedication.append("   Frequency : ").append(medication.get(5)).append("\n");
      singleMedication.append("\n");

      if (medication.get(6).equals("Morning")) {
        morningMedications.append(singleMedication);
        morningCounter += 1;
      } else if (medication.get(6).equals("Evening")) {
        eveningMedications.append(singleMedication);
        eveningCounter += 1;
      } else if (medication.get(6).equals("Night")) {
        nightMedications.append(singleMedication);
        nightCounter += 1;
      }
    }

    // check if any time of day has zero medications and format the prescription accordingly
    if (morningCounter == 0) {
      morningMedications.append("No Medications\n");
    }
    if (eveningCounter == 0) {
      eveningMedications.append("No Medications\n");
    }
    if (nightCounter == 0) {
      nightMedications.append("No Medications\n");
    }

    // append medications from each time of day into a final prescription
    formattedPrescription.append(morningMedications).append(eveningMedications).append(nightMedications);

    return formattedPrescription.toString();
  }

  /**
   * This method is used for viewing that doctor needs to add different medications for patient
   *
   * @return None
   * @Param prescription- interface for representing patient prescription
   */
  public static void addPrescription(IPrescription prescription) {
    ArrayList<ArrayList<String>> medications = new ArrayList<>();

    // ask doctor to input patient username
    System.out.println("Please enter the patient username for whom you want to add prescription :");
    Scanner inputPatientUserName = new Scanner(System.in);
    prescription.setPatientUserName(inputPatientUserName.nextLine());

    // ask doctor to input medication list
    System.out.println("Please enter medication details for the patient ");
    System.out.println("Type exit to stop adding medications.");
    String doctorInput;
    while (true) {
      Scanner inputMedicationDetails = new Scanner(System.in);
      ArrayList<String> individualMedication = new ArrayList<>();
      System.out.println("Brand Name : ");
      doctorInput = inputMedicationDetails.nextLine();
      if (!doctorInput.equals("exit")) {
        individualMedication.add(doctorInput);
      } else {
        break;
      }
      System.out.println("Generic Name : ");
      doctorInput = inputMedicationDetails.nextLine();
      if (!doctorInput.equals("exit")) {
        individualMedication.add(doctorInput);
      } else {
        break;
      }
      System.out.println("Route : ");
      doctorInput = inputMedicationDetails.nextLine();
      if (!doctorInput.equals("exit")) {
        individualMedication.add(doctorInput);
      } else {
        break;
      }
      System.out.println("Strength : ");
      doctorInput = inputMedicationDetails.nextLine();
      if (!doctorInput.equals("exit")) {
        individualMedication.add(doctorInput);
      } else {
        break;
      }
      System.out.println("Amount : ");
      doctorInput = inputMedicationDetails.nextLine();
      if (!doctorInput.equals("exit")) {
        individualMedication.add(doctorInput);
      } else {
        break;
      }
      System.out.println("Frequency : ");
      doctorInput = inputMedicationDetails.nextLine();
      if (!doctorInput.equals("exit")) {
        individualMedication.add(doctorInput);
      } else {
        break;
      }
      System.out.println("Time of Day : ");
      doctorInput = inputMedicationDetails.nextLine();
      if (!doctorInput.equals("exit")) {
        individualMedication.add(doctorInput);
      } else {
        break;
      }
      medications.add(individualMedication);
    }

    // add general and specific medication list ot prescription
    prescription.setMedicationsByDoctor(medications);
  }

}
