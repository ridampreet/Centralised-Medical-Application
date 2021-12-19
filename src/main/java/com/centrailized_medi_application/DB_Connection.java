package com.centrailized_medi_application;

import java.io.*;
import java.sql.*;
import java.util.List;
import java.util.Properties;

/**
 * author:Ridampreet Singh & Aditya Jain
 * DbConnection interface implemented by the classes.
 * Constructors take parameters as config file to get database details.
 * getDetails()-method will return boolean array containing the validity status of the entered credentials.
 * createConnection()-returns a connection so that the interaction with the database can begin.
 */
public class DB_Connection implements DbConnection {

  private String url;
  private String username;
  private String password;
  private static Connection connection = null;
  private boolean res_id = false;
  private boolean res_pass = false;
  private String configFile;
  private String u_name;
  private String u_pass;
  Properties pr = null;

  public DB_Connection()  {
    configFile = "src/main/resources/config_Production.properties";
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      String[] fullFileName = configFile.split("/", 0);
      String fileName = fullFileName[3];
      InputStream f1 = getClass().getResourceAsStream(fileName);
      pr = new Properties();
      pr.load(f1); //load the details from the properties file
      url = pr.getProperty("database");
      username = pr.getProperty("user");
      password = pr.getProperty("password");
      connection = DriverManager.getConnection(url, username, password);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public DB_Connection(String u_name, String u_pass) {
    try {
      configFile = "src/main/resources/config_Production.properties";
      Class.forName("com.mysql.cj.jdbc.Driver");
      String[] fullFileName = configFile.split("/", 0);
      String fileName = fullFileName[3];
      InputStream f1 = getClass().getResourceAsStream(fileName);
      pr = new Properties();
      pr.load(f1);  //load the details from the properties file
      url = pr.getProperty("database");
      username = pr.getProperty("user");
      password = pr.getProperty("password");
      this.u_name = u_name;
      this.u_pass = u_pass;
      connection = DriverManager.getConnection(url, username, password);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * The constructor DB_Connection takes parameters as configFile containing the database details, the username and the password,
   * further eastablishes a connection with the database.
   * @throws IOException
   * @throws ClassNotFoundException
   * @throws SQLException
   */
  public DB_Connection(String configFile, String u_name, String u_pass) throws IOException, ClassNotFoundException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    String[] fullFileName = configFile.split("/", 0);
    String fileName = fullFileName[3];
    InputStream f1 = getClass().getResourceAsStream(fileName);
    pr = new Properties();
    pr.load(f1);  //load the details from the properties file
    url = pr.getProperty("database");
    username = pr.getProperty("user");
    password = pr.getProperty("password");
    this.configFile = configFile;
    this.u_name = u_name;
    this.u_pass = u_pass;
    connection = DriverManager.getConnection(url, username, password);
  }

  /**
   * Constructor takes parameter only as a configFile which contains the details of database and then eastablishes a connection with the database
   * @param configFile
   * @throws ClassNotFoundException
   * @throws IOException
   * @throws SQLException
   */

  public DB_Connection(String configFile) throws ClassNotFoundException, IOException, SQLException {
    Class.forName("com.mysql.cj.jdbc.Driver");
    String[] fullFileName = configFile.split("/", 0);
    String fileName = fullFileName[3];
    InputStream f1 = getClass().getResourceAsStream(fileName);
    pr = new Properties();
    pr.load(f1); //load the details from the properties file
    url = pr.getProperty("database");
    username = pr.getProperty("user");
    password = pr.getProperty("password");
    connection = DriverManager.getConnection(url, username, password);
  }

  /**
   * The method createConnection() returns a created connection of the database.
   * @return
   */

  //return the connection created in the constructor of this class.
  @Override
  public Connection createConnection() {
    return connection;
  }

  /**
   *This method closes the connection
   * @throws SQLException
   */

  @Override
  public void close() throws SQLException {
    connection.close();
  }

  /**
   * Method uses the username and the password stored in local variables to check if the users are registered with the application.
   * @return
   * @throws SQLException
   * @throws IOException
   * @throws ClassNotFoundException
   */

  //return credential array containing info if the username and password are valid.
  @Override
  public List<Object> getDetails() throws SQLException, IOException, ClassNotFoundException {
    DB_Layer db = DB_Layer.singleConnection();
    List<Object> listResults = db.getCredStatus(this.u_name, this.u_pass);
    db.close();
    return listResults;
  }
}
