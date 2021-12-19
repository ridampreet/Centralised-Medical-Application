package com.centrailized_medi_application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Ridampreet Singh & Kazi Hasan
 * Description: This class acts as a data layer for the whole application and allows other classes to send their
 * parameters so that respective queries can be executed and the valid result sets can be returned.
 * getUserDetails()-Method gets the details from the respective tables of the patient or the doctor and returns a result set
 * insertConsultations()-Inserts the Consultations of doctors.
 * getCredStatus()-Returns the credentials array which contains boolean values of username and password being correct.
 * fetchDonors()-Returns the result set containing donors.
 * check_if_patient()-checks if the user is a patient, if yes then returns all of the information about the patient.
 * check_if_doctor()-checks if the user is a doctor, if yes then returns all of the information about the doctor.
 * updatePatient()-Updates the login information stored about the patient.
 * updateDoctor()-Updates the login information stored about the patient.
 * displayPatientInfo()-Returns a result set containing information about the patient.
 * displayFamilyinfo()-Returns the result set containing the family information.
 * insertNewDoctor()-Inserts a new Doctor into the database.
 * insertNewPatient()-Inserts a new patient into the database.
 * feedRatings()-Inserts ratings about the patient into the database.
 */

public class DB_Layer {

  private Connection connect;
  private static DB_Layer dlb = null;

