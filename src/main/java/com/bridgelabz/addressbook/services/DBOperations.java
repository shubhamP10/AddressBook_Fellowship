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
    PreparedStatement statement = null;
    List<Person> personList = new ArrayList<>();
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
        try {
            statement = con.prepareStatement(getQuery);
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
        String updateQuery = "UPDATE person_details SET " + fieldName + " = '" + newValue +
                "' WHERE first_name = '" + firstName + "' AND last_name = '" + lastName + "'";
        try {
            statement = con.prepareStatement(updateQuery);
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
            statement = con.prepareStatement(deleteQuery);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Record Deleted Successfully");
    }

    public void sortRecords(Connection con) {
        System.out.println("Sort By...\n"
                + "1: First Name\n"
                + "2: City\n"
                + "3: State\n"
                + "4: Zip Code\n"
                + "5: Back");
        int choice = InputUtil.getIntValue();
        String sortQuery = "";
        switch (choice) {
            case 1:
                sortQuery = "SELECT * FROM person_details ORDER BY first_name ASC";
                this.getDataOnQuery(con, sortQuery).forEach(System.out::println);
                break;
            case 2:
                sortQuery = "SELECT * FROM person_details ORDER BY city ASC";
                this.getDataOnQuery(con, sortQuery).forEach(System.out::println);
                break;
            case 3:
                sortQuery = "SELECT * FROM person_details ORDER BY state ASC";
                this.getDataOnQuery(con, sortQuery).forEach(System.out::println);
                break;
            case 4:
                sortQuery = "SELECT * FROM person_details ORDER BY zip ASC";
                this.getDataOnQuery(con, sortQuery).forEach(System.out::println);
                break;
            case 5:
                return;
            default:
                System.out.println("Please Enter Valid Option...");
        }
    }

    private List<Person> getDataOnQuery(Connection con, String sortQuery) {
        List<Person> personData = new ArrayList<>();
        try {
            statement = con.prepareStatement(sortQuery);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                firstName = result.getString("first_name");
                lastName = result.getString("last_name");
                address = result.getString("address");
                city = result.getString("city");
                state = result.getString("state");
                phone = result.getString("phone");
                zip = String.valueOf(result.getInt("zip"));
                personData.add(new Person(firstName, lastName, address, city, state, zip, phone));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personData;
    }

    public void searchInRecords(Connection con) {
        int flag = 0;
        while (flag == 0) {
            System.out.println("1. Search By City\n" +
                    "2. Search By State\n" +
                    "3. Back\n" +
                    "Choose Your Option");
            int choice = InputUtil.getIntValue();
            String searchQuery = "";
            switch (choice) {
                case 1:
                    System.out.println("Enter City Name To Search: ");
                    String city = InputUtil.getStringValue();
                    searchQuery = "SELECT * FROM person_details WHERE city = '"+city+"'";
                    this.getDataOnQuery(con,searchQuery).forEach(System.out::println);

                    break;
                case 2:
                    System.out.println("Enter State Name To Search: ");
                    String state = InputUtil.getStringValue();
                    searchQuery = "SELECT * FROM person_details WHERE state = '"+state+"'";
                    this.getDataOnQuery(con,searchQuery).forEach(System.out::println);
                    break;
                case 3:
                    flag = 1;
                    break;
                default:
                    System.out.println("Please Enter Correct Option...");
            }
        }
    }
}
