package com.caparniyazi.ds.designpatterns.visitor;

import lombok.Getter;

@Getter
public class Point implements Shape {
    // Data fields
    private int id;
    private int x;
    private int y;

    public Point() {

    }

    public Point(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public void move(int x, int y) {

    }

    @Override
    public void draw() {

    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitPoint(this);
    }
}
