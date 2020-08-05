package com.bridgelabz.addressbook.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public final static String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public final static String DB_URL = "jdbc:mysql://localhost:3306/AddressBook";
    public final static String DB_USERNAME = "root";
    public final static String DB_PASSWORD = "shubhamp";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Connection con = null;

        // load the Driver Class
        Class.forName(DB_DRIVER_CLASS);

        // create the connection now
        con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        System.out.println("DB Connection created successfully");
        return con;
    }
}
