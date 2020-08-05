package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.utility.ValidateInputs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBOperations {
    private String firstName, lastName, address, city, state, phone, zip;
    public void addRecordToDB(Connection con) {

        String insertQuery = "INSERT INTO person_details(first_name,last_name,address,city,state,phone,zip)" +
                "VALUES(?,?,?,?,?,?,?)";
        firstName = ValidateInputs.validateName("First Name");
        lastName = ValidateInputs.validateName("Last Name");
        phone = ValidateInputs.validatePhone();
        address = ValidateInputs.validateAddress();
        city = ValidateInputs.validateName("City");
        zip = ValidateInputs.validateZip();
        state = ValidateInputs.validateName("State");
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(insertQuery);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, address);
            statement.setString(4, city);
            statement.setString(5, state);
            statement.setString(6, phone);
            statement.setInt(7, Integer.parseInt(zip));
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        System.out.println("Person Data Inserted Successfully");
    }

    public List<Person> getDataFromDB(Connection con) {
        String getQuery = "SELECT * FROM person_details";
        List<Person> personList = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement(getQuery);
            ResultSet result = statement.executeQuery();
            while (result.next()){
                firstName = result.getString("first_name");
                lastName = result.getString("last_name");
                address = result.getString("address");
                city = result.getString("city");
                state = result.getString("state");
                phone = result.getString("phone");
                zip = String.valueOf(result.getInt("zip"));
                personList.add(new Person(firstName,lastName,address,city,state,zip,phone));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personList;
    }
}
