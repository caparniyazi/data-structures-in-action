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

        System.out.println("Enter an expression tree using prefix notation: ");
        BinaryTree<String> expTree = BinaryTree.readExpTree();
        System.out.println("Evaluated value is: " + new ExpressionTree(expTree).eval());
    }
}
