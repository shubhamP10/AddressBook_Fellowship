package com.bridgelabz.addressbook.utility;

public class ValidateInputs {

    public static String validateName(String name) {
        System.out.print("Enter " + name + ": ");
        String param = InputUtil.getStringValue();
        if (param.matches("^[A-Z][a-z]+$")) {
            return param;
        } else {
            System.out.println("Invalid " + name + " enter again");
            return validateName(name);
        }
    }

    public static String validatePhone() {
        System.out.print("Enter phone number: ");
        String phoneNumber = InputUtil.getStringValue();
        if (phoneNumber.matches("^[1-9]+[0-9]+[ ]?+[1-9][0-9]{9}$"))
            return phoneNumber;
        else {
            System.out.println("Invalid phone number enter again");
            return validatePhone();
        }
    }

    public static String validateAddress() {
        System.out.print("Enter Address: ");
        String address = InputUtil.getStringValue();
        if (address.matches("^[A-Z][a-z]{2}+([ ]?+[a-zA-Z]+)*$"))
            return address;
        else {
            System.out.println("Invalid address enter again");
            return validateAddress();
        }
    }

    public static String validateZip() {
        System.out.print("Enter Zip: ");
        String zip = InputUtil.getStringValue();
        if (zip.matches("^[1-9][0-9]{6}$"))
            return zip;
        else {
            System.out.println("Invalid zip enter again");
            return validateZip();
        }
    }
}
