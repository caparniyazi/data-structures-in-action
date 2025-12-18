package com.caparniyazi.ds.designpatterns.visitor;

public interface Shape {
    void move(int x, int y);
    void draw();
    String accept(Visitor visitor);
}
