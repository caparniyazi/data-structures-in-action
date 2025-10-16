package com.caparniyazi.ds.maps;

import java.util.List;

public interface ContactListInterface {
    /**
     * Changes the numbers associated with the given name or adds a new entry with this name
     * and list of numbers. Returns the old list of numbers or null if this is a new entry.
     *
     * @param name    Contact name
     * @param numbers Contact's phone numbers
     * @return The old list of numbers or null if this is a new entry
     */
    List<String> addOrChangeEntry(String name, List<String> numbers);

    /**
     * Searches the contact list for the given name and returns its list of numbers or null
     * if the name is not found.
     *
     * @param name Contact's name
     * @return The list of numbers for the given name or null if the name is not found.
     */
    List<String> lookupEntry(String name);

    /**
     * Removes the entry with the specified name from the contact list and
     * returns its list of numbers or null if the name is not in the contact list.
     *
     * @param name Contact's name
     * @return its list of numbers or null if the name is not in the contact list.
     */
    List<String> removeEntry(String name);

    /**
     * Displays the contact list in order by name.
     */
    void display();
}
