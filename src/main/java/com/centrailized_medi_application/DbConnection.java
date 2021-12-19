package com.centrailized_medi_application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * author:Ridampreet Singh
 * DbConnection interface implemented by the classes.
 * getDetails()-method will return boolean array containing the validity status of the entered credentials.
 * createConnection()-returns a connection so that the connection can be established with the database.
 */
public interface DbConnection {

  List<Object> getDetails() throws SQLException, IOException, ClassNotFoundException;

  Connection createConnection();

  void close() throws SQLException;

}
