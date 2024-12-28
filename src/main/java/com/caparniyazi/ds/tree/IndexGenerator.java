package com.caparniyazi.ds.tree;

import java.util.Scanner;
import java.util.TreeSet;

/**
 * Class to build an index.
 */
public class IndexGenerator {
    // Data fields
    private final TreeSet<String> index;
    /**
     * Pattern for extracting words from a line. A word is a string of
     * one or more letters or numbers or ' characters.
     */
    private static final String PATTERN = "[\\p{L}\\p{N}']+";

    public IndexGenerator() {
        index = new TreeSet<>();
    }

    /**
     * Reads each word in a data file and stores it in an index
     * along with its line number.
     *
     * @param scanner A scanner object.
     * @post Lowercase form of each word with its line number is stored in the index.
     */
    public void buildIndex(Scanner scanner) {
        int lineNum = 0;

        // Keep reading lines until done.
        while (scanner.hasNextLine()) {
            lineNum++;
            String token;

            while ((token = scanner.findInLine(PATTERN)) != null) {
                token = token.toLowerCase();
                index.add(String.format("%s, %3d", token, lineNum));
            }
            scanner.nextLine(); // clear the scan buffer.
        }
    }

    /**
     * Displays the index, one word per line.
     */
    public void showIndex() {
        index.forEach(System.out::println);
    }
}
