package com.bridgelabz.fellowship.services;
import com.bridgelabz.fellowship.models.Person;

import java.util.Collections;
import java.util.List;

public class Sort {
    public static void sortByName(List<Person> person)
    {
        Collections.sort(person, Person.firstNameSorting);
        for(Person p: person)
        {
            System.out.println(p);
        }
    }
}
