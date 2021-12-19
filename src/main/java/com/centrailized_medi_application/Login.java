package com.centrailized_medi_application;

/**
 * @author Aditya Jain
 * @description: Login abstract class has abstract methods such as
 * fetch(), validate() and authenticate(). LoginCommand Object works on Login class. (Command Design Pattern)
 */
public abstract class Login {
  protected abstract void fetch(String username, String Psswd);
  protected abstract void validate();
  protected abstract void authenticate();
}
