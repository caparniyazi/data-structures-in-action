package com.caparniyazi.ds.designpatterns.visitor;

public class TreeLeaf extends Tree {
    public TreeLeaf(int value, Color color, int depth) {
        super(value, color, depth);
    }

    @Override
    public void accept(TreeVis visitor) {
        visitor.visitLeaf(this);
    }
}
