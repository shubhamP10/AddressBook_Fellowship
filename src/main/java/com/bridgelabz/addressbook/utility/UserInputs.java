package com.bridgelabz.addressbook.utility;

public class UserInputs {
    public int editMenu() {
        System.out.println("What You Want to edit...\n"
                + "\t1: Address\n"
                + "\t2: city\n"
                + "\t3: State\n"
                + "\t4: Phone\n"
                + "\t5: Zip Code\n"
                + "\t6. Save And Exit\n");
        return InputUtil.getIntValue();
    }

    public String[] getName() {
        String[] name = new String[2];
        System.out.println("Enter First Name And Last Name To Edit Person Details");
        System.out.println("Enter First Name");
        name[0] = InputUtil.getStringValue();
        System.out.println("Enter Last Name");
        name[1] = InputUtil.getStringValue();
        return name;
    }

    public int sortMenu() {
        System.out.println("Sort By...\n"
                + "1: First Name\n"
                + "2: City\n"
                + "3: State\n"
                + "4: Zip Code\n"
                + "5: Back");
        return InputUtil.getIntValue();
    }

    public int searchMenu() {
        System.out.println("1. Search By City\n" +
                "2. Search By State\n" +
                "3. Back\n" +
                "Choose Your Option");
        return InputUtil.getIntValue();
    }

    public String getSearchQuery(String searchField) {
        System.out.println("Enter " + searchField + " Name To Search: ");
        String searchItem = InputUtil.getStringValue();
        return "SELECT * FROM person_details WHERE " + searchField + " = '" + searchItem + "'";
    }
}
