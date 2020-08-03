/*
 *@author: Shubham Pattar
 *main code for address book management
 *this file is the gateway for all functions
 */
package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.services.AddressBookService;
import com.bridgelabz.addressbook.utility.AddressBookUtility;
import com.bridgelabz.addressbook.utility.FileOperations;
import com.bridgelabz.addressbook.utility.InputUtil;

import java.io.IOException;
import java.util.List;

import static java.lang.System.exit;

public class AddressBook {

    public static void main(String[] args) throws AddressBookException, IOException {
        final String JSON_SIMPLE_FILE_PATH = "src/main/resources/JSonSimpleAddressBook.json";
        final String OPEN_CSV_FILE_PATH = "src/main/resources/CSVAddressBook.csv";
        final String GSON_JSON_FILE_PATH = "src/main/resources/gsonJSONAddressBook.json";
        final int jsonSampleOperation = 1, openCSVOperation = 2, gsonOperation = 3;
        int operations = 0, flag = 0;
        String filePath = null;
        List<Person> personList;
        FileOperations fileOperations = new FileOperations();
        AddressBookUtility addressBookUtility = new AddressBookUtility();
        final AddressBookService addressBookService = new AddressBookService();

        System.out.println("Select Below Operations:\n1. Using JSON SAMPLE\n2. Using OPEN CSV\n3. Using GSON \n");
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
                    personList = fileOperations.getDataInList(filePath, operations);
                    personList = addressBookService.addRecord(personList);
                    fileOperations.convertToFile(personList, filePath, operations);
                    break;
                case 2:
                    List<Person> person = fileOperations.getDataInList(filePath, operations);
                    addressBookService.displayRecord(person);
                    break;
                case 3:
                    personList = fileOperations.getDataInList(filePath, operations);
                    personList = addressBookService.editRecord(personList);
                    fileOperations.convertToFile(personList, filePath, operations);

                    break;
                case 4:
                    personList = fileOperations.getDataInList(filePath, operations);
                    personList = addressBookUtility.deleteRecord(personList);
                    fileOperations.convertToFile(personList, filePath, operations);
                    break;
                case 5:
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
