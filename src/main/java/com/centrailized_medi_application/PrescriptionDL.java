package com.centrailized_medi_application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * @author Kazi Hasan
 * @description: shows input output interface to patients and doctors for prescription related functions
 */
public class PrescriptionDL implements IPrescriptionPersistence {

  /**
   * This method is used for saving patient prescription by doctors
   *
   * @return None
   * @Param prescription- interface for patient prescription
   */
  @Override
  public void savePrescription(IPrescription prescription) {
    // get general and specific medication details
    ArrayList<ArrayList<String>> medications = prescription.getMedicationsByDoctor();

    try {
      DbConnection one = new DB_Connection();
      Connection connection = one.createConnection();
      Statement statement = connection.createStatement();
      ResultSet resultSet1 = null;
      ResultSet resultSet2 = null;
      ResultSet resultSet3 = null;


      for (ArrayList<String> medication : medications) {
        statement.executeUpdate("INSERT INTO MedicationGeneral (BrandName,GenericName,Route)\n" +
            "VALUES ('" + medication.get(0) + "','" + medication.get(1) + "','" + medication.get(2) + "');");

        resultSet1 = statement.executeQuery("SELECT * FROM MedicationGeneral ORDER BY " +
            "MedicationGeneralID DESC LIMIT 1;");
        resultSet1.next();
        int generalMedicationID = resultSet1.getInt("MedicationGeneralID");

        statement.executeUpdate("INSERT INTO MedicationSpecific (MedicationGeneralID, Strength, Amount, " +
            "Frequency,TimeOfDay)\nVALUES (" + generalMedicationID + "," + Integer.parseInt(medication.get(3)) +
            "," + Integer.parseInt(medication.get(4)) + ",'" + medication.get(5) + "','" + medication.get(6) + "');");

        resultSet2 = statement.executeQuery("SELECT * FROM MedicationSpecific ORDER BY " +
            "MedicationSpecificID DESC LIMIT 1;");
        resultSet2.next();
        int specificMedicationID = resultSet2.getInt("MedicationSpecificID");

        resultSet3 = statement.executeQuery("SELECT id FROM patient_info\n" +
            "WHERE emailId='" + prescription.getPatientUserName() + "';");
        resultSet3.next();
        int patientID = resultSet3.getInt("id");

        statement.executeUpdate("INSERT INTO PatientMedication\n" +
            "VALUES (" + specificMedicationID + "," + patientID + ");");
      }

      if (resultSet1 != null) {
        resultSet1.close();
      }
      if (resultSet2 != null) {
        resultSet2.close();
      }
      if (resultSet3 != null) {
        resultSet3.close();
      }
      statement.close();
      connection.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * This method is used for loading patient prescription
   *
   * @return None
   * @Param prescription- interface for representing patient prescription
   */
  @Override
  public void loadPrescription(IPrescription prescription) {
    ArrayList<ArrayList<String>> medicationList = new ArrayList<>();
    String patientUserName = prescription.getPatientUserName();

    try {
      DbConnection one = new DB_Connection();
      Connection connection = one.createConnection();
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(
          "SELECT BrandName, GenericName, Route, Strength, Amount, Frequency, TimeOfDay FROM patient_info, " +
              "MedicationGeneral, MedicationSpecific, PatientMedication\n" +
              "WHERE patient_info.emailId = '" + patientUserName + "' AND\n" +
              "\tMedicationSpecific.MedicationGeneralID = MedicationGeneral.MedicationGeneralID AND\n" +
              "    patient_info.id = PatientMedication.id AND\n" +
              "    MedicationSpecific.MedicationSpecificID = PatientMedication.MedicationSpecificID;");

      // add the retrieved medication list to class arraylist of medications
      while (resultSet.next()) {
        ArrayList<String> resultRow = new ArrayList<String>();
        resultRow.add(resultSet.getString("BrandName"));
        resultRow.add(resultSet.getString("GenericName"));
        resultRow.add(resultSet.getString("Route"));
        resultRow.add(Integer.toString(resultSet.getInt("Strength")));
        resultRow.add(Integer.toString(resultSet.getInt("Amount")));
        resultRow.add(resultSet.getString("Frequency"));
        resultRow.add(resultSet.getString("TimeOfDay"));
        medicationList.add(resultRow);
      }

      resultSet.close();
      statement.close();
      connection.close();

    } catch (Exception e) {
      e.printStackTrace();
    }

    prescription.setMedicationList(medicationList);
  }


}
