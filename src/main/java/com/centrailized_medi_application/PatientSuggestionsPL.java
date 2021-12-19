package com.centrailized_medi_application;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Kazi Hasan
 * @description: shows input output by patient for getting suggested doctors list
 */
public class PatientSuggestionsPL {

  /**
   * This method is used for viewing that patient needs to enter specialization
   *
   * @return None
   * @Param patientSuggestions- interface representing patient suggestion class
   */
  public static void setSpecializationByPatient(IPatientSuggestions patientSuggestions) {
    System.out.println("Please Enter the Doctor Specialization to search for: ");
    Scanner inputSpecialization = new Scanner(System.in);
    patientSuggestions.setSpecialization(inputSpecialization.nextLine());
  }

  /**
   * This method is used for viewing the suggested list of doctors
   *
   * @return None
   * @Param patientSuggestions- interface representing patient suggestion class
   */
  public static String viewSuggestedDoctors(IPatientSuggestions patientSuggestions) {
    System.out.println("Doctors, Specialized in " + patientSuggestions.getSpecialization() + " within 10km radius are: ");

    StringBuilder suggestedDoctors = new StringBuilder();
    suggestedDoctors.append("SUGGESTED DOCTORS\n");

    for (ArrayList<String> doctor : patientSuggestions.getDoctorsList()) {
      String singleDoctor = "*******************************************" + "\n" +
          "First Name: " + doctor.get(0) + "\n" +
          "Last Name: " + doctor.get(1) + "\n" +
          "Specialization: " + doctor.get(2) + "\n" +
          "Clinic Address: " + doctor.get(3) + "\n" +
          "Distance: " + doctor.get(4) + " km" + "\n" +
          "Average Rating: " + doctor.get(5) + "\n" +
          "Contact Number: " + doctor.get(6) + "\n" +
          "\n";
      suggestedDoctors.append(singleDoctor);
    }
    return suggestedDoctors.toString();
  }

}
