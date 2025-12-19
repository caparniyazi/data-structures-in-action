package com.caparniyazi.ds.designpatterns.visitor;

import lombok.Getter;

@Getter
public abstract class Tree {
    // Data fields
    private int value;
    private Color color;
    private int depth;

    public Tree(final int value, final Color color, final int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public abstract void accept(TreeVis visitor);
}
