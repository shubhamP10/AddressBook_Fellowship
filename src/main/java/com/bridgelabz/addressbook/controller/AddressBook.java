/*
 *@author: Shubham Pattar
 *main code for address book management
 *this file is the gateway for all functions
 */
package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.services.AddressBookService;
import com.bridgelabz.addressbook.utility.InputUtil;
import com.bridgelabz.addressbook.utility.JSONSampleOperations;

import java.util.LinkedList;

public class AddressBook {
    public static void main(String[] args) throws AddressBookException {
        final String JSON_SIMPLE_FILE_PATH = "src\\main\\resources\\JSonSimpleAddressBook.json";
        final String OPEN_CSV_FILE_PATH = "src\\main\\resources\\CSVAddressBook.csv";
        int flag = 0;
        String filePath = null;
        LinkedList<Person> personList;
        JSONSampleOperations jsonSampleOperations = new JSONSampleOperations();
        final AddressBookService addressBookService = new AddressBookService();

        System.out.println("Select Below Operations:\n1. JSON SAMPLE\n2.OPEN CSV \n");
        int option = InputUtil.getIntValue();
        switch (option) {
            case 1:
                filePath = JSON_SIMPLE_FILE_PATH;
                break;
            case 2:
                filePath = OPEN_CSV_FILE_PATH;
                break;
        }
        while (flag == 0) {
            System.out.println("--- Address Book Management ---\n");
            System.out.println("\t--MENU--");
            System.out.println("1: Add New Person      ");
            System.out.println("2: Display Records     ");
            System.out.println("3: Edit Person     ");
            System.out.println("4: Delete Person     ");
            System.out.println("5: Sort     ");
            System.out.println("6: Search     ");
            System.out.println("7: Exit		       \n");
            System.out.println("--- Enter Your Choice ---");
            int choice = InputUtil.getIntValue();
            switch (choice) {
                case 1:
                    if (option == 1) {
                        personList = jsonSampleOperations.getDataInList(filePath);
                        personList = addressBookService.addRecord(personList);
                        jsonSampleOperations.convertToFile(personList, filePath);
                    }
                    break;
                case 2:
                    if (option == 1) {
                        LinkedList<Person> person = jsonSampleOperations.getDataInList(filePath);
                        addressBookService.displayRecord(person);
                    }
                    break;
                case 3:
                    if (option == 1) {
                        LinkedList<Person> person = jsonSampleOperations.getDataInList(filePath);
                        LinkedList<Person> record = addressBookService.editRecord(person);
                        jsonSampleOperations.convertToFile(record, filePath);
                    }
                    break;
                case 4:
                    if (option == 1) {
                        personList = jsonSampleOperations.getDataInList(filePath);
                        personList = addressBookService.deleteRecord(personList);
                        jsonSampleOperations.convertToFile(personList, filePath);
                    }
                    break;
                case 5:
                    if (option == 1) {
                        LinkedList<Person> person = jsonSampleOperations.getDataInList(filePath);
                        addressBookService.sortRecords(person);
                    }
                    break;
                case 6:
                    if (option == 1) {
                        LinkedList<Person> person = jsonSampleOperations.getDataInList(filePath);
                        addressBookService.searchInRecords(person);
                    }
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
