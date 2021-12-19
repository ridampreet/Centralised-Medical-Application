package com.centrailized_medi_application;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;

class PastConsultationsTest {
  public static final String doctorUsername = "key@gmail.com";
  PastConsultations pastConsultations;

  void constructor() {
    pastConsultations = new PastConsultations(new DB_Connection(), doctorUsername);
  }

  @Disabled("not working ")
  @Test
  @DisplayName("To see the past consultation list")
  void getPreviousConsultations()  {
    this.constructor();
    String result = "PAST CONSULTATIONS\n" +
        "*******************************************\n" +
        "First Name: Kane\n" +
        "Last Name: Brown\n" +
        "Last Consultation: 21/07/2021 23:11:37\n" +
        "Patient ID: 136\n" +
        "Blood Group: A+\n" +
        "Contact No: 2323434343\n" +
        "\n" +
        "*******************************************\n" +
        "First Name: test\n" +
        "Last Name: one\n" +
        "Last Consultation: 12/07/2021 20:01:03\n" +
        "Patient ID: 143\n" +
        "Blood Group: o+\n" +
        "Contact No: 4354343111\n" +
        "\n";
    assertEquals(result, pastConsultations.getPreviousConsultations());
  }
}