package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.dbconnection.DBConnection;
import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.utility.UserInputs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchSortOperations {
    private final UserInputs userInputs = new UserInputs();
    private PreparedStatement statement;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String phone;
    private String zip;

    public SearchSortOperations() {
        statement = null;
        firstName = lastName = address = city = state = phone = zip = null;
    }

    public void sortRecords() throws SQLException {
        int choice = userInputs.sortMenu();
        String sortQuery = "";
        try (Connection con = DBConnection.getConnection()) {
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
    }

    public void searchInRecords() throws SQLException {
        int flag = 0;
        try (Connection con = DBConnection.getConnection()) {
            while (flag == 0) {
                int choice = userInputs.searchMenu();
                String searchQuery = "";
                switch (choice) {
                    case 1:
                        searchQuery = userInputs.getSearchQuery("city");
                        this.getDataOnQuery(con, searchQuery).forEach(System.out::println);
                        break;
                    case 2:
                        searchQuery = userInputs.getSearchQuery("state");
                        this.getDataOnQuery(con, searchQuery).forEach(System.out::println);
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

    private List<Person> getDataOnQuery(Connection con, String sortQuery) throws SQLException {
        List<Person> personData = new ArrayList<>();
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
        return personData;
    }
}
