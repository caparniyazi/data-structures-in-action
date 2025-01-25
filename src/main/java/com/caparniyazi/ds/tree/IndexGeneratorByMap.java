package com.caparniyazi.ds.tree;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

/**
 * Class to build an index.
 * We previously used a binary search tree to store an index of words occurring in a term
 * paper. Each data element in the tree was a string consisting of a word followed by a three digit
 * line number. Although this is one approach to storing an index, it would be more useful to store each
 * word and all the line numbers for that word as a single index entry. We could do this by
 * storing the index in a Map in which each word is a key and its associated value is a list of all
 * the line numbers at which the word occurs. While building the index, each time a word is
 * encountered, its list of line numbers would be retrieved (using the word as a key) and the
 * most recent line number would be appended to this list (a List<Integer>).
 */
public class IndexGeneratorByMap {
    // Data fields
    private final SortedMap<String, ArrayList<Integer>> index;
    // A Map object that will store each word occurring in a term paper along with the number of times the word occurs.
    private static final Map<String, Integer> COUNTS = new TreeMap<>();
    /**
     * Pattern for extracting words from a line. A word is a string of
     * one or more letters or numbers or ' characters.
     */
    private static final String PATTERN = "[\\p{L}\\p{N}']+";

    public IndexGeneratorByMap() {
        index = new TreeMap<>();
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

                // Get the list of line numbers for token
                ArrayList<Integer> lines = index.getOrDefault(token, new ArrayList<>());
                lines.add(lineNum);
                index.put(token, lines);    // Store new list of line numbers.
            }
            scanner.nextLine(); // Clear the buffer.
        }
    }

    public static void buildWordCounts(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String token;

            while ((token = scanner.findInLine(PATTERN)) != null) {
                token = token.toLowerCase();
                COUNTS.put(token, COUNTS.getOrDefault(token, 0) + 1);
            }
            scanner.nextLine();
        }
        // Show counts.
        COUNTS.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    /**
     * Displays the index, one word per line.
     */
    public void showIndex() {
        index.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    public static void main(String[] args) {
        Path path = Path.of(System.getProperty("user.dir").concat("/JavaTutorial.txt"));
        Scanner scanner = null;
        try {
            scanner = new Scanner(path.toFile());
            IndexGeneratorByMap generator = new IndexGeneratorByMap();
            generator.buildIndex(scanner);
            generator.showIndex();
            scanner.close();
            System.out.println("Each word occurring with the number of times the word occurs:");
            scanner = new Scanner(path.toFile());
            IndexGeneratorByMap.buildWordCounts(scanner);
        } catch (FileNotFoundException e) {
            System.err.println("Error building index");
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
