package com.centrailized_medi_application;

/**
 * @author Ridampreet Singh
 * Interface implemented by the LoginAuthorisation class.
 * getSecurityQuestion()-gets the security question from the database for that specific user.
 * resetPassword()-method invoked from the getSecurityQuestion() when the user has successfuly
 * answered security question, asks user for the new password and then sends to the database.
 */
public interface ILoginAuthorisation {
  String user_id = null;
  String getSecurityQuestion(String Username);//will be used to get the security questions for the user trying to login
  String resetPassword(String user_name, boolean securityQuesCleared, Integer retries);//This function will provide the user the ability to reset password
  void set_Retry(Integer localRetry);
}
