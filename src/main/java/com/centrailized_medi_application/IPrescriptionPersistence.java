package com.centrailized_medi_application;

/**
 * @author Kazi Hasan
 * @description: holds methods for saving and loading prescription data to and from database
 * savePrescription()-used to save the current prescription given by doctor into database
 * loadPrescription()-used to load the current prescription for the current patient from the database
 */

public interface IPrescriptionPersistence {

  void savePrescription(IPrescription prescription);

  void loadPrescription(IPrescription prescription);

}
