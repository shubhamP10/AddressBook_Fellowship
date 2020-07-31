package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.utility.InputUtil;
import com.bridgelabz.addressbook.utility.WriteToCSV;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*Helper Class to Perform AddressBook Operations
 * Add, Display, Edit, Delete, Search, Sort
 */
public class AddressBookService implements IAddressBookService {
    private final List<Person> personList = new ArrayList<>();

    JSONArray personArray = new JSONArray();
    /*Method Search the Person By City
     * @Param Person List
     */
    public static void searchByCity(List<Person> person) {
        String search;
        List<Person> matches = new ArrayList<>();
        System.out.println("Enter First Name to search : ");
        search = InputUtil.getStringValue();
        int flag = 0;
        for (Person p : person) {
            if (p.getCity().equalsIgnoreCase(search)) {
                flag = 1;
                matches.add(p);
            }
        }
        if (flag == 1) {
            System.out.println("...Match Found...");
            for (Person p : matches) {
                System.out.println(p);
            }
        } else {
            System.out.println("Match Not Found!!!");
        }
    }

    /*Method Search the Person By State
     * @Param Person List
     */
    public static void searchByState(List<Person> person) {
        String search;
        int flag = 0;
        List<Person> matches = new ArrayList<>();
        System.out.println("Enter First Name to search : ");
        search = InputUtil.getStringValue();
        for (Person p : person) {
            if (p.getState().equalsIgnoreCase(search)) {
                flag = 1;
                matches.add(p);
            }
        }
        if (flag == 1) {
            System.out.println("...Match Found...");
            for (Person p : matches) {
                System.out.println(p);
            }
        } else {
            System.out.println("Match Not Found!!!");
        }
    }

    /*Method Sort the Records By Name
     * @Param Person List
     */
    public static void sortByName(List<Person> person) {
        person.sort(Person.firstNameSorting);
        person.forEach(System.out::println);
    }

    /*Method Sort the Records By City
     * @Param Person List
     */
    public static void sortByCity(List<Person> person) {
        person.sort(Person.citySorting);
        person.forEach(System.out::println);
    }

    /*Method Sort the Records By State
     * @Param Person List
     */
    public static void sortByState(List<Person> person) {
        person.sort(Person.stateSorting);
        person.forEach(System.out::println);
    }

    /*Method Sort the Records By Zip
     * @Param Person List
     */
    public static void sortByZip(List<Person> person) {
        person.sort(Person.zipSorting);
        person.forEach(System.out::println);
    }

    /*Method Add Person Record*/
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
        Person person = new Person(firstName, lastName, address, city, state, phone, zip);
        personList.add(person);
        this.writeToJSONFile(person);
        WriteToCSV.writeAddCSV(personList);
    }

    private void writeToJSONFile(Person person) {
        JSONObject personDetails = new JSONObject();
        personDetails.put("first Name",person.getFirstName());
        personDetails.put("last Name",person.getLastName());
        personDetails.put("Phone",person.getPhone());
        personArray.add(personDetails);
        try (FileWriter file = new FileWriter("PersonDetails.json")) {
            file.write(personArray.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*Method to Display Person Records*/
    public void displayRecord() {
        if (personList.isEmpty()) {
            System.out.println("No Records To Display!!!");
        } else {
            personList.forEach(System.out::println);
        }
    }

    /*Method to Edit Person Record*/
    public void editRecord() throws AddressBookException {
        int id, i = 0;
        String address, city, state, phone, zip;
        try {
            if (personList.isEmpty()) {
                System.out.println("No Records To Edit!!!");
            } else {
                for (Person person : personList) {
                    System.out.println("ID: #" + personList.indexOf(person) + " : " + person);
                }
                System.out.print("\nEnter #ID to Edit Contact : ");
                id = InputUtil.getIntValue();
                System.out.println(personList.get(id));
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
                            personList.get(id).setAddress(address);
                            break;
                        case 2:
                            System.out.print("Enter new City : ");
                            city = InputUtil.getStringValue();
                            personList.get(id).setCity(city);
                            break;
                        case 3:
                            System.out.print("Enter new State : ");
                            state = InputUtil.getStringValue();
                            personList.get(id).setState(state);
                            break;
                        case 4:
                            System.out.print("Enter new Phone : ");
                            phone = InputUtil.getStringValue();
                            personList.get(id).setPhone(phone);
                            break;
                        case 5:
                            System.out.print("Enter new Zip Code : ");
                            zip = InputUtil.getStringValue();
                            personList.get(id).setZip(zip);
                            break;
                        case 6:
                            WriteToCSV.writeFromEdit(personList);
                            i = 1;
                            break;
                        default:
                            System.out.println("Please Enter Valid Option");
                    }
                    System.out.println(personList.get(id));
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new AddressBookException("Entered Wrong #ID", AddressBookException.exceptionType.ENTERED_WRONG_ID);
        }
    }

    /*Method to Delete Person Record*/
    public void deleteRecord() throws AddressBookException {
        try {
            int id;
            if (personList.isEmpty()) {
                System.out.println("No Records To Delete!!!");
            } else {
                personList.stream().map(p -> "ID: #" + personList.indexOf(p) + " : " + p).forEach(System.out::println);
                System.out.print("\nEnter #ID to delete Contact : ");
                id = InputUtil.getIntValue();
                personList.remove(id);
                WriteToCSV.writeFromDelete(personList);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new AddressBookException("Entered Wrong #ID", AddressBookException.exceptionType.ENTERED_WRONG_ID);
        }
    }

    /*Method for Sort Menu*/
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
                sortByName(personList);
                break;
            case 2:
                sortByCity(personList);
                break;
            case 3:
                sortByState(personList);
                break;
            case 4:
                sortByZip(personList);
                break;
            case 5:
                return;
            default:
                System.out.println("Please Enter Valid Option...");
        }
    }

    /*Method to Check Duplication of First Name
     * @Param FirstName
     */
    public boolean checkExists(String firstName) {
        int flag = personList.stream().anyMatch(p -> p.getFirstName().equalsIgnoreCase(firstName)) ? 1 : 0;
        return flag == 1;
    }

    /*Method for Search Menu*/
    public void searchInRecords() {
        int i = 0;
        while (i == 0) {
            System.out.println("1. Search By City\n" +
                    "2. Search By State\n" +
                    "3. Back\n" +
                    "Choose Your Option");
            int choice = InputUtil.getIntValue();
            switch (choice) {
                case 1:
                    searchByCity(personList);
                    break;
                case 2:
                    searchByState(personList);
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
