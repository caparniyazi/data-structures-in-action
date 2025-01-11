package com.caparniyazi.ds.tree;

import static com.caparniyazi.ds.tree.HuffmanTree.HuffData;

public class TestHuffmanTree {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        String letters = "abcde";

        HuffmanTree.HuffData<Character> letterA = new HuffmanTree.HuffData<>(64.0, letters.charAt(0));
        HuffmanTree.HuffData<Character> letterB = new HuffmanTree.HuffData<>(13.0, letters.charAt(1));
        HuffmanTree.HuffData<Character> letterC = new HuffmanTree.HuffData<>(22.0, letters.charAt(2));
        HuffmanTree.HuffData<Character> letterD = new HuffmanTree.HuffData<>(32.0, letters.charAt(3));
        HuffmanTree.HuffData<Character> letterE = new HuffmanTree.HuffData<>(103.0, letters.charAt(4));
        HuffmanTree.HuffData<Character>[] data = new HuffData[]{letterA, letterB, letterC, letterD, letterE};
        HuffmanTree<Character> aTree = new HuffmanTree<>();

        aTree.buildTree(data);
        aTree.printTree();
    }
}
