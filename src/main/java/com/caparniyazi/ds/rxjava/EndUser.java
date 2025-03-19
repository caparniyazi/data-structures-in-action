package com.caparniyazi.ds.rxjava;

import lombok.Getter;
import lombok.Setter;

public class EndUser implements Observer {
    // Data fields
    @Getter
    @Setter
    String name;

    EndUser(String name, SubjectLibrary subject) {
        this.name = name;
        subject.subscribeObserver(this);
    }

    @Override
    public void update(String availability) {
        System.out.println("Hello " + name + "! We are glad to notify you that the book is now " + availability);
    }
}
