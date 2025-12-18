package com.caparniyazi.ds.designpatterns.visitor;

/**
 * Use the Visitor Pattern when you want to add capabilities to a composite of objects
 * and encapsulation is not important.
 * The purpose of a Visitor pattern is to define a new operation without introducing the modifications to an existing object structure.
 * Consequently, we’ll make good use of the Open/Closed principle as we won’t modify the code,
 * but we’ll still be able to extend the functionality by providing a new Visitor implementation.
 * Benefits:
 * <pre>
 * It allows you to add operations to a Composite structure without changing the structure itself.
 *
 *  􀂃 Adding new operations is relatively easy.
 *  􀂃 The code for operations performed by the Visitor is centralized.
 *
 * </pre>
 * Drawbacks:
 * <pre>
 *  􀂃 The Composite classes’ encapsulation is broken when the Visitor is used.
 *  􀂃 Because the traversal function is involved, changes to the Composite structure are more
 * difficult.
 * </pre>
 */
public class XMLExportVisitor implements Visitor {
    public String export(Shape... args) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

        for (Shape shape : args) {
            sb.append(shape.accept(this)).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String visitPoint(Point p) {
        return "<point>" + "\n" +
                "    <id>" + p.getId() + "</id>" + "\n" +
                "    <x>" + p.getX() + "</x>" + "\n" +
                "    <y>" + p.getY() + "</y>" + "\n" +
                "</point>";
    }

    @Override
    public String visitCircle(Circle c) {
        return "<circle>" + "\n" +
                "    <id>" + c.getId() + "</id>" + "\n" +
                "    <x>" + c.getX() + "</x>" + "\n" +
                "    <y>" + c.getY() + "</y>" + "\n" +
                "    <radius>" + c.getRadius() + "</radius>" + "\n" +
                "</circle>";
    }

    @Override
    public String visitRectangle(Rectangle r) {
        return "<rectangle>" + "\n" +
                "    <id>" + r.getId() + "</id>" + "\n" +
                "    <x>" + r.getX() + "</x>" + "\n" +
                "    <y>" + r.getY() + "</y>" + "\n" +
                "    <width>" + r.getWidth() + "</width>" + "\n" +
                "    <height>" + r.getHeight() + "</height>" + "\n" +
                "</rectangle>";
    }

    @Override
    public String visitCompoundGraphic(CompoundShape cg) {
        return "<compound_graphic>" + "\n" +
                "   <id>" + cg.getId() + "</id>" + "\n" +
                _visitCompoundGraphic(cg) +
                "</compound_graphic>";
    }

    private String _visitCompoundGraphic(CompoundShape cg) {
        StringBuilder sb = new StringBuilder();

        for (Shape shape : cg.children) {
            String obj = shape.accept(this);

            // Proper indentation for sub-objects.
            obj = "    " + obj.replace("\n", "\n    ") + "\n";
            sb.append(obj);
        }
        return sb.toString();
    }
}
