package com.caparniyazi.ds.tree;

/**
 * The Basis of this list was a Turkish text with 1,947,735 characters (294,656 words),
 * 1,585,776 characters were used for the counting.
 * (frequency data via sttmedia.com)
 */
public class TestHuffmanTreeOfTurkishText {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        String letters = "aâbcçdefgğhıiîjklmnoöprsştuûüvyz";
        double[] frequencies = {
                11.56, 0.01, 2.83, 1.12, 1.35, 4.62, 9.39, 0.53, 1.50, 0.91,
                1.33, 3.84, 8.61, 0.01, 0.16, 4.70, 6.08,
                3.44, 6.80, 3.59, 0.71, 0, 75, 6, 81, 3, 27, 1.56, 3.30, 3.42, 1.77, 0.01, 1.73, 2.73, 1.43};
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
