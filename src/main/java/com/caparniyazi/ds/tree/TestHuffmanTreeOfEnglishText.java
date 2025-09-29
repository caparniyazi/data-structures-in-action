package com.caparniyazi.ds.tree;

/**
 * Table 6.8, based on data published in Donald Knuth, The Art of Computer Programming,
 * Vol. 3: Sorting and Searching (Addison-Wesley, 1973), p. 441, represents the relative
 * frequencies of the letters in English text.
 */
public class TestHuffmanTreeOfEnglishText {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        String letters = " abcdefghijklmnopqrstuvwxyz";
        double[] frequencies = {186, 64, 13, 22, 32, 103, 21, 15, 47, 57, 1, 5, 32, 20, 57, 63, 15, 1, 48, 51, 80, 23, 8, 18, 1, 16, 1};
        var data = new HuffmanTree.HuffData[letters.length()];

        for (int i = 0; i < letters.length(); i++) {
            HuffmanTree.HuffData<Character> letter = new HuffmanTree.HuffData<>(frequencies[i], letters.charAt(i));
            data[i] = letter;
        }
        HuffmanTree<Character> aTree = new HuffmanTree<>();
        aTree.buildTree(data);
        aTree.printTree();
    }
}
