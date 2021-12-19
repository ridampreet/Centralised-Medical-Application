package com.centrailized_medi_application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Monisha J and Ridampreet Singh
 * @description : This program performs the actual authentication of Doctor Login.
 * @params : MainDashboard and DoctorDashboard are passed, which are used to handle
 * changes after a login failure or success respectively.
 */
public class Doctor extends Login {
  private String userName;
  private String password;
  private boolean isValidUsername = false;
  private boolean isValidPassword = false;
  private boolean isUserValidated = false;
  private boolean[] creds = new boolean[2];
  private MainDashboard init;
  private DoctorDashboard dd;
  private ILoginAuthorisation loginAuthorisation; // Password interface
  private static int localRetry = 0;          // Security Question counter
  private static DbConnection connect;

  ResultSet login_name;
  ResultSet login_pass;
  ResultSet res_check_for_doc;
  PreparedStatement p1;
  PreparedStatement p2;
  PreparedStatement check_for_doc;

  /**
   * Constructor initializes the Dashboard members declared
   *
   * @param init       - It holds the MainDashboard instance
   * @param doctorPage - It holds the DoctorDashboard instance
   */
  public Doctor(MainDashboard init, DoctorDashboard doctorPage, DbConnection connection,
                ILoginAuthorisation localAuthorisation) {
    try {
      this.init = init;
      this.dd = doctorPage;
      this.loginAuthorisation = localAuthorisation;
      if (Doctor.connect != null) {
        connect.close();
      }
      connect = connection;
    } catch (SQLException e) {
      System.out.print(e.getMessage());
    }
  }

  /**
   * This method returns the doctor username entered.
   *
   * @return String username
   */
  public String getUsername() {
    return this.userName;
  }

  /**
   * This method returns the doctor password entered.
   *
   * @return String password
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * This method returns the boolean stating the validity of the username entered
   *
   * @return boolean isValidUsername
   */
  public boolean getUsernameStatus() {
    return this.isValidUsername;
  }

  /**
   * This method returns the boolean stating the validity of the password entered
   *
   * @return boolean isValidPassword
   */
  public boolean getPasswordStatus() {
    return this.isValidPassword;
  }

  /**
   * This method returns the boolean if the user credentials were validated.
   *
   * @return boolean isUserValidated
   */
  public boolean getValidationStatus() {
    return this.isUserValidated;
  }

  /**
   * This method receives and initialises the login credentials
   *
   * @param name     - It holds the username
   * @param password - It holds the user password
   */
  @Override
  public void fetch(String name, String password) {
    this.userName = name;
    this.password = password;
  }

  /**
   * This method validates the user credentials shared.
   */
  @Override
  public void validate() {
    try {
      List<Object> resultState = connect.getDetails();
      this.creds = (boolean[]) resultState.get(0);
      this.isValidUsername = creds[0];
      this.isValidPassword = creds[1];
      login_name = (ResultSet) resultState.get(1);
      login_pass = (ResultSet) resultState.get(2);
      res_check_for_doc = (ResultSet) resultState.get(3);
      p1 = (PreparedStatement) resultState.get(4);
      p2 = (PreparedStatement) resultState.get(5);
      check_for_doc = (PreparedStatement) resultState.get(6);

      if (login_name != null) {
        login_name.close();
      }
      if (login_pass != null) {
        login_pass.close();
      }
      if (res_check_for_doc != null) {
        res_check_for_doc.close();
      }
      if (p1 != null) {
        p1.close();
      }
      if (p2 != null) {
        p2.close();
      }
      if (check_for_doc != null) {
        check_for_doc.close();
      }
      connect.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * This checks the authentication of the user.
   * If it is valid, navigates to user dashboard
   * Else if password entered is wrong, it gives maximum 3 trials and asks the user to reset the password
   * Else(username doesn't exits) asks the user to register first
   */
  @Override
  public void authenticate() {

    isUserValidated = true;
    try {
      if (this.isValidUsername && this.isValidPassword) {
        System.out.println("Welcome " + this.userName);
        this.dd.display();
      } else if (this.isValidUsername && !this.isValidPassword) {
        System.out.println("Check your credentials!");
        localRetry++;
        this.loginAuthorisation.set_Retry(localRetry);
        if (localRetry != 3) {
          this.init.displayDoctorLogin();
        } else if (localRetry == 3) {
          this.loginAuthorisation.getSecurityQuestion(userName);
          this.init.display();
        }
      } else {
        System.out.println("Please register to the system!");
        System.out.println("Navigating to main menu...");
        this.init.display();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      ;
    }
  }
}
