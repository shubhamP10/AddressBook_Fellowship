package com.bridgelabz.addressbook.models;

import java.util.Comparator;

public class Person {
    /*Sort By First Name*/
    public static Comparator<Person> firstNameSorting = (p1, p2) -> {
        String firstName = p1.getFirstName();
        String firstName2 = p2.getFirstName();
        return firstName.compareToIgnoreCase(firstName2);
    };
    /*Sort By City*/
    public static Comparator<Person> citySorting = (p1, p2) -> {
        String city1 = p1.getCity();
        String city2 = p2.getCity();
        return city1.compareToIgnoreCase(city2);
    };
    /*Sort By State*/
    public static Comparator<Person> stateSorting = (p1, p2) -> {
        String state1 = p1.getState();
        String state2 = p2.getState();
        return state1.compareToIgnoreCase(state2);
    };
    /*Sort By Zip*/
    public static Comparator<Person> zipSorting = (p1, p2) -> {
        String zip1 = p1.getZip();
        String zip2 = p2.getZip();
        return zip1.compareToIgnoreCase(zip2);
    };
    private final String firstName;
    private final String lastName;
    private String address;
    private String city;
    private String state;
    private String phone;
    private String zip;

    /*Constructor to initialize fields*/
    public Person(String firstName, String lastName, String address, String city, String state, String zip, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.zip = zip;
    }

    /*Getter & Setter Methods*/
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "\nFull Name : " + firstName + " " + lastName +
                "\nAddress : " + address +
                "\nCity : " + city +
                "\nState : " + state +
                "\nPhone : " + phone +
                "\nZip : " + zip + "\n";
    }
}
