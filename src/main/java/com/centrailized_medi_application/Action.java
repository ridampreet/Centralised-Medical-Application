package com.centrailized_medi_application;

/**
 * @author Aditya Jain
 * @description: Action is an Ivoker class, which sets command and puts the command to work
 * with the help of run().
 */
public class Action {
  LoginCommand command; // command object of class LoginCommand

  /**
   * This method sets the Command.
   *
   * @return void
   * @Param LoginCommand command - command object
   */
  public void setCommand(LoginCommand command) {
    this.command = command;
  }

  /**
   * This method triggers the confirmation() defined inside LoginCommand object.
   *
   * @return void
   * @Param void
   */
  public void run() {
    command.confirmation();
  }
}
