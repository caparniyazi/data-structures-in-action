package com.caparniyazi.ds.maps;

public class TestHashCode {
    public static void main(String[] args) {
        String myStr = "Hello world from Istanbul";
        System.out.println(myStr.hashCode());
        System.out.println(hashCode(myStr));
    }

    /**
     * Method that returns the hashcode of the given string using the following
     * Java API algorithm:
     * * s[0]*31^(n-1) + s[1]*31^(n-2) + ... + s[n-1]
     *
     * @param str The input string.
     * @return The hashcode of the given string.
     */
    public static int hashCode(String str) {
        char[] c = str.toCharArray();
        int h = 0;

        for (char value : c) {
            h = 31 * h + value;
        }

        return h;
    }
}
