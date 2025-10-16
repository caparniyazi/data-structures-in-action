package com.caparniyazi.ds;

import com.caparniyazi.ds.maps.MapContactList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TestMapContactList {
    // Data fields
    private final MapContactList contactList = new MapContactList();

    // The attribute @BeforeEach tells JUnit to execute this method before each test.
    @BeforeEach
    public void initialize() {
        List<String> nums = new ArrayList<>(List.of("123", "345"));
        contactList.addOrChangeEntry("Hasan", nums);
        nums = new ArrayList<>(List.of("135", "357"));
        contactList.addOrChangeEntry("Mustafa", nums);
    }

    @Test
    public void lookupAnItemNotInList() {
        assertNull(contactList.lookupEntry("Ahmet"));
    }

    @Test
    public void lookupItemInList() {
        assertEquals(List.of("135", "357"), contactList.lookupEntry("Mustafa"));
    }
}
