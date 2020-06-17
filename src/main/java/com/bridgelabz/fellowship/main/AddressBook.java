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
        int choice,i=0;
        final Helper help = new Helper();
        while(i==0)
        {
            System.out.println("--- Address Book Management ---\n");
            System.out.println("\t--MENU--");
            System.out.println("1: Add New Person      ");
            System.out.println("2: Display Records     ");
            System.out.println("3: Edit Person     ");
            System.out.println("4: Exit		       \n");
//			System.out.println(" -----------------------");
            System.out.println("--- Enter Your Choice ---");
            choice = InputUtil.getIntValue();
            switch(choice)
            {
                case 1 :
                    help.addRecord();
                    break;
                case 2 :
                    help.displayRecord();
                    break;
                case 3 :
                    help.editRecord();
                    break;
                case 4 :
                    i=1;
                    break;
                default :
                    System.out.println("Please Enter Valid Option!!!");
            }
        }
    }
}
