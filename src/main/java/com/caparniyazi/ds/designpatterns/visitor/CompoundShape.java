package com.caparniyazi.ds.designpatterns.visitor;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CompoundShape implements Shape {
    // Data fields
    @Getter
    private int id;
    public List<Shape> children = new ArrayList<>();

    public CompoundShape(int id) {
        this.id = id;
    }

    @Override
    public void move(int x, int y) {
    }

    @Override
    public void draw() {
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitCompoundGraphic(this);
    }

    public void add(Shape shape) {
        children.add(shape);
    }
}
