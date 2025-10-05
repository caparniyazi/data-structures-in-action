package com.caparniyazi.ds.tree;

/**
 * The Expression Tree class implementation with a data field that is a BinaryTree.
 * The nodes store operators and leaves store the operands (numbers or variables).
 */
public class ExpressionTree {
    // Data fields
    private final BinaryTree<String> tree;

    public ExpressionTree() {
        tree = new BinaryTree<>();
    }

    public ExpressionTree(BinaryTree<String> tree) {
        this.tree = tree;
    }

    /**
     * Starter method to evaluate the expression.
     *
     * @return the evaluated value of expression stored in the tree.
     */
    public int eval() {
        return eval(tree.getRoot());
    }

    /**
     * @param node The root node
     * @return The evaluated value of expression stored in the tree.
     */
    private int eval(BinaryTree.Node<String> node) {
        if (node == null) {
            return 0;
        }

        // Check if the node is a leaf node.
        if (node.left == null && node.right == null) {
            return Integer.parseInt(node.data);
        }
        int leftVal = eval(node.left);
        int rightVal = eval(node.right);

        return switch (node.data) {
            case "+" -> leftVal + rightVal;
            case "-" -> leftVal - rightVal;
            case "*" -> leftVal * rightVal;
            case "/" -> leftVal / rightVal;
            default -> 0;
        };
    }

    // (3 + 4) * 5
    public void buildSampleTree() {
        BinaryTree.Node<String> plus = new BinaryTree.Node<>("+");
        BinaryTree.Node<String> mul = new BinaryTree.Node<>("*");
        BinaryTree.Node<String> three = new BinaryTree.Node<>("3");
        BinaryTree.Node<String> four = new BinaryTree.Node<>("4");
        BinaryTree.Node<String> five = new BinaryTree.Node<>("5");

        plus.left = three;
        plus.right = four;
        mul.left = plus;
        mul.right = five;

        tree.setRoot(mul);
        System.out.println(tree.inOrderToString());
    }
}
