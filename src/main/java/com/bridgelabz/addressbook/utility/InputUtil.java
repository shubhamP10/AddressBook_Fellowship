package com.bridgelabz.addressbook.utility;

import java.util.Scanner;

public class InputUtil {
    private final static Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public static int getIntValue() {
        return sc.nextInt();
    }

    public static String getStringValue() {
        return sc.next();
    }
}