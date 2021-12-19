package com.centrailized_medi_application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityQuestionsTest {
  private SecurityQuestions sq = new SecurityQuestions();

  @Test
  @DisplayName("To get answer1")
  void getAnswer1() {
    sq.setAnswer1("answer11");
    assertEquals("answer11", sq.getAnswer1(), "answer1 getter fails");
  }

  @Test
  @DisplayName("To set answer1")
  void setAnswer1() {
    sq.setAnswer1("answer12");
    assertEquals("answer12", sq.getAnswer1(), "answer1 setter fails");
  }

  @Test
  @DisplayName("To get answer2")
  void getAnswer2() {
    sq.setAnswer2("answer21");
    assertEquals("answer21", sq.getAnswer2(), "answer2 getter fails");
  }

  @Test
  @DisplayName("To set answer2")
  void setAnswer2() {
    sq.setAnswer2("answer22");
    assertEquals("answer22", sq.getAnswer2(), "answer2 setter fails");
  }

  @Test
  @DisplayName("To get answer3")
  void getAnswer3() {
    sq.setAnswer3("answer31");
    assertEquals("answer31", sq.getAnswer3(), "answer3 getter fails");
  }

  @Test
  @DisplayName("To set answer3")
  void setAnswer3() {
    sq.setAnswer3("answer32");
    assertEquals("answer32", sq.getAnswer3(), "answer3 setter fails");
  }
}