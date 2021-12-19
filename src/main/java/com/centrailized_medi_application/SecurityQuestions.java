package com.centrailized_medi_application;

import java.util.Scanner;

/**
 * @author Monisha J, Neelay Jayantbharti Goswami
 * @description : This program gets the answers for security question from the person trying to register or
 * trying to reset password. The class has getter and setter of the required variables holding security answers.
 */
public class SecurityQuestions implements Details {
  private Validation validate = new Validation();
  private String answer1;
  private String answer2;
  private String answer3;

  protected Scanner sc = new Scanner(System.in);

  public String getAnswer1() {
    return answer1;
  }

  public void setAnswer1(String ans1) {
    this.answer1 = ans1;
  }

  public String getAnswer2() {
    return answer2;
  }

  public void setAnswer2(String ans2) {
    this.answer2 = ans2;
  }

  public String getAnswer3() {
    return answer3;
  }

  public void setAnswer3(String ans3) {
    this.answer3 = ans3;
  }

  /**
   * This method reads validates and sets the answers for the security questions.
   *
   * @params : None
   */
  @Override
  public void getDetails() {
    String temp;
    System.out.println("Your first school:");
    temp = sc.nextLine();
    while (!validate.validateAlphanumeric(temp)) {
      System.out.println("Please enter your first school: ");
      temp = sc.nextLine();
    }
    setAnswer1(temp);

    System.out.println("Your hobby:");
    temp = sc.nextLine();
    while (!validate.validateAlphanumeric(temp)) {
      System.out.println("Please enter Your hobby: ");
      temp = sc.nextLine();
    }
    setAnswer2(temp);
    System.out.println("Your first bike:");
    temp = sc.nextLine();
    while (!validate.validateAlphanumeric(temp)) {
      System.out.println("Please enter your first bike:");
      temp = sc.nextLine();
    }
    setAnswer3(temp);
  }
}
