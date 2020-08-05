package com.bridgelabz.addressbook;

import com.bridgelabz.addressbook.models.Person;
import com.bridgelabz.addressbook.services.AddressBookService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AddressBookServiceTest {

    AddressBookService addressBookService = new AddressBookService();
    List<Person> personList = new ArrayList<>();

    @Test
    public void givenPersonDetails_WhenProper_ShouldAddPersonToAddressBook() {
        addressBookService.addRecord(personList,
                new Person("abc","xyz","mno","pqr","efg","598754","5987456541"));
    }
}
