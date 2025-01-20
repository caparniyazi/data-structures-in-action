package com.caparniyazi.ds.tree;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;

/**
 * Class to represent and build a Huffman tree.
 * <p/>
 * In computer science and information theory, a Huffman code is a particular type of optimal prefix code
 * that is commonly used for lossless data compression.
 * The process of finding or using such a code is Huffman coding,
 * an algorithm developed by David A. Huffman while he was a Sc.D. student at MIT,
 * and published in the 1952 paper "A Method for the Construction of Minimum-Redundancy Codes".
 */
public class HuffmanTree<T> implements Serializable {
    // Data fields
    // A reference to the completed Huffman tree.
    private BinaryTree<HuffData<T>> huffTree;

    // Nested class
    public static class HuffData<T> implements Serializable {
        // Data fields
        // The weight or probability assigned to this HuffData.
        double weight;
        T symbol;    // The alphabet symbol if this is a leaf.

        public HuffData(double weight, T symbol) {
            this.weight = weight;
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                    .append("weight", weight)
                    .append("symbol", symbol)
                    .toString();
        }
    }

    // Methods

    /**
     * Builds the Huffman tree using the given alphabet and weights.
     *
     * @param symbols An array of HuffData objects.
     * @post huffTree contains a reference to the Huffman tree.
     */
    public void buildTree(HuffData<T>[] symbols) {
        Queue<BinaryTree<HuffData<T>>> theQueue = new MyPriorityQueue<>(symbols.length, (lt, rt) -> Double.compare(lt.getData().weight, rt.getData().weight));

        // Load the queue with the leaves, in other words place the set of trees into a priority queue.
        for (HuffData<T> nextSymbol : symbols) {
            var aBinaryTree = new BinaryTree<HuffData<T>>(nextSymbol, null, null);
            theQueue.offer(aBinaryTree);
        }

        // Build the tree.
        while (theQueue.size() > 1) {
            BinaryTree<HuffData<T>> left = theQueue.poll();
            BinaryTree<HuffData<T>> right = theQueue.poll();
            double wl = left.getData().weight;
            double wr = right.getData().weight;

            // Create a new tree with sum of weights of children.
            HuffData<T> sum = new HuffData<T>(wl + wr, null);
            var newTree = new BinaryTree<HuffData<T>>(sum, left, right);
            theQueue.offer(newTree);
        }
        ((MyPriorityQueue<?>) theQueue).printTree();

        // The queue should now contain only one item.
        huffTree = theQueue.poll();
    }

    /**
     * Outputs the resulting code.
     * It can be used to test the custom Huffman tree. It displays the tree,
     * so you can examine it and verify that the Huffman tree that was built
     * is correct based on the input data.
     * <p/>
     * To display the code for each alphabet symbol, we perform a preorder traversal
     * of the final tree.
     *
     * @param out  A PrintStream for the output.
     * @param code The code up to this node.
     * @param tree The current node in the tree.
     */
    private void printCode(PrintStream out, String code, BinaryTree<HuffData<T>> tree) {
        HuffData<T> theData = tree.getData();

        if (theData.symbol != null) {   // The current node is a leaf node, so the code is output.

            if (theData.symbol.equals(' ')) {
                out.println("space: " + code);
            } else {
                out.println(theData.symbol + ": " + code);
            }
        } else {    // Otherwise the left and right subtrees are traversed.
            /*
             * When we traverse the left subtree, we append a 0 to the code,
             * and when we traverse the right subtree, we append a 1 to the code.
             */
            printCode(out, code + "0", tree.getLeftSubtree());
            printCode(out, code + "1", tree.getRightSubtree());
        }
    }

    public String printTree() {
        PrintStream out = new PrintStream(System.out);
        String code = "";
        printCode(out, code, huffTree);
        return out.toString();
    }

    /**
     * A method for the Huffman tree class that encodes a String of letters
     * that is passed as its first argument. Assume that a second argument,
     * codes (type String[]) contains the code strings (binary digits)
     * for the symbols (space at position 0, 'a' at position 1, 'b' at position 2, and so on).
     *
     * @param str   String to be encoded.
     * @param codes Array of codes.
     * @return Encoded string.
     * @throws ArrayIndexOutOfBoundsException if string contains a char other than a letter or space.
     */
    public static String encode(String str, String[] codes) {
        StringBuilder result = new StringBuilder();
        for (char c : str.toCharArray()) {
            int index = 0;

            if (c != ' ') {
                index = Character.toUpperCase(c) - 'A';
            }
            result.append(codes[index]);
        }

        return result.toString();
    }

    public String[] getCodes() {
        SortedMap<Character, String> map = new TreeMap<>();
        String currentCode = "";
        buildCode(map, currentCode, huffTree);
        List<String> codesList = new ArrayList<>();
        map.forEach((k, v) -> codesList.add(v));
        return codesList.toArray(new String[0]);
    }

    private void buildCode(
            SortedMap<Character, String> map,
            String code,
            BinaryTree<HuffData<T>> tree) {
        HuffData<T> theData = tree.getData();

        if (theData.symbol != null) {
            map.put((Character) theData.symbol, code);
        } else {
            buildCode(map, code + "0", tree.getLeftSubtree());
            buildCode(map, code + "1", tree.getRightSubtree());
        }
    }

    public Map<Character, String> getCodeMap() {
        SortedMap<Character, String> map = new TreeMap<>();
        buildCode(map, "", huffTree);
        return map;
    }

    /**
     * Method to decode a message that is input as a string of digit characters '0' and '1'.
     *
     * @param codedMessage The input message as a string of zeros and ones.
     * @return The decoded message as a String.
     */
    public String decode(String codedMessage) {
        var result = new StringBuilder();
        var currTree = huffTree;

        for (int i = 0; i < codedMessage.length(); i++) {

            if (codedMessage.charAt(i) == '1') {
                currTree = currTree.getRightSubtree();
            } else {
                currTree = currTree.getLeftSubtree();
            }

            if (currTree.isLeaf()) {
                HuffData<T> theData = currTree.getData();
                result.append(theData.symbol);
                currTree = huffTree;    // Reset to Huffman tree.
            }
        }

        return result.toString();
    }
}
