package com.centrailized_medi_application;

/**
 * @author Kazi Hasan
 * @description: holds methods to load patient lat/lon and suggested list of doctors from database
 * loadPatientLatLon()-used to load current patient latitude and longitude from database
 * loadDoctorSuggestions()-used to get filtered list of doctors from the database
 */
public interface ISuggestionsPersistence {

  void loadPatientLatLon(IPatientSuggestions patientSuggestions);

  void loadDoctorSuggestions(IPatientSuggestions patientSuggestions);


}
