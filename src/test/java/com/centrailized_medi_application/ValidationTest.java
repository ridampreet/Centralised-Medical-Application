package com.centrailized_medi_application;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationTest {
    // Test Cases for Email Validation
    @Test
    void validateEmailTestOne()
    {
        Validation emailtestone = new Validation();
        //Temporary Variable to check the output
        assertEquals(true,emailtestone.validateEmail("nilay@gmail.com"),"Validation Test" +
                " for email validation failed ");

        assertEquals(true,emailtestone.validateEmail("one@gmail.com"),"Validation Test" +
                " for email validation failed ");

        assertEquals(true,emailtestone.validateEmail("1234@gmail.com"),"Validation Test" +
                " for email validation failed ");
    }
    @Test
    void validateEmailTestTwo()
    {
        Validation emailtesttwo = new Validation();
        //Temporary Variable to check the output
        assertEquals(false,emailtesttwo.validateEmail("nilaygmail.com"),"Validation Test" +
                " for email validation failed ");

        assertEquals(false,emailtesttwo.validateEmail("oneemail.com"),"Validation Test" +
                " for email validation failed ");

        assertEquals(false,emailtesttwo.validateEmail("1234gmail"),"Validation Test" +
                " for email validation failed ");
    }

    // Test Cases for Name Validation
    @Test
    void validateNameTestOne()
    {
        Validation nametestone = new Validation();
        assertEquals(true,nametestone.validateName("Neelay Goswami"),"Validation Test" +
                " for name validation failed ");

        assertEquals(true,nametestone.validateName("Ironman"),"Validation Test" +
                " for name validation failed ");

        assertEquals(true,nametestone.validateName("captainamerica"),"Validation Test" +
                " for name validation failed ");

        assertEquals(true,nametestone.validateName("Captain .A "),"Validation Test" +
                " for name validation failed ");
    }

    @Test
    void validateNameTestTwo()
    {
        Validation nametesttwo = new Validation();
        assertEquals(false,nametesttwo.validateName(".wonder woman"),"Validation Test" +
                " for name validation failed ");

        assertEquals(false,nametesttwo.validateName("Iron,man"),"Validation Test" +
                " for name validation failed ");

        assertEquals(false,nametesttwo.validateName(". captain america"),"Validation Test" +
                " for name validation failed ");
    }

    // Test Cases for Date of Birth Validation
    @Test
    void validateDobTestOne()
    {
        Validation dobtestone = new Validation();
        assertEquals(true,dobtestone.validateDob("06/07/1999"),"Validation Test" +
                " for Date of birth validation failed ");
        assertEquals(true,dobtestone.validateDob("01.01.2005"),"Validation Test" +
                " for Date of birth validation failed ");
        assertEquals(true,dobtestone.validateDob("06-10-2004"),"Validation Test" +
                " for Date of birth validation failed ");
        assertEquals(true,dobtestone.validateDob("6-1-2004"),"Validation Test" +
                " for Date of birth validation failed ");
    }

    @Test
    void validateDobTestTwo()
    {
        Validation dobtesttwo = new Validation();
        assertEquals(false,dobtesttwo.validateDob("13/13/2009"),"Validation Test" +
                " for Date of birth validation failed ");
        assertEquals(false,dobtesttwo.validateDob("12.12/2006"),"Validation Test" +
                " for Date of birth validation failed ");
        assertEquals(false,dobtesttwo.validateDob("15-15-1980"),"Validation Test" +
                " for Date of birth validation failed ");
        assertEquals(false,dobtesttwo.validateDob("10/5.1980"),"Validation Test" +
                " for Date of birth validation failed ");
    }

    // Test Cases for ContactNo Validation
    @Test
    void validateContactTestOne()
    {
        Validation contacttestone = new Validation();
        assertEquals(true,contacttestone.validateContactNo("1234567890"),"Validation Test" +
                " for Contact number validation failed ");
        assertEquals(true,contacttestone.validateContactNo("9876543217"),"Validation Test" +
                " for Contact number validation failed ");
        assertEquals(true,contacttestone.validateContactNo("2342356677"),"Validation Test" +
                " for Contact number validation failed ");

    }
    @Test
    void validateContactTestTwo()
    {
        Validation contacttesttwo = new Validation();
        assertEquals(false,contacttesttwo.validateContactNo("3330234567890"),"Validation Test" +
                " for Contact number validation failed ");
        assertEquals(false,contacttesttwo.validateContactNo("34567890"),"Validation Test" +
                " for Contact number validation failed ");
        assertEquals(false,contacttesttwo.validateContactNo("+13302345678"),"Validation Test" +
                " for Contact number validation failed ");

    }

    // Test Cases for BloodGroup Validation
    @Test
    void validateBloodGroupTestOne()
    {
        Validation bloodgrouptestone = new Validation();
        assertEquals(true,bloodgrouptestone.validateBloodGroup("O+ve"),"Validation Test" +
                " for bloodgroup validation failed ");
        assertEquals(true,bloodgrouptestone.validateBloodGroup("ab+ve"),"Validation Test" +
                " for bloodgroup validation failed ");
        assertEquals(true,bloodgrouptestone.validateBloodGroup("b-"),"Validation Test" +
                " for bloodgroup validation failed ");
        assertEquals(true,bloodgrouptestone.validateBloodGroup("B-"),"Validation Test" +
                " for bloodgroup validation failed ");
        assertEquals(true,bloodgrouptestone.validateBloodGroup("a+VE"),"Validation Test" +
                " for bloodgroup validation failed ");
    }
    @Test
    void validateBloodGroupTestTwo()
    {
        Validation bloodgrouptesttwo = new Validation();
        assertEquals(false,bloodgrouptesttwo.validateBloodGroup("Opositive"),"Validation Test" +
                " for bloodgroup validation failed ");
        assertEquals(false,bloodgrouptesttwo.validateBloodGroup("o"),"Validation Test" +
                " for bloodgroup validation failed ");
        assertEquals(false,bloodgrouptesttwo.validateBloodGroup("ab"),"Validation Test" +
                " for bloodgroup validation failed ");
        assertEquals(false,bloodgrouptesttwo.validateBloodGroup("b"),"Validation Test" +
                " for bloodgroup validation failed ");

    }

    // Test Cases for Volunteer Validation
    @Test
    void validateVolunteerTestOne()
    {
        Validation validatevolunteertestone = new Validation();
        assertEquals(true,validatevolunteertestone.validateVolunteer("y"),"Validation Test" +
                " for volunteer validation failed ");
        assertEquals(true,validatevolunteertestone.validateVolunteer("n"),"Validation Test" +
                " for volunteer validation failed ");
        assertEquals(true,validatevolunteertestone.validateVolunteer("yes"),"Validation Test" +
                " for volunteer validation failed ");
        assertEquals(true,validatevolunteertestone.validateVolunteer("NO"),"Validation Test" +
                " for volunteer validation failed ");

    }
    @Test
    void validateVolunteerTestTwo()
    {
        Validation validatevolunteertesttwo = new Validation();
        assertEquals(false,validatevolunteertesttwo.validateVolunteer("noo"),"Validation Test" +
                " for volunteer validation failed ");
        assertEquals(false,validatevolunteertesttwo.validateVolunteer("YEs"),"Validation Test" +
                " for volunteer validation failed ");
        assertEquals(false,validatevolunteertesttwo.validateVolunteer("nO"),"Validation Test" +
                " for volunteer validation failed ");
        assertEquals(false,validatevolunteertesttwo.validateVolunteer("yES"),"Validation Test" +
                " for volunteer validation failed ");
    }

    // Test Cases for Alphanumeric Strings Validation
    @Test
    void validateAlphanumericTestOne()
    {
        Validation validatealphanumerictesone = new Validation();
        assertEquals(true,validatealphanumerictesone.validateAlphanumeric("testone1213"),"Validation Test" +
                " for Alphanumeric validation failed ");
        assertEquals(true,validatealphanumerictesone.validateAlphanumeric("test,one,123"),"Validation Test" +
                " for Alphanumeric validation failed ");
        assertEquals(true,validatealphanumerictesone.validateAlphanumeric("123213test"),"Validation Test" +
                " for Alphanumeric validation failed ");
    }
    @Test
    void validateAlphanumericTestTwo()
    {
        Validation validatealphanumerictestwo = new Validation();
        assertEquals(false,validatealphanumerictestwo.validateAlphanumeric("testtwo1213@"),"Validation Test" +
                " for Alphanumeric validation failed ");
        assertEquals(false,validatealphanumerictestwo.validateAlphanumeric("#testtwo1213"),"Validation Test" +
                " for Alphanumeric validation failed ");
        assertEquals(false,validatealphanumerictestwo.validateAlphanumeric("testtwo_1213"),"Validation Test" +
                " for Alphanumeric validation failed ");
        assertEquals(false,validatealphanumerictestwo.validateAlphanumeric("  testtwo_1213"),"Validation Test" +
                " for Alphanumeric validation failed ");
    }

    // Test Cases for Password Validation
    @Test
    void validatePasswordTestOne()
    {
        Validation validatepasswordtestone = new Validation();
        assertEquals(true,validatepasswordtestone.validatePassword("Test@one123"),"Validation Test" +
                " for Password validation failed ");
        assertEquals(true,validatepasswordtestone.validatePassword("one@Test12"),"Validation Test" +
                " for Password validation failed ");
        assertEquals(true,validatepasswordtestone.validatePassword("one$Test2021"),"Validation Test" +
                " for Password validation failed ");
        assertEquals(true,validatepasswordtestone.validatePassword("2021#One"),"Validation Test" +
                " for Password validation failed ");
    }
    @Test
    void validatePasswordTestTwo()
    {
        Validation validatepasswordtesttwo = new Validation();
        assertEquals(false,validatepasswordtesttwo.validatePassword("12345"),"Validation Test" +
                " for Password validation failed ");
        assertEquals(false,validatepasswordtesttwo.validatePassword("12345test"),"Validation Test" +
                " for Password validation failed ");
        assertEquals(false,validatepasswordtesttwo.validatePassword(" 12345@TESTtwo"),"Validation Test" +
                " for Password validation failed ");
        assertEquals(false,validatepasswordtesttwo.validatePassword("testtwo@123"),"Validation Test" +
                " for Password validation failed ");
    }

    // Test Cases for Gender Validation
    @Test
    void validateGenderTestOne()
    {
        Validation validategendertestone = new Validation();
        assertEquals(true,validategendertestone.validateGender("f"),"Validation Test" +
            " for Gender validation failed ");
        assertEquals(true,validategendertestone.validateGender("MALE"),"Validation Test" +
                " for Gender validation failed ");
        assertEquals(true,validategendertestone.validateGender("F"),"Validation Test" +
                " for Gender validation failed ");
        assertEquals(true,validategendertestone.validateGender("Male"),"Validation Test" +
                " for Gender validation failed ");
        assertEquals(true,validategendertestone.validateGender("M"),"Validation Test" +
                " for Gender validation failed ");
        assertEquals(true,validategendertestone.validateGender("Female"),"Validation Test" +
                " for Gender validation failed ");
    }
    @Test
    void validateGenderTesttwo()
    {
        Validation validategendertesttwo = new Validation();
        assertEquals(false,validategendertesttwo.validateGender("MaLe"),"Validation Test" +
                " for Gender validation failed ");
        assertEquals(false,validategendertesttwo.validateGender("Fe"),"Validation Test" +
                " for Gender validation failed ");
        assertEquals(false,validategendertesttwo.validateGender("FEMAle"),"Validation Test" +
                " for Gender validation failed ");
        assertEquals(false,validategendertesttwo.validateGender("mALE"),"Validation Test" +
                " for Gender validation failed ");

    }
}
