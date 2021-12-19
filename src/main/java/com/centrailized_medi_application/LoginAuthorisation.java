package com.centrailized_medi_application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Scanner;

/**
 * @author Ridampreet Singh & Aditya Jain
 * Implements interface ILoginAuthorisation.
 * getSecurityQuestion()-gets the security question from the database for that specific user.
 * resetPassword()-method invoked from the getSecurityQuestion() when the user has successfuly
 * answered security question, asks user for the new password and then sends to the database.
 */
public class LoginAuthorisation implements ILoginAuthorisation {
  int retry = 0;
  DB_Layer layer = null;

  /**
   * Method gets the security question from database and then prompts the user to answer it,
   * if answer correctly, the user gets teh funcionality to reset the password.
   * @param user_name
   * @return
   */

  @Override
  public String getSecurityQuestion(String user_name) {
    String result = null;
    try {
      layer = DB_Layer.singleConnection();
      List<Object> resultState = layer.check_if_patient(user_name);
      ResultSet s1 = (ResultSet) resultState.get(0);
      PreparedStatement prtstmt1 = (PreparedStatement) resultState.get(1);
      List<Object> resultState1 = layer.check_if_doctor(user_name);
      ResultSet s2 = (ResultSet) resultState1.get(0);
      PreparedStatement prtstmt2 = (PreparedStatement) resultState1.get(1);
      if (s1.next()) {
        System.out.println("Please enter Your first school:");
        Scanner sc = new Scanner(System.in);
        String inputAnswer = sc.nextLine();
        String answerFromDB = s1.getString("security_answer_1");
        if (answerFromDB.equals(inputAnswer)) {
          resetPassword(user_name, true, retry);
        }
        s1.close();
        prtstmt1.close();
        layer.close();
        result = answerFromDB;
      } else if (s2.next()) {
        System.out.println("Please enter Your first school:");
        Scanner sc2 = new Scanner(System.in);
        String inputAnswer = sc2.nextLine();
        String answerFromDB = s2.getString("security_answer_1");
        if (answerFromDB.equals(inputAnswer)) {
          resetPassword(user_name, true, retry);
        }
        s2.close();
        prtstmt2.close();
        layer.close();
        result = answerFromDB;
      } else {
        result = "User Does not Exist";
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  /**
   * The method provides the functionality to the user to reset the password.
   * @param user_name
   * @param securityQuesCleared
   * @param retries
   * @return
   */
  @Override
  public String resetPassword(String user_name, boolean securityQuesCleared, Integer retries) {
    String result = null;
    try {
      if (securityQuesCleared == false) {
        return "Please answer the security question first !";
      }
      layer = DB_Layer.singleConnection();
      List<Object> resultState = layer.check_if_patient(user_name);
      ResultSet s2 = (ResultSet) resultState.get(0);
      PreparedStatement prtstmt2 = (PreparedStatement) resultState.get(1);
      List<Object> resultState1 = layer.check_if_doctor(user_name);
      ResultSet s3 = (ResultSet) resultState1.get(0);
      PreparedStatement prtstmt3 = (PreparedStatement) resultState1.get(1);
      if (s2.next()) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the new password");
        String newPassword = sc.next();
        layer.updatePatient(newPassword, user_name);
        s2.close();
        prtstmt2.close();
        layer.close();
        result = "Password reset was successful !";
      } else if (s3.next()) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the new password");
        String newPassword = sc.next();
        layer.updateDoctor(newPassword, user_name);
        s3.close();
        prtstmt3.close();
        layer.close();
      } else {
        result = "User is not registered !";
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return result;
  }

  /**
   * Method keeps a strack of the number of retries.
   * @param localRetry
   */
  @Override
  public void set_Retry(Integer localRetry) {
    this.retry = localRetry;
    System.out.println("Incorrect tries: " + retry);
  }
}
