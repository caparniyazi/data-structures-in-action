package com.caparniyazi.ds.lambda;

import java.util.List;

import static com.caparniyazi.ds.lambda.ListFun.concat;

public class ListFunApp {
    public static void main(String[] args) {
        System.out.println("Our list:");
        ListFun<Integer> list = ListFun.list(3, 6, 9);
        list.forEach(System.out::println);
        System.out.println("----------------");

        System.out.println("Adding 44 to our list:");
        ListFun<Integer> newListFun = list.addElement(44);
        newListFun.forEach(System.out::println);
        System.out.println("----------------");

        System.out.println("Removing 3 from our list:");
        ListFun<Integer> removed = newListFun.removeElement(3);
        removed.forEach(System.out::println);
        System.out.println("----------------");

        System.out.println("Reversing the list:");
        ListFun<Integer> reversedList = removed.reverseList();
        reversedList.forEach(System.out::println);
        System.out.println("----------------");

        System.out.println("Concatenating list:");
        ListFun<Integer> list1 = ListFun.list(3, 6, 9);
        ListFun<Integer> list2 =  ListFun.list(34, 44, 23);
        concat(list1, list2).forEach(System.out::println);

        System.out.println("Constructing functional list out of collection:");
        List<Integer> result = List.of(1, 3, 5, 7);
        list.addAll(result).forEach(System.out::println);
    }
}
