package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.utility.InputUtil;
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
            while (result.next()) {
                firstName = result.getString("first_name");
                lastName = result.getString("last_name");
                address = result.getString("address");
                city = result.getString("city");
                state = result.getString("state");
                phone = result.getString("phone");
                zip = String.valueOf(result.getInt("zip"));
                personList.add(new Person(firstName, lastName, address, city, state, zip, phone));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personList;
    }

    public void editPersonDetails(Connection con) {
        System.out.println("Enter First Name And Last Name To Edit Person Details");
        System.out.println("Enter First Name");
        firstName = InputUtil.getStringValue();
        System.out.println("Enter Last Name");
        lastName = InputUtil.getStringValue();
        int flag = 0;
        while (flag == 0) {
            System.out.println("What You Want to edit...\n"
                    + "\t1: Address\n"
                    + "\t2: city\n"
                    + "\t3: State\n"
                    + "\t4: Phone\n"
                    + "\t5: Zip Code\n"
                    + "\t6. Save And Exit\n");
            int choice = InputUtil.getIntValue();
            switch (choice) {
                case 1:
                    System.out.print("Enter new Address : ");
                    address = InputUtil.getStringValue();
                    this.updateField(con, firstName, lastName, "address", address);
                    flag = 1;
                    break;
                case 2:
                    System.out.print("Enter new City : ");
                    city = InputUtil.getStringValue();
                    this.updateField(con, firstName, lastName, "city", city);
                    break;
                case 3:
                    System.out.print("Enter new State : ");
                    state = InputUtil.getStringValue();
                    this.updateField(con, firstName, lastName, "state", state);
                    break;
                case 4:
                    System.out.print("Enter new Phone : ");
                    phone = InputUtil.getStringValue();
                    this.updateField(con, firstName, lastName, "phone", phone);
                    break;
                case 5:
                    System.out.print("Enter new Zip Code : ");
                    zip = InputUtil.getStringValue();
                    this.updateField(con, firstName, lastName, "zip", zip);
                    break;
                case 6:
                    flag = 1;
                    break;
                default:
                    System.out.println("Please Enter Valid Option");
            }
        }
    }

    private void updateField(Connection con, String firstName, String lastName, String fieldName, String newValue) {
        String updateQuery = "UPDATE person_details SET " + fieldName + " = '" + newValue + "' WHERE first_name = '" + firstName + "' AND last_name = '" + lastName + "'";
        try {
            PreparedStatement statement = con.prepareStatement(updateQuery);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Address Updated Successfully...");
    }

    public void deleteRecord(Connection con) {
        System.out.println("Enter First Name And Last Name To Edit Person Details");
        System.out.println("Enter First Name");
        firstName = InputUtil.getStringValue();
        System.out.println("Enter Last Name");
        lastName = InputUtil.getStringValue();
        String deleteQuery = "DELETE FROM person_details Where first_name = '" + firstName + "' AND last_name = '" + lastName + "'";
        try {
            PreparedStatement statement = con.prepareStatement(deleteQuery);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Record Deleted Successfully");
    }
}
