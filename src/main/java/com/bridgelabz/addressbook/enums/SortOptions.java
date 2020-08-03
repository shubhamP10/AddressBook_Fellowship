package com.bridgelabz.addressbook.enums;

import com.bridgelabz.addressbook.models.Person;

import java.util.Comparator;

public enum SortOptions {
    NAME(Comparator.comparing(Person::getFirstName)),
    CITY(Comparator.comparing(Person::getCity)),
    STATE(Comparator.comparing(Person::getState)),
    ZIP(Comparator.comparing(Person::getZip));

    public final Comparator<? super Person> comparator;

    SortOptions(Comparator<? super Person> comparator) {
        this.comparator = comparator;
    }
}