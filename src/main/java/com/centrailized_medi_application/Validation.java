package com.centrailized_medi_application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Neelay Jayantbharti Goswami
 * @description : This class is used to validate the input of the user while
 * they are doing the process of registration. This class includes methods like
 * validateEmail(String email), validateName(String name), validateDob(String dob),
 * validateContactNo(String contactno), validateGender(String gender),
 * validateBloodGroup(String bloodgroup), validateVolunteer(String volunteer)
 * validateAlphanumeric(String data) and validatePassword(String password)
 * This class is also an example of application of Single Responsibility
 * Principle as it is only main function is to validate inputs
 * @return true if validation constraints for the given method are followed
 * otherwise false
 */
public class Validation {
  /**
   * This method is used to validate email input from user
   * @description: Input constraint is that it should contain @ in between and .
   * in last part
   * @param email
   * @return true if validation constraints in description are followed
   * otherwise false
   */
  public boolean validateEmail(String email)  {
    String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    // Compile the ReGex
    Pattern p = Pattern.compile(regex);

    if (email == null) { return false; }

    //Matching between given Email and regular expression
    Matcher m = p.matcher(email);

    // Return true the name matched the ReGex

    if (m.matches() == true)
    { return true; }
    else { return false; }
  }

  /**
   * This method is used to validate Name input from user
   * @description: Input constraint is that there should not be any . or space in
   * first charactor
   * @param name
   * @return true if validation constraints in description are followed
   * otherwise false
   */
  public boolean validateName(String name)
  {
    //Regex to check valid name.
    String regex = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$";

    // Compile the ReGex
    Pattern p = Pattern.compile(regex);

    // If the name is empty return false
    if (name == null) { return false; }

    //Matching between given name and regular expression
    Matcher m = p.matcher(name);

    // Return if the name matched the ReGex
    if (m.matches() == true)
    { return true; }
    else { return false; }
  }

  /**
   * This method is used to validate Date of Birth input
   * @description Input constraint is that Date format should be any of this
   * DD/MM/YYYY or DD.MM.YYYY or DD-MM-YYYY
   * @param dob
   * @return true if validation constraints in description are followed
   * otherwise false
   */
  public boolean validateDob(String dob){
    //Regex for date of birth
    String dateRegEx="^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))" +
        "(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]" +
        "|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])" +
        "|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";

    // Compile the ReGex
    Pattern p = Pattern.compile(dateRegEx);

    // If the dob is empty return false
    if (dob == null) { return false; }

    // Matching given dob and regular expression.
    Matcher m = p.matcher(dob);

    // Return true if the dob matched the ReGex
    if (m.matches() == true) { return true; }
    else { return false; }
  }

  /**
   * This is method to validate contact no input
   * @description  Input constraint is that entered contact number length
   * should be   it contact
   * number
   * @param contactno
   * @return true if validation constraints in description are followed
   * otherwise false
   */
  public boolean validateContactNo(String contactno){

    String ContactRegEx="^\\d{10}$";
    // Compile the ReGex
    Pattern p = Pattern.compile(ContactRegEx);

    if (contactno == null) { return false; }

    // Matching between given contactno and regular expression.
    Matcher m = p.matcher(contactno);

    // Return true if the contactno matched the ReGex

    if (m.matches() == true)
    { return true; }
    else { return false; }
  }

  /**
   * This is method to validate gender input
   * @description Input constraint is that entered gender should be from the option given below
   * @param gender
   * @returntrue if validation constraints in description are followed
   * otherwise false
   */
  public boolean validateGender(String gender)
  {
    //List of the approved options if matched then return true
    if(gender.equals("f") || gender.equals("F") || gender.equals("female") || gender.equals("Female") || gender.equals("FEMALE")  ||
        gender.equals("m")  || gender.equals("M") || gender.equals("male") || gender.equals("Male") || gender.equals("MALE"))
    { return true; }
    else { return false; }
  }

