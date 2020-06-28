package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.exception.AddressBookException;

public interface IAddressBookService {
    void addRecord();

    void editRecord() throws AddressBookException;

    void displayRecord();

    void deleteRecord() throws AddressBookException;

    void sortRecords();

    void searchInRecords();

    boolean checkExists(String firstName);
}
