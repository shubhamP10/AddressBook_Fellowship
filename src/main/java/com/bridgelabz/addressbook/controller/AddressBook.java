/*
 *@author: Shubham Pattar
 *main code for address book management
 *this file is the gateway for all functions
 */
package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.dbconnection.DBConnection;
import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.services.AddressBookService;
import com.bridgelabz.addressbook.services.DBOperations;
import com.bridgelabz.addressbook.utility.AddressBookUtility;
import com.bridgelabz.addressbook.utility.FileOperations;
import com.bridgelabz.addressbook.utility.InputUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static java.lang.System.exit;

public class AddressBook {

    public static void main(String[] args) throws AddressBookException, IOException, SQLException, ClassNotFoundException {
        final String JSON_SIMPLE_FILE_PATH = "src/main/resources/JSonSimpleAddressBook.json";
        final String OPEN_CSV_FILE_PATH = "src/main/resources/CSVAddressBook.csv";
        final String GSON_JSON_FILE_PATH = "src/main/resources/gsonJSONAddressBook.json";
        final int jsonSampleOperation = 1, openCSVOperation = 2, gsonOperation = 3;
        int operations = 0, flag = 0;
        String filePath = null;
        Connection con = null;
        List<Person> personList;
        FileOperations fileOperations = new FileOperations();
        DBOperations dbOperations = new DBOperations();
        AddressBookUtility addressBookUtility = new AddressBookUtility();
        final AddressBookService addressBookService = new AddressBookService();

        System.out.println("Select Below Operations:\n" +
                "1. Using JSON SAMPLE\n" +
                "2. Using OPEN CSV\n" +
                "3. Using GSON \n" +
                "4. Using MySQL DataBase");
        int option = InputUtil.getIntValue();
        switch (option) {
            case 1:
                filePath = JSON_SIMPLE_FILE_PATH;
                operations = jsonSampleOperation;
                break;
            case 2:
                filePath = OPEN_CSV_FILE_PATH;
                operations = openCSVOperation;
                break;
            case 3:
                filePath = GSON_JSON_FILE_PATH;
                operations = gsonOperation;
                break;
            case 4:
                con = DBConnection.getConnection();
                break;
            default:
                System.out.println("Invalid Choice!!!");
                exit(0);
        }
        while (flag == 0) {
            System.out.println("--- Address Book Management ---\n");
            System.out.println("\t--MENU--");
            System.out.println("1: Add New Person");
            System.out.println("2: Display Records");
            System.out.println("3: Edit Person");
            System.out.println("4: Delete Person");
            System.out.println("5: Sort");
            System.out.println("6: Search");
            System.out.println("7: Exit\n");
            System.out.println("--- Enter Your Choice ---");
            int choice = InputUtil.getIntValue();
            switch (choice) {
                case 1:
                    if (option == 4) {
                        dbOperations.addRecordToDB(con);
                        break;
                    }
                    personList = fileOperations.getDataInList(filePath, operations);
                    personList = addressBookUtility.addRecord(personList);
                    fileOperations.convertToFile(personList, filePath, operations);
                    break;
                case 2:
                    if (option == 4) {
                        List<Person> personDetails = dbOperations.getDataFromDB(con);
                        addressBookService.displayRecord(personDetails);
                        break;
                    }
                    List<Person> person = fileOperations.getDataInList(filePath, operations);
                    addressBookService.displayRecord(person);
                    break;
                case 3:
                    if (option == 4) {
                        dbOperations.editPersonDetails(con);
                        break;
                    }
                    personList = fileOperations.getDataInList(filePath, operations);
                    personList = addressBookService.editRecord(personList);
                    fileOperations.convertToFile(personList, filePath, operations);

                    break;
                case 4:
                    if (option == 4) {
                        dbOperations.deleteRecord(con);
                        break;
                    }
                    personList = fileOperations.getDataInList(filePath, operations);
                    personList = addressBookUtility.deleteRecord(personList);
                    fileOperations.convertToFile(personList, filePath, operations);
                    break;
                case 5:
                    if (option == 4) {
                        dbOperations.sortRecords(con);
                        break;
                    }
                    personList = fileOperations.getDataInList(filePath, operations);
                    addressBookUtility.sortRecords(personList);
                    break;
                case 6:
                    personList = fileOperations.getDataInList(filePath, operations);
                    addressBookUtility.searchInRecords(personList);
                    break;
                case 7:
                    flag = 1;
                    break;
                default:
                    System.out.println("Please Enter Valid Option!!!");
            }
        }
    }
}
