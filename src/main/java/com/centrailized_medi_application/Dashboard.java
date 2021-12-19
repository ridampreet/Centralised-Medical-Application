package com.centrailized_medi_application;


/**
 * @author Aditya Jain
 * @description: Dashboard is an abstract class. It has a functionality display().
 * The Class is extended by PatientDashboard & DoctorDashboard which when triggered
 * loads the dashboard for Patient & Doctor respectively.
 */
public abstract class Dashboard {
  public abstract void display();
}

