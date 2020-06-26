package com.bridgelabz.addressbook.models;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.services.SearchSortService;
import com.bridgelabz.addressbook.utility.InputUtil;

import java.util.ArrayList;
import java.util.List;

public class Helper {
    List<Person> PERSON = new ArrayList<>();

    public void addRecord() {
        int i = 0;
        String firstName = null;
        final String lastName, address, city, state, phone, zip;
        while (i == 0) {
            System.out.print("Enter First Name : ");
            firstName = InputUtil.getStringValue();
            if (checkExists(firstName)) {
                System.out.println("Person Name Already Exists!!\nPlease enter different name...");
            } else {
                i = 1;
            }
        }
        System.out.print("Enter Last Name : ");
        lastName = InputUtil.getStringValue();
        System.out.print("Enter Phone Number : ");
        phone = InputUtil.getStringValue();
        System.out.print("Enter Address : ");
        address = InputUtil.getStringValue();
        System.out.print("Enter city : ");
        city = InputUtil.getStringValue();
        System.out.print("Enter zip : ");
        zip = InputUtil.getStringValue();
        System.out.print("Enter state : ");
        state = InputUtil.getStringValue();

        PERSON.add(new Person(firstName, lastName, address, city, state, phone, zip));
    }

    public void displayRecord() {
        if (PERSON.isEmpty()) {
            System.out.println("No Records To Display!!!");
        } else {
            for (Person person : PERSON) {
                System.out.println(person);
            }
        }

    }

    public void editRecord() throws AddressBookException {
        int id, i = 0;
        String address, city, state, phone, zip;
        try {
            if (PERSON.isEmpty()) {
                System.out.println("No Records To Edit!!!");
            } else {
                for (Person person : PERSON) {
                    System.out.println("ID: #" + PERSON.indexOf(person) + " : " + person);
                }
                System.out.print("\nEnter #ID to Edit Contact : ");
                id = InputUtil.getIntValue();
                System.out.println(PERSON.get(id));
                while (i == 0) {
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
                            PERSON.get(id).setAddress(address);
                            break;
                        case 2:
                            System.out.print("Enter new City : ");
                            city = InputUtil.getStringValue();
                            PERSON.get(id).setCity(city);
                            break;
                        case 3:
                            System.out.print("Enter new State : ");
                            state = InputUtil.getStringValue();
                            PERSON.get(id).setState(state);
                            break;
                        case 4:
                            System.out.print("Enter new Phone : ");
                            phone = InputUtil.getStringValue();
                            PERSON.get(id).setPhone(phone);
                            break;
                        case 5:
                            System.out.print("Enter new Zip Code : ");
                            zip = InputUtil.getStringValue();
                            PERSON.get(id).setZip(zip);
                            break;
                        case 6:
                            i = 1;
                            break;
                        default:
                            System.out.println("Please Enter Valid Option");
                    }
                    System.out.println(PERSON.get(id));
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new AddressBookException("Entered Wrong #ID", AddressBookException.exceptionType.ENTERED_WRONG_ID);
        }
    }

    public void deleteRecord() throws AddressBookException {
        try {
            int id;
            if (PERSON.isEmpty()) {
                System.out.println("No Records To Delete!!!");
            } else {
                for (Person p : PERSON) {
                    System.out.println("ID: #" + PERSON.indexOf(p) + " : " + p);
                }
                System.out.print("\nEnter #ID to delete Contact : ");
                id = InputUtil.getIntValue();
                PERSON.remove(id);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new AddressBookException("Entered Wrong #ID", AddressBookException.exceptionType.ENTERED_WRONG_ID);
        }
    }

    public void sortRecords() {
        System.out.println("Sort By...\n"
                + "1: First Name\n"
                + "2: City\n"
                + "3: State\n"
                + "4: Zip Code\n"
                + "5: Back");
        int choice = InputUtil.getIntValue();
        switch (choice) {
            case 1:
                SearchSortService.sortByName(PERSON);
                break;
            case 2:
                SearchSortService.sortByCity(PERSON);
                break;
            case 3:
                SearchSortService.sortByState(PERSON);
                break;
            case 4:
                SearchSortService.sortByZip(PERSON);
                break;
            case 5:
                return;
            default:
                System.out.println("Please Enter Valid Option...");
        }
    }


    public boolean checkExists(String firstName) {
        int flag = 0;
        for (Person p : PERSON) {
            if (p.getFirstName().equalsIgnoreCase(firstName)) {
                flag = 1;
                break;
            }
        }
        return flag == 1;
    }

    public void SearchInRecords() {
        int i = 0;
        while (i == 0) {
            System.out.println("1. Search By City\n" +
                    "2. Search By State\n" +
                    "3. Back\n" +
                    "Choose Your Option");
            int choice = InputUtil.getIntValue();
            switch (choice) {
                case 1:
                    SearchSortService.searchByCity(PERSON);
                    break;
                case 2:
                    SearchSortService.searchByState(PERSON);
                    break;
                case 3:
                    i = 1;
                    break;
                default:
                    System.out.println("Please Enter Correct Option...");
            }
        }
    }
}
