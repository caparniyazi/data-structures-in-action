package com.caparniyazi.ds.designpatterns.visitor;

public class ProductOfRedNodesVisitor extends TreeVis {
    // Data fields
    private long result = 1;
    private final int M = 1000000007;

    @Override
    public int getResult() {
        return (int) result;
    }

    @Override
    public void visitNode(TreeNode node) {
        if (node.getColor() == Color.RED) {
            result = (result * node.getValue()) % M;
        }
    }

    @Override
    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == Color.RED) {
            result = (result * leaf.getValue()) % M;
        }
    }
}
