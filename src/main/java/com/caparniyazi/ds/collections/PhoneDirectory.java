package com.caparniyazi.ds.collections;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple class to store a list of names and phone numbers.
 */
public class PhoneDirectory {
    // Data fields

    // The class representing each item in the phone directory.
    @Getter
    @Setter
    public static class DirectoryEntry {
        String name;
        String number;

        // Constructor
        public DirectoryEntry(String name, String number) {
            this.name = name;
            this.number = number;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof DirectoryEntry) {
                return name.equals(((DirectoryEntry) obj).name);
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return "Name: " + name + "; Number: " + number;
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

    /**
     * Method to add an entry to the Directory or change an existing entry.
     *
     * @param name      The name of the person being added or changed.
     * @param newNumber The new number to be assigned.
     * @return The old number, or if a new entry, null.
     */
    public String addOrChangeEntry(final String name, final String newNumber) {
        String oldNumber = null;
        int index = theDirectory.indexOf(new DirectoryEntry(name, ""));

        if (index != -1) {
            oldNumber = theDirectory.get(index).getNumber();
            theDirectory.get(index).setNumber(newNumber);
        } else {
            theDirectory.add(new DirectoryEntry(name, newNumber));
        }

        return oldNumber;
    }

    /**
     * Method to remove an entry.
     *
     * @param name The name of the person being removed.
     * @return The entry removed, or if there is no entry, null.
     */
    public DirectoryEntry removeEntry(final String name) {
        int index = theDirectory.indexOf(new DirectoryEntry(name, ""));

        if (index == -1) {
            return null;
        } else {
            return theDirectory.remove(index);
        }
    }
}
