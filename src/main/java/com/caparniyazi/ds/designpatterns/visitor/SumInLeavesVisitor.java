package com.caparniyazi.ds.designpatterns.visitor;

public class SumInLeavesVisitor extends TreeVis {
    // Data fields
    private int result = 0;

    @Override
    public int getResult() {
        return result;
    }

    @Override
    public void visitNode(TreeNode node) {

    }

    @Override
    public void visitLeaf(TreeLeaf leaf) {
        result += leaf.getValue();
    }
}
