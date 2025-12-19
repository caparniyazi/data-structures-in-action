package com.caparniyazi.ds.designpatterns.visitor;

public class FancyVisitor extends TreeVis {
    // Data fields
    private int nonLeafEvenDepthSum = 0;
    private int greenLeavesSum = 0;

    @Override
    public int getResult() {
        return Math.abs(nonLeafEvenDepthSum - greenLeavesSum);
    }

    @Override
    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 == 0) {
            nonLeafEvenDepthSum += node.getValue();
        }
    }

    @Override
    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor().equals(Color.GREEN)) {
            greenLeavesSum += leaf.getValue();
        }
    }
}
