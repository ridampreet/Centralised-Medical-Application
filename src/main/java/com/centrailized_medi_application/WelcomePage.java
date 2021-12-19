package com.centrailized_medi_application;

/**
 * @author Aditya Jain
 * @description: WelcomePage is a Concrete class which extends MainDashboard
 * It overrides methods such as displayPatientLogin(), displayPatientRegistration(),
 * displayDoctorRegistration(), displayDoctorLogin()
 */
public class WelcomePage extends MainDashboard {
  /**
   * This function allows the user to login as a Patient
   */
  @Override
  public void displayPatientLogin() {
    try {
      System.out.println("Enter your username:");
      String patient_name = (sc.next());
      System.out.println("Enter your password:");
      String patient_pass = (sc.next());
      Action action = new Action(); // Initialize Action
      Patient p1 = new Patient(this, new PatientPage(patient_name), new DB_Connection(patient_name, patient_pass), new LoginAuthorisation());  // Initialize patient
      PatientLogin p_login = new PatientLogin(p1, this);  // Passing the object to the patient login
      p_login.setPatientName(patient_name);
      p_login.setPatientPass(patient_pass);
      action.setCommand(p_login);
      action.run();
    } catch (Exception e) {
      System.out.println("Doctor Login error" + e.getMessage());
    }
  }

  /**
   * This function allows the user to register as a Patient
   */
  @Override
  public void displayPatientRegistration() {
    try {
      BasicDetails basicDetails = new BasicDetails();
      PatientDetails patientDetails = new PatientDetails();
      SecurityQuestions securityQuestions = new SecurityQuestions();
      Action action = new Action(); // Initialize Action
      NewPatient p = new NewPatient(basicDetails, patientDetails, securityQuestions, this);
      PatientRegistration patientReg = new PatientRegistration(p, this);
      patientReg.start();
      action.setCommand(patientReg);
      action.run();
    } catch (Exception e) {
      System.out.println("Input Exception Encountered moving to main menu " + e);
    }
  }

  /**
   * This methods creates instances to get details required for registration.
   * And then executes the registration operation.
   */

  @Override
  public void displayDoctorRegistration() {
    BasicDetails basicDetails = new BasicDetails();
    DoctorDetails doctorDetails = new DoctorDetails();
    SecurityQuestions securityQuestions = new SecurityQuestions();
    Action action = new Action(); // Initialize Action
    NewDoctor doc = new NewDoctor(basicDetails, doctorDetails, securityQuestions, this);
    DoctorRegistration docReg = new DoctorRegistration(doc, this);
    docReg.start();
    action.setCommand(docReg);
    action.run();
  }

  /**
   * This method reads the username and password for login.
   * Creates instances for concrete classes and executes the login operation.
   */
  @Override
  public void displayDoctorLogin() {
    try {
      System.out.println("Enter your username:");
      String doctor_name = (sc.next());
      System.out.println("Enter your password:");
      String doctor_password = (sc.next());
      Action action = new Action(); // Initialize Action
      Doctor dr = new Doctor(this, new DoctorPage(doctor_name), new DB_Connection(doctor_name, doctor_password), new LoginAuthorisation());  // Initialize doctor
      DoctorLogin doctorLogin = new DoctorLogin(dr, this);  // Passing the object to the doctor login
      doctorLogin.setDoctorName(doctor_name);
      doctorLogin.setDoctorPassword(doctor_password);
      action.setCommand(doctorLogin);
      action.run();
    } catch (Exception e) {
      System.out.println("Doctor Login error" + e.getMessage());
    }
  }

}