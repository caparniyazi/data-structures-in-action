package com.caparniyazi.ds.designpatterns.visitor;

public interface Visitor {
    String visitPoint(Point point);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);

    String visitCompoundGraphic(CompoundShape cg);
}
