package com.caparniyazi.ds.lambda;

public class LambdaPracticeWithReturnValue {
    public static void main(String[] args) {
        LengthOfString len = String::length;
        int length = len.length("Istanbul");
        System.out.println(length);

        len = str -> {
            int l = str.length();
            System.out.println("The length of the string is: " + l);
            return str.length();
        };
        length = len.length("Istanbul");
        System.out.println(length);
    }
}
