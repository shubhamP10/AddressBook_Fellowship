package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.dbconnection.DBConnection;
import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.utility.UserInputs;
import com.bridgelabz.addressbook.utility.ValidateInputs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseCRUDOperations {
    PreparedStatement statement = null;
    UserInputs userInputs = new UserInputs();
    List<Person> personList = new ArrayList<>();
    private String firstName, lastName, address, city, state, phone, zip;

    public void addRecordToDB() {

        String insertQuery = "INSERT INTO person_details(first_name,last_name,address,city,state,phone,zip)" +
                "VALUES(?,?,?,?,?,?,?)";
        firstName = ValidateInputs.validateName("First Name");
        lastName = ValidateInputs.validateName("Last Name");
        phone = ValidateInputs.validatePhone();
        address = ValidateInputs.validateAddress();
        city = ValidateInputs.validateName("City");
        zip = ValidateInputs.validateZip();
        state = ValidateInputs.validateName("State");
        try (Connection con = DBConnection.getConnection()) {
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
        }
        System.out.println("Person Data Inserted Successfully");
    }

    public List<Person> getDataFromDB() {
        String getQuery = "SELECT * FROM person_details";
        try (Connection con = DBConnection.getConnection()) {
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

    public void editPersonDetails() throws SQLException {
        String[] name = userInputs.getName();
        int flag = 0;
        try (Connection con = DBConnection.getConnection()) {
            while (flag == 0) {
                int choice = userInputs.editMenu();
                switch (choice) {
                    case 1:
                        address = ValidateInputs.validateAddress();
                        this.updateField(con, name[0], name[1], "address", address);
                        break;
                    case 2:
                        city = ValidateInputs.validateName("City");
                        this.updateField(con, name[0], name[1], "city", city);
                        break;
                    case 3:
                        state = ValidateInputs.validateName("State");
                        this.updateField(con, name[0], name[1], "state", state);
                        break;
                    case 4:
                        phone = ValidateInputs.validatePhone();
                        this.updateField(con, name[0], name[1], "phone", phone);
                        break;
                    case 5:
                        zip = ValidateInputs.validateZip();
                        this.updateField(con, name[0], name[1], "zip", zip);
                        break;
                    case 6:
                        flag = 1;
                        break;
                    default:
                        System.out.println("Please Enter Valid Option");
                }
            }
        }
    }

    private void updateField(Connection con, String firstName,
                             String lastName, String fieldName, String newValue) throws SQLException {
        String updateQuery = "UPDATE person_details SET " + fieldName + " = '" + newValue +
                "' WHERE first_name = '" + firstName + "' AND last_name = '" + lastName + "'";

        statement = con.prepareStatement(updateQuery);
        statement.executeUpdate();
        System.out.println("Address Updated Successfully...");
    }

    public void deleteRecord() {
        String[] name = userInputs.getName();
        String deleteQuery = "DELETE FROM person_details Where first_name = '" + name[0] + "' AND last_name = '" + name[1] + "'";
        try (Connection con = DBConnection.getConnection()) {
            statement = con.prepareStatement(deleteQuery);
            statement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Record Deleted Successfully");
    }
}
