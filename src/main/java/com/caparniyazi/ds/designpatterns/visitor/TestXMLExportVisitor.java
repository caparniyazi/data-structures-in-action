package com.caparniyazi.ds.designpatterns.visitor;

public class TestXMLExportVisitor {
    public static void main(String[] args) {
        Point p = new Point(1, 10, 55);
        Circle circle = new Circle(2, 23, 15, 10);
        Rectangle rectangle = new Rectangle(3, 10, 17, 20, 30);

        CompoundShape compoundShape = new CompoundShape(4);
        compoundShape.add(p);
        compoundShape.add(circle);
        compoundShape.add(rectangle);

        CompoundShape c = new CompoundShape(5);
        c.add(p);
        compoundShape.add(c);

        export(circle, compoundShape);
    }

    private static void export(Shape... shapes) {
        XMLExportVisitor visitor = new XMLExportVisitor();
        System.out.println(visitor.export(shapes));
    }
}
