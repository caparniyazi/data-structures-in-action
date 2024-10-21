package com.caparniyazi.ds.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple class to store a list of names and phone numbers.
 */
public class PhoneDirectory {
    // Data fields

    // The class representing each item in the phone directory.
    public static class DirectoryEntry {
        String name;
        String number;

        // Constructor
        public DirectoryEntry(String name, String number) {
            this.name = name;
            this.number = number;
        }
    }

    // The object to store a phone directory.
    private final List<DirectoryEntry> theDirectory = new ArrayList<>();

    /**
     * Method to add an entry to the phone directory.
     *
     * @param name   The person's name
     * @param number The phone number
     */
    public void addEntry(final String name, final String number) {
        theDirectory.add(new DirectoryEntry(name, number));
    }

    /**
     * Method to retrieve the entry for a particular name.
     *
     * @param name The person's name
     * @return the entry for a particular name.
     */
    public DirectoryEntry getEntry(final String name) {
        int index = theDirectory.indexOf(new DirectoryEntry(name, ""));

        if (index == -1) {
            return null;
        } else {
            return theDirectory.get(index);
        }
    }
}
