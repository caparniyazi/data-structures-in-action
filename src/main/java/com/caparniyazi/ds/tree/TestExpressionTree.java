package com.caparniyazi.ds.tree;

public class TestExpressionTree {
    /**
     * (3 + 4) * 5 = 35
     *
     * @param args The args.
     */
    public static void main(String[] args) {
        ExpressionTree tree = new ExpressionTree();
        tree.buildSampleTree();
        System.out.println("Evaluated value is: " + tree.eval());
    }
}