  private DB_Layer() {
    try {
      DB_Connection db = new DB_Connection();
      this.connect = db.createConnection();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public static DB_Layer singleConnection() throws SQLException, IOException, ClassNotFoundException {
    if (dlb == null) {
      DB_Layer.dlb = new DB_Layer();
      return dlb;
    }
    return DB_Layer.dlb;
  }

  public List<Object> getUserDetails(String username, String user_type) {
    try {
      String sqlStmt;
      if (user_type.equals("Doctor")) {
        sqlStmt = "SELECT * FROM doctor_info where emailId =?;";
      } else {
        sqlStmt = "SELECT * FROM patient_info where emailId =?;";
      }
      ResultSet currentDoctorDetails;
      PreparedStatement prepStmt = connect.prepareStatement(sqlStmt);
      prepStmt.toString();
      prepStmt.setString(1, username);
      currentDoctorDetails = prepStmt.executeQuery();
      return Arrays.asList(currentDoctorDetails, prepStmt);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }

  }

  public void close() {
    try {
      connect.close();
      DB_Layer.dlb = null;
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void insertConsultations(String docter_user_name, String p_name, DateTimeFormatter consultation_date, LocalDateTime current_time) {
    PreparedStatement prep_statement = null;
    try {
      prep_statement = connect.prepareStatement("INSERT INTO `consultations`(doctor_username,patient_username,consultation_date_and_time) VALUES (?, ?, ?)");
      prep_statement.setString(1, docter_user_name);
      prep_statement.setString(2, p_name);
      prep_statement.setString(3, (consultation_date.format(current_time)));
      prep_statement.executeUpdate();
      prep_statement.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public List<Object> getCredStatus(String u_name, String u_pass) {
    try {
      String name;
      boolean res_id = false;
      boolean res_pass = false;
      boolean[] cred_validity = new boolean[2];
      String pass;
      PreparedStatement p1 = connect.prepareStatement("select * from CSCI5308_5_PRODUCTION.login_details where user_name=?");
      PreparedStatement p2 = null;
      PreparedStatement check_for_doc = null;
      p1.setString(1, u_name);
      ResultSet login_name = p1.executeQuery();
      ResultSet login_pass = null;
      ResultSet res_check_for_doc = null;
      while (login_name.next()) {
        name = login_name.getString("user_name");
        if (name.equals((u_name))) {
          res_id = true;
        }
        check_for_doc = connect.prepareStatement("select * from CSCI5308_5_PRODUCTION.doctor_info where emailid=?");
        check_for_doc.setString(1, u_name);
        res_check_for_doc = check_for_doc.executeQuery();
        if (res_check_for_doc.next() == true) {
          res_id = true;
          if (res_check_for_doc.getString("password").equals(u_pass)) {
            res_pass = true;
          } else {
            res_pass = false;
          }
          cred_validity[0] = res_id;
          cred_validity[1] = res_pass;
          return Arrays.asList(cred_validity, login_name, login_pass, res_check_for_doc, p1, p2, check_for_doc);
        }
        p2 = connect.prepareStatement("select * from CSCI5308_5_PRODUCTION.login_details where user_name=? and pass=?");
        p2.setString(1, u_name);
        p2.setString(2, u_pass);
        login_pass = p2.executeQuery();
        while (login_pass.next()) {

          pass = login_pass.getString("pass");
          if (pass.equals(u_pass)) {
            res_pass = true;
          }
        }
        cred_validity[0] = res_id;
        cred_validity[1] = res_pass;
      }
      return Arrays.asList(cred_validity, login_name, login_pass, res_check_for_doc, p1, p2, check_for_doc);
    } catch (Exception e) {
      return null;
    }
  }

  public List<Object> fetchDonors() {
    try {
      PreparedStatement get_donors = connect.prepareStatement("Select * from CSCI5308_5_PRODUCTION.patient_info where volunteer=? ");
      get_donors.setString(1, "yes");
      ResultSet exec_get_donors = get_donors.executeQuery();

      return Arrays.asList(exec_get_donors, get_donors);
    } catch (Exception e) {
      return null;
    }
  }

  public List<Object> check_if_patient(String user_name) {
    try {
      PreparedStatement check_if_patient = connect.prepareStatement("select * from CSCI5308_5_PRODUCTION.patient_info where emailId=?");
      check_if_patient.setString(1, user_name);
      ResultSet s1 = check_if_patient.executeQuery();

      return Arrays.asList(s1, check_if_patient);
    } catch (Exception e) {
      return null;
    }
  }

  public List<Object> check_if_doctor(String user_name) {
    try {
      PreparedStatement check_if_Doctor = connect.prepareStatement("select * from CSCI5308_5_PRODUCTION.doctor_info where emailId=?");
      check_if_Doctor.setString(1, user_name);
      ResultSet s2 = check_if_Doctor.executeQuery();

      return Arrays.asList(s2, check_if_Doctor);
    } catch (Exception e) {
      return null;
    }
  }

  public void updatePatient(String newPassword, String user_name) {
    try {
      PreparedStatement updatePass = connect.prepareStatement("Update CSCI5308_5_PRODUCTION.patient_info set password=? where emailid=?");
      updatePass.setString(1, newPassword);
      updatePass.setString(2, user_name);
      updatePass.execute();
      updatePass = connect.prepareStatement("Update CSCI5308_5_PRODUCTION.login_details set pass=? where user_name=?");
      updatePass.setString(1, newPassword);
      updatePass.setString(2, user_name);
      updatePass.execute();
      updatePass.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

  }

  public void updateDoctor(String newPassword, String user_name) {
    try {
      PreparedStatement updatePass = connect.prepareStatement("Update CSCI5308_5_PRODUCTION.doctor_info set password=? where emailid=?");
      updatePass.setString(1, newPassword);
      updatePass.setString(2, user_name);
      updatePass.execute();
      updatePass = connect.prepareStatement("Update CSCI5308_5_PRODUCTION.login_details set pass=? where user_name=?");
      updatePass.setString(1, newPassword);
      updatePass.setString(2, user_name);
      updatePass.execute();

      updatePass.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public List<Object> displayPatientInfo(String patientEmail) {
    try {
      String sqlStmt = "SELECT * FROM CSCI5308_5_PRODUCTION.patient_info where emailId =?";
      PreparedStatement prepStmt = connect.prepareStatement(sqlStmt);
      prepStmt.setString(1, patientEmail);
      ResultSet currentPatientDetails = prepStmt.executeQuery();
      return Arrays.asList(currentPatientDetails, prepStmt);
    } catch (Exception e) {
      return null;
    }

  }

  public List<Object> displayFamilyinfo(String familyCode, String patientEmail) {
    try {
      String sqlStmt = "SELECT * FROM CSCI5308_5_PRODUCTION.patient_info where familyMemberCode =\"" + familyCode + "\" and not emailId=\"" + patientEmail + "\"";
      PreparedStatement prepStmt = connect.prepareStatement(sqlStmt);
      ResultSet familyDetails = prepStmt.executeQuery();
      return Arrays.asList(familyDetails, prepStmt);
    } catch (Exception e) {
      return null;
    }
  }

  public void insertNewDoctor(BasicDetails basicDetails, DoctorDetails doctorDetails, SecurityQuestions securityQuestions) {
    try {
      PreparedStatement st = connect.prepareStatement("Insert into login_details(user_name,pass) values(?,?)");
      st.setString(1, basicDetails.getEmailId());
      st.setString(2, basicDetails.getPassword());
      st.execute();
      st = connect.prepareStatement("Select * from CSCI5308_5_PRODUCTION.login_details where user_name=\"" + basicDetails.getEmailId() + "\"");
      ResultSet rs2 = st.executeQuery();
      rs2.next();
      int curr_id = rs2.getInt("idlogin_details");
      st.close();
      PreparedStatement insert_statement = connect.prepareStatement("insert into " +
          "doctor_info(id,firstname,lastname,gender,dateOfBirth,address,latitude,longitude,contactNo,speciality,registrationNumber," +
          "emailId,password,security_answer_1,security_answer_2,security_answer_3) " +
          "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
      insert_statement.setInt(1, curr_id);
      insert_statement.setString(2, basicDetails.getFirstName());
      insert_statement.setString(3, basicDetails.getLastName());
      insert_statement.setString(4, basicDetails.getGender());
      insert_statement.setString(5, basicDetails.getDob());
      insert_statement.setString(6, basicDetails.getAddress());
      insert_statement.setInt(7, basicDetails.getLatitude());
      insert_statement.setInt(8, basicDetails.getLongitude());
      insert_statement.setString(9, basicDetails.getContactNo());
      insert_statement.setString(10, doctorDetails.getSpeciality());
      insert_statement.setInt(11, doctorDetails.getRegistrationNumber());
      insert_statement.setString(12, basicDetails.getEmailId());
      insert_statement.setString(13, basicDetails.getPassword());
      insert_statement.setString(14, securityQuestions.getAnswer1());
      insert_statement.setString(15, securityQuestions.getAnswer2());
      insert_statement.setString(16, securityQuestions.getAnswer3());
      insert_statement.execute();
      insert_statement.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void insertNewPatient(BasicDetails basicDetails, PatientDetails patientDetails, SecurityQuestions securityQuestions) {
    try {
      PreparedStatement st = connect.prepareStatement("Insert into login_details(user_name,pass) values(?,?)");
      st.setString(1, basicDetails.getEmailId());
      st.setString(2, basicDetails.getPassword());
      st.execute();
      st = connect.prepareStatement("Select * from login_details where user_name=\"" + basicDetails.getEmailId() + "\"");
      ResultSet rs2 = st.executeQuery();
      rs2.next();
      int curr_id = rs2.getInt("idlogin_details");
      st.close();
      PreparedStatement test = connect.prepareStatement("INSERT INTO patient_info(id,firstname,lastname," +
          "dateOfbirth,gender,password,emailId,address,contactNo,bloodGroup," +
          "allergy,chronicDisease,insuranceNo,donorCardNo,familyMemberCode,volunteer,security_answer_1,security_answer_2,security_answer_3,latitude,longitude) " +
          "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
      test.setInt(1, curr_id);
      test.setString(2, basicDetails.getFirstName());
      test.setString(3, basicDetails.getLastName());
      test.setString(4, basicDetails.getDob());
      test.setString(5, basicDetails.getGender());
      test.setString(6, basicDetails.getPassword());
      test.setString(7, basicDetails.getEmailId());
      test.setString(8, basicDetails.getAddress());
      test.setString(9, basicDetails.getContactNo());
      test.setString(10, patientDetails.getBloodGroup());
      test.setString(11, patientDetails.getAllergy());
      test.setString(12, patientDetails.getChonicDisease());
      test.setString(13, patientDetails.getInsuranceNo());
      test.setString(14, patientDetails.getDonorCardNo());
      test.setString(15, patientDetails.getFamilyMemberCode());
      test.setString(16, patientDetails.getVolunteer());
      test.setString(17, securityQuestions.getAnswer1());
      test.setString(18, securityQuestions.getAnswer2());
      test.setString(19, securityQuestions.getAnswer3());
      test.setInt(20, basicDetails.getLatitude());
      test.setInt(21, basicDetails.getLongitude());
      test.execute();
      test.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public List<Object> feedRatings(String userName) {
    try {
      PreparedStatement prep_statement = connect.prepareStatement("SELECT * FROM `consultations` WHERE patient_username = ?");
      prep_statement.setString(1, userName);
      ResultSet doc_list = prep_statement.executeQuery();
      ResultSet get_current_value = null;
      Scanner sc = new Scanner(System.in);
      while (doc_list.next()) {
        System.out.println(doc_list.getString("doctor_username") + " " + doc_list.getString("consultation_date_and_time"));
        System.out.println("Enter rating:");
        int rating = sc.nextInt();
        int current_value = 0;
        //Fetch existing values
        String email_doc = doc_list.getString("doctor_username");
        PreparedStatement current_val = connect.prepareStatement("SELECT * FROM `doctor_info` WHERE emailId=?");
        current_val.setString(1, email_doc);
        //current_val.setInt(2, rating);
        get_current_value = current_val.executeQuery();
        int updt_rating = 0;
        if (get_current_value.next()) {
          current_value = get_current_value.getInt("rating");
          updt_rating = (current_value + rating) / 2;
        } else {
          updt_rating = rating;
        }
        //Update with the patient values
        PreparedStatement pstatement = connect.prepareStatement("Update doctor_info set rating=? where emailId=?");
        pstatement.setInt(1, updt_rating);
        email_doc = doc_list.getString("doctor_username");
        pstatement.setString(2, email_doc);
        boolean doc_rate = pstatement.execute();
      }
      return Arrays.asList(doc_list, get_current_value, prep_statement);
    } catch (Exception e) {
      return null;
    }
  }
}
