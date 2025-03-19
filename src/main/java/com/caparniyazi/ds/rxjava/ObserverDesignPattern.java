package com.caparniyazi.ds.rxjava;

public class ObserverDesignPattern {
    public static void main(String[] args) {
        Book book = new Book("GooseBumps", "Horror", "NA", 200, "SoldOut");
        EndUser user1 = new EndUser("Ali", book);
        EndUser user2 = new EndUser("Veli", book);
        System.out.println(book.getInStock());

        book.setInStock("In Stock");
    }
}
