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
     *
     * @param person List
     */
    public static void searchBy(List<Person> person, String searchItem) {
        List<Person> matches = new ArrayList<>();
        int flag = 0;
        for (Person p : person) {
            if (p.getCity().equalsIgnoreCase(searchItem)) {
                flag = 1;
                matches.add(p);
            } else if (p.getState().equalsIgnoreCase(searchItem)) {
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
     *
     * @param person      List
     * @param sortOptions Enum
     */
    public static void sortData(List<Person> person, SortOptions sortOptions) {
        person.stream().sorted(sortOptions.comparator).forEach(System.out::println);
    }

    /**
     * Method To Add Person Records
     *
     * @param personList List
     * @return PersonList
     */
    public List<Person> addRecord(List<Person> personList, Person person) {
        personList.add(person);
        return personList;
    }

    /**
     * Method To Display Person Records
     *
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
     *
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
}
