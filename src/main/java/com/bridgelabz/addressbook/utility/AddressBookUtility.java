package com.bridgelabz.addressbook.utility;

import com.bridgelabz.addressbook.enums.SortOptions;
import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.services.AddressBookService;

import java.util.ArrayList;
import java.util.List;

import static com.bridgelabz.addressbook.services.AddressBookService.searchBy;
import static com.bridgelabz.addressbook.services.AddressBookService.sortData;

public class AddressBookUtility {
    AddressBookService addressBookService = new AddressBookService();

    /**
     * Method To Delete Person Details
     * @param personList
     * @return PersonList
     * @throws AddressBookException
     */
    public List<Person> deleteRecord(List<Person> personList) throws AddressBookException {
        List<Person> deleteRecord = new ArrayList<>();
        try {
            int id;
            if (personList.isEmpty()) {
                System.out.println("No Records To Delete!!!");
            } else {
                personList.stream().map(p -> "ID: #" + personList.indexOf(p) + " : " + p).forEach(System.out::println);
                System.out.print("\nEnter #ID to delete Contact : ");
                id = InputUtil.getIntValue();
                 deleteRecord = addressBookService.deleteRecord(personList, id);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new AddressBookException("Entered Wrong #ID",
                    AddressBookException.exceptionType.ENTERED_WRONG_ID);
        }
        return deleteRecord;
    }

    /**
     * Method To Search In Person Records
     * @param person
     */
    public void searchInRecords(List<Person> person) {
        int flag = 0;
        while (flag == 0) {
            System.out.println("1. Search By City\n" +
                    "2. Search By State\n" +
                    "3. Back\n" +
                    "Choose Your Option");
            int choice = InputUtil.getIntValue();
            switch (choice) {
                case 1:
                    System.out.println("Enter City Name To Search: ");
                    String city = InputUtil.getStringValue();
                    searchBy(person, city);
                    break;
                case 2:
                    System.out.println("Enter State Name To Search: ");
                    String state = InputUtil.getStringValue();
                    searchBy(person, state);
                    break;
                case 3:
                    flag = 1;
                    break;
                default:
                    System.out.println("Please Enter Correct Option...");
            }
        }
    }

    /**
     * Method To Sort Records By Given Options
     * @param personList
     */
    public void sortRecords(List<Person> personList) {
        System.out.println("Sort By...\n"
                + "1: First Name\n"
                + "2: City\n"
                + "3: State\n"
                + "4: Zip Code\n"
                + "5: Back");
        int choice = InputUtil.getIntValue();
        switch (choice) {
            case 1:
                sortData(personList, SortOptions.NAME);
                break;
            case 2:
                sortData(personList, SortOptions.CITY);
                break;
            case 3:
                sortData(personList, SortOptions.STATE);
                break;
            case 4:
                sortData(personList, SortOptions.ZIP);
                break;
            case 5:
                return;
            default:
                System.out.println("Please Enter Valid Option...");
        }
    }
}
