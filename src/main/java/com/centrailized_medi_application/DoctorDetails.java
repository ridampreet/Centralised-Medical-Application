package com.centrailized_medi_application;

import java.util.Scanner;

/**
 * @author Monisha J
 * @description : This program receives input of doctor specific details.
 * The fields used for a doctor alone. It holds getter and setter of the fields.
 * It implements Details, to override the getDetails method, that receives command line input
 */
public class DoctorDetails implements Details {

  private String speciality;
  private int registrationNumber;
  protected Scanner sc = new Scanner(System.in);

  public String getSpeciality() {
    return speciality;
  }

  public void setSpeciality(String spc) {
    this.speciality = spc;
  }

  public int getRegistrationNumber() {
    return registrationNumber;
  }

  public void setRegistrationNumber(int num) {
    this.registrationNumber = num;
  }

  /**
   * This method gets the doctor specific details.
   * Validates the input and allows the user to re-enter if it is invalid.
   * And assigns valid input it to the appropriate members.
   */
  @Override
  public void getDetails() {
    try {

      System.out.println("Enter your Speciality:");
      String temp = sc.nextLine();
      while (temp.isEmpty()) {
        System.out.println("Please enter your Speciality");
        temp = sc.nextLine();
      }
      setSpeciality(temp);

      System.out.println("Enter your Registration Number");
      int no = sc.nextInt();
      while (String.valueOf(no).length() == 0) {
        System.out.println("Please enter your Registration number");
        no = sc.nextInt();
      }
      setRegistrationNumber(no);

    } catch (Exception e) {
      System.out.println("doctorDetails Error " + e.getMessage());
    }
  }
}
