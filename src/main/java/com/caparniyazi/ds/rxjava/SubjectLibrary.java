package com.caparniyazi.ds.rxjava;

public interface SubjectLibrary {
    void subscribeObserver(Observer observer);

    void unsubscribeObserver(Observer observer);

    void notifyObservers();
}