  /**
   * This is method to validate bloodGroup input
   * @description Input constraint is that entered bloodgroup should be from the option given below
   * @param bloodgroup
   * @return true if validation constraints in description are followed
   * otherwise false
   */
  public boolean validateBloodGroup(String bloodgroup)
  {
    ////List of the approved options if matched then return true
    if(bloodgroup.equals("A+")|| bloodgroup.equals("A+ve")|| bloodgroup.equals("A-ve") || bloodgroup.equals("A-")
        || bloodgroup.equals("a+") || bloodgroup.equals("a+ve") || bloodgroup.equals("a-ve")
        || bloodgroup.equals("a-") ||bloodgroup.equals("A+VE") ||bloodgroup.equals("A-VE")
        ||bloodgroup.equals("a+VE") ||bloodgroup.equals("a-VE") || bloodgroup.equals("B+")
        || bloodgroup.equals("B+ve") || bloodgroup.equals("B-ve") || bloodgroup.equals("B-")
        || bloodgroup.equals("b+") || bloodgroup.equals("b+ve") || bloodgroup.equals("b-ve")
        || bloodgroup.equals("b-")||bloodgroup.equals("B+VE")  ||bloodgroup.equals("B-VE")
        ||bloodgroup.equals("b+VE") ||bloodgroup.equals("b-VE") ||bloodgroup.equals("O+")
        || bloodgroup.equals("O+ve") || bloodgroup.equals("O-ve") || bloodgroup.equals("O-")
        || bloodgroup.equals("o+") || bloodgroup.equals("o+ve")  || bloodgroup.equals("o-ve")
        || bloodgroup.equals("o-")||bloodgroup.equals("O+VE") ||bloodgroup.equals("O-VE")
        ||bloodgroup.equals("o+VE") ||bloodgroup.equals("o-VE") || bloodgroup.equals("AB+")
        || bloodgroup.equals("AB+ve") || bloodgroup.equals("AB-ve") || bloodgroup.equals("AB-")
        || bloodgroup.equals("ab+") || bloodgroup.equals("ab+ve")  || bloodgroup.equals("ab-ve")
        || bloodgroup.equals("ab-")||bloodgroup.equals("AB+VE") ||bloodgroup.equals("AB-VE")
        ||bloodgroup.equals("ab+VE") ||bloodgroup.equals("ab-VE") )
    { return true; }
    else { return false; }
  }

  /**
   * This is method to validate volunteer choice input
   * @description  Input constraint is that entered volunteer choice should be from the given options
   * @param volunteer
   * @return true if validation constraints in description are followed
   * otherwise false
   */
  public boolean validateVolunteer(String volunteer)
  {
    //List of the approved options if matched then return true
    if(volunteer.equals("yes") || volunteer.equals("YES") || volunteer.equals("NO") ||volunteer.equals("Yes")
        || volunteer.equals("no") || volunteer.equals("Y") ||volunteer.equals("No")
        || volunteer.equals("N")  || volunteer.equals("y")  || volunteer.equals("n"))
    { return true; }
    else { return false; }
  }

  /**
   * This is method to validate alphanumeric input
   * @description Constraints are that String can only contain alphabets and number
   * along and also comma is allowed
   * @param data
   * @return true if validation constraints in description are followed
   * otherwise false
   */
  public boolean validateAlphanumeric(String data)
  {
    //Regex for Alphanumeric
    String AlphanumericRegEx="^[a-zA-Z0-9,\\s]+$";

    // Compile the ReGex
    Pattern p = Pattern.compile(AlphanumericRegEx);
    if (data == null) {
      return false;
    }

    // Matching given Alphanurmic String and regular expression.
    Matcher m = p.matcher(data);

    // Return true if the Alphanurmic matched the ReGex;
    if (m.matches() == true)
    { return true; }
    else { return false; }

  }

  /**
   * This is method to validate password input
   * @description Password Constraints :
   * A digit must occur at least once.
   * A lower case alphabet must occur at least once.
   * An upper case alphabet that must occur at least once.
   * A special character that must occur at least once.
   * White spaces don’t allowed in the entire string.
   *  At least 8 characters and at most 20 characters.
   * @param password
   * @return true if validation constraints in description are followed
   * otherwise false
   */
  public boolean validatePassword(String password)
  {

    String PasswordRegEx="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
    Pattern p = Pattern.compile(PasswordRegEx);

    if (password == null) {
      return false;
    }

    // Matching between given Password and regular expression.
    Matcher m = p.matcher(password);

    // Return true if the Password matched the ReGex
    if (m.matches() == true)
    { return true; }
    else { return false; }
  }
}