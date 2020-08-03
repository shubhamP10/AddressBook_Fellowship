package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.enums.SortOptions;
import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.utility.InputUtil;

import java.util.ArrayList;
import java.util.List;

public class AddressBookService implements IAddressBookService {

    /**
     * Method To Search Person By City
     * @param person List
     */
    public static void searchBy(List<Person> person, String searchItem) {
        List<Person> matches = new ArrayList<>();
        int flag = 0;
        for (Person p : person) {
            if (p.getCity().equalsIgnoreCase(searchItem)) {
                flag = 1;
                matches.add(p);
            }
            else if (p.getState().equalsIgnoreCase(searchItem)){
                flag = 1;
                matches.add(p);
            }
        }
        if (flag == 1) {
            System.out.println("...Match Found...");
            matches.forEach(System.out::println);
        } else {
            System.out.println("Match Not Found!!!");
        }
    }

    /**
     * Method To Sort Data
     * @param person List
     * @param sortOptions Enum
     */
    public static void sortData(List<Person> person, SortOptions sortOptions) {
       person.stream().sorted(sortOptions.comparator).forEach(System.out::println);
    }

    /**
     * Method To Add Person Records
     * @param personList List
     * @return PersonList
     */
    public List<Person> addRecord(List<Person> personList) {
        int flag = 0;
        String firstName = null;
        final String lastName, address, city, state, phone, zip;
        while (flag == 0) {
            System.out.print("Enter First Name : ");
            firstName = InputUtil.getStringValue();
            if (checkExists(firstName, personList)) {
                System.out.println("Person Name Already Exists!!\nPlease enter different name...");
            } else {
                flag = 1;
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
        Person person = new Person(firstName, lastName, address, city, state, zip, phone);
        personList.add(person);
        return personList;
    }

    /**
     * Method To Display Person Records
     * @param person List
     */
    public void displayRecord(List<Person> person) {
        if (person.isEmpty()) {
            System.out.println("No Records To Display!!!");
        } else {
            person.forEach(System.out::println);
        }
    }

    @Override
    public List<Person> deleteRecord(List<Person> personList, int id) {
        personList.remove(id);
        return personList;
    }

    /**
     * Method To Edit Person Records
     * @param person List
     * @return PersonList
     * @throws AddressBookException
     */
    public List<Person> editRecord(List<Person> person) throws AddressBookException {
        int id, flag = 0;
        String address, city, state, phone, zip;
        try {
            if (person.isEmpty()) {
                System.out.println("No Records To Edit!!!");
            } else {
                for (Person person1 : person) {
                    System.out.println("ID: #" + person.indexOf(person1) + " : " + person1);
                }
                System.out.print("\nEnter #ID to Edit Contact : ");
                id = InputUtil.getIntValue();
                System.out.println(person.get(id));
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
                            person.get(id).setAddress(address);
                            break;
                        case 2:
                            System.out.print("Enter new City : ");
                            city = InputUtil.getStringValue();
                            person.get(id).setCity(city);
                            break;
                        case 3:
                            System.out.print("Enter new State : ");
                            state = InputUtil.getStringValue();
                            person.get(id).setState(state);
                            break;
                        case 4:
                            System.out.print("Enter new Phone : ");
                            phone = InputUtil.getStringValue();
                            person.get(id).setPhone(phone);
                            break;
                        case 5:
                            System.out.print("Enter new Zip Code : ");
                            zip = InputUtil.getStringValue();
                            person.get(id).setZip(zip);
                            break;
                        case 6:
                            flag = 1;
                            break;
                        default:
                            System.out.println("Please Enter Valid Option");
                    }
                    System.out.println(person.get(id));
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new AddressBookException("Entered Wrong #ID",
                    AddressBookException.exceptionType.ENTERED_WRONG_ID);
        }
        return person;
    }

    /**
     * Method to Check Duplication of First Name
     * @Param FirstName
     */
    public boolean checkExists(String firstName, List<Person> person) {
        int flag = person.stream()
                .anyMatch(p -> p.getFirstName().equalsIgnoreCase(firstName)) ? 1 : 0;
        return flag == 1;
    }
}
