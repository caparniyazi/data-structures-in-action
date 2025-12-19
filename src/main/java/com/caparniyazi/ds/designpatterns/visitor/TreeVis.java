package com.caparniyazi.ds.designpatterns.visitor;

public abstract class TreeVis {
    public abstract int getResult();

    public abstract void visitNode(TreeNode visitor);

    public abstract void visitLeaf(TreeLeaf leaf);
}
