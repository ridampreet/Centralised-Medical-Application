package com.centrailized_medi_application;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class LoginAuthorisationTest {
    @Disabled("Test disabled as the method requires custom input from user")
    @Test
    public void resetValidUser(){
        Integer retry = 3;
        boolean clearedSecurityQues = true;
        String user_name = "Neelay@gmail.com";
        LoginAuthorisation lg = new LoginAuthorisation();
        assertEquals("Password reset was successful !", lg.resetPassword(user_name, clearedSecurityQues, retry));

    }
    @Test
    public void resetInavlidUser() {
        Integer retry = 3;
        boolean clearedSecurityQues = true;
        String user_name = "greg.house@gmail.com";
        LoginAuthorisation lg = new LoginAuthorisation();
        assertEquals("User is not registered !", lg.resetPassword( user_name,clearedSecurityQues,retry));

    }

    @Test
    public void resetCouldNotClearSecQues() {
        Integer retry = 3;
        boolean clearedSecurityQues = false;
        String user_name = "greg.house@gmail.com";
        LoginAuthorisation lg = new LoginAuthorisation();
        assertEquals("Please answer the security question first !", lg.resetPassword( user_name,clearedSecurityQues,retry));


    }



    @Disabled("Asks for user input")
    @Test
    void verifySecurityAnswer() throws SQLException {
        boolean[] creds = new boolean[2];
        DB_Connection db = new DB_Connection();
        Connection connect = db.createConnection();
        String user_name = "maxone@gmail.com";
        PreparedStatement answer = connect.prepareStatement("select * from patient_info where emailId=?");
        answer.setString(1, user_name);
        ResultSet s1 = answer.executeQuery();
        s1.next();
        LoginAuthorisation key = new LoginAuthorisation();
        s1.close();
        answer.close();
        db.close();
        assertEquals(s1.getString("security_answer_1"), key.getSecurityQuestion(user_name), "Incorrect Security Answer");

    }
    @Disabled("Asks for user input")
    @Test
    void verifySecurityInvalid() throws SQLException, IOException, ClassNotFoundException {
        boolean[] creds = new boolean[2];
        String user_name = "Ridam@gmail.com";
        LoginAuthorisation key = new LoginAuthorisation();
        assertEquals("User Does not Exist", key.getSecurityQuestion(user_name));
    }

    /*
     *Author: Ridampreet Singh
     */
    @Disabled("Disabled as it asks for user input")
    @Test
    void verifySecurityAnswer_for_Doctor() throws SQLException, IOException, ClassNotFoundException {
        DB_Connection db = new DB_Connection();
        Connection connect = db.createConnection();
        String user_name = "doctor2@gmail.com";
        PreparedStatement answer = connect.prepareStatement("select * from doctor_info where emailId=?");
        answer.setString(1, user_name);
        ResultSet s1 = answer.executeQuery();
        s1.next();
        LoginAuthorisation key = new LoginAuthorisation();
        s1.close();
        answer.close();
        db.close();
        assertEquals(s1.getString("security_answer_1"), key.getSecurityQuestion(user_name), "Incorrect Security Answer");
    }

    /*
     *Author: Ridampreet Singh
     */
    @Disabled("Disabled as it asks for user input")
    @Test
    void verifyDoctorInvalid()  {
        String user_name = "Ridam@gmail.com";
        LoginAuthorisation key = new LoginAuthorisation();
        assertEquals("User Does not Exist", key.getSecurityQuestion(user_name));
    }
}