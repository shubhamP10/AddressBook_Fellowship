package com.bridgelabz.fellowship.models;
import com.bridgelabz.fellowship.utils.InputUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Helper {
    List<Person> p = new ArrayList<Person>();
    //	ADD METHOD
    public void addRecord()
    {
        final String fname, lname, address, city, state, phone,zip;

        System.out.print("Enter First Name : ");
        fname = InputUtil.getStringValue();
        System.out.print("Enter Last Name : ");
        lname = InputUtil.getStringValue();
        System.out.print("Enter Phone Number : ");
        phone = InputUtil.getStringValue();
        System.out.print("Enter Address : ");
        address = InputUtil.getStringValue();
        System.out.print("Enter city : ");
        city = InputUtil.getStringValue();
        System.out.print("Enter zip : ");
        zip = InputUtil.getStringValue();
        System.out.print("Enter state : ");
        state = InputUtil.getStringValue();

        p.add(new Person(fname,lname,address,city,state,phone,zip));
    } // END of addRecord()

    //	DISPLAY METHOD
    public void displayRecord()
    {
        for(Person p1: p)
        {
            System.out.println(p1);
        }

    } // END OF displayRecord
}
