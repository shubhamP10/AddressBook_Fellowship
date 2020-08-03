package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.exception.AddressBookException;
import com.bridgelabz.addressbook.models.Person;

import java.util.List;

public interface IAddressBookService {

    List<Person> addRecord(List<Person> personList);

    List<Person> editRecord(List<Person> person) throws AddressBookException;

    void displayRecord(List<Person> person);

    List<Person> deleteRecord(List<Person> personList, int id) throws AddressBookException;

    boolean checkExists(String firstName, List<Person> person);
}
