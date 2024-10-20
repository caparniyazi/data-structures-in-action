package com.caparniyazi.ds.collections;

import java.util.ArrayList;

public class CollectionUtils {
    /**
     * Method to replace each occurrence of oldItem in aList with newItem.
     *
     * @param aList   The Arraylist in which items are to be replaced.
     * @param oldItem The item to be replaced.
     * @param newItem The item to replace the old item.
     * @post All occurrences of old item have been replaced with new item.
     */
    public static void replace(ArrayList<String> aList, String oldItem, String newItem) {
        int index = aList.indexOf(oldItem);

        while (index != -1) {
            aList.set(index, newItem);
            index = aList.indexOf(oldItem);
        }
    }
}
