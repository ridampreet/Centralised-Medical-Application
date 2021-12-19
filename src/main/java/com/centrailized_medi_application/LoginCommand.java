package com.centrailized_medi_application;

/**
 * @author Aditya Jain
 * @description: Abstract class LoginCommand has abstract methods such as execute() and confirmation().
 * execute() triggers actions such as fetch(), validate() and authenticate() when user is trying to login
 * confirmation() triggers execute() whenever user agrees to proceed with
 */
public abstract class LoginCommand {
  protected abstract void execute();
  protected abstract void confirmation();
}
