package com.bridgelabz.addressbook.models;

public class Person {
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    /*Getter & Setter Methods*/
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
