/*
 *@author: Shubham Pattar
 *main code for address book management
 *this file is the gateway for all functions
 */
package com.bridgelabz.fellowship.main;
import com.bridgelabz.fellowship.utils.*;
import  com.bridgelabz.fellowship.models.Helper;

public class AddressBook {
    public static void main(String[] args) {
        final Helper help = new Helper();
        help.addRecord();
    }
}
