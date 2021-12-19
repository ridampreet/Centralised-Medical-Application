package com.centrailized_medi_application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatientDetailsTest {
    private PatientDetails testcheck = new PatientDetails();

    @Test
    @DisplayName("To get patient's bloodgroup")
    void bloodGroupTest() {
        testcheck.setBloodGroup("o+ve");
        assertEquals("o+ve", testcheck.getBloodGroup(), "value of blood group not obtained");
    }

    @Test
    @DisplayName("To get patient's allergy")
    void allergyTest() {
        testcheck.setAllergy("PollenGrains");
        assertEquals("PollenGrains", testcheck.getAllergy(), "value of Allergy not obtained");
    }

    @Test
    @DisplayName("To get patient's Chronic Disease")
    void chronicDiseaseTest() {
        testcheck.setChronicDisease("tonsilits");
        assertEquals("tonsilits", testcheck.getChonicDisease(), "Information of Chronic Disease not obtained");
    }

    @Test
    @DisplayName("To get patient's Insurance Number")
    void insuranceNumberTest() {
        testcheck.setInsuranceNo("IN4444785");
        assertEquals("IN4444785", testcheck.getInsuranceNo(), "Information of Insurance Number not obtained");
    }

    @Test
    @DisplayName("To get patient's donorCardNo")
    void donorCardNoTest() {
        testcheck.setDonorCardNo("NGd888909");
        assertEquals("NGd888909", testcheck.getDonorCardNo(), "Information of DonorCard No. not obtained");
    }

    @Test
    @DisplayName("To get patient's Family Member Code")
    void familyMemberCodeTest() {
        testcheck.setFamilyMemberCode("DRG0002432");
        assertEquals("DRG0002432", testcheck.getFamilyMemberCode(), "Information of family member code not obtained");
    }

    @Test
    @DisplayName("To get patient's Volunteer Status")
    void volunteerTest() {
        testcheck.setVolunteer("yes");
        assertEquals("yes", testcheck.getVolunteer(), "Information of patient's volunteer status not obtained");
    }

}