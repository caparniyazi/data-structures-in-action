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
        letters = "*+-/%";

        HuffmanTree.HuffData<Character> letterMu = new HuffmanTree.HuffData<>(50.0, letters.charAt(0));
        HuffmanTree.HuffData<Character> letterPl = new HuffmanTree.HuffData<>(30.0, letters.charAt(1));
        HuffmanTree.HuffData<Character> letterMi = new HuffmanTree.HuffData<>(25.0, letters.charAt(2));
        HuffmanTree.HuffData<Character> letterDi = new HuffmanTree.HuffData<>(10.0, letters.charAt(3));
        HuffmanTree.HuffData<Character> letterMo = new HuffmanTree.HuffData<>(5.0, letters.charAt(4));
        data = new HuffData[]{letterMu, letterPl, letterMi, letterDi, letterMo};
        aTree = new HuffmanTree<>();

        aTree.buildTree(data);
        aTree.printTree();
    }
}
