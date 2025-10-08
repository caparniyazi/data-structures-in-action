package com.caparniyazi.ds.tree;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;

public class IndexGeneratorWithMap {
    // Data fields
    private final Map<String, List<Integer>> index; // The search tree used to store the index.
    private static final String PATTERN = "[\\p{L}\\p{N}']+";

    public IndexGeneratorWithMap() {
        index = new TreeMap<>();
    }

    /**
     * Reads each word in a data file and stores it in an index
     * along with a list of line numbers where it occurs.
     *
     * @param scanner A scanner object.
     * @post The Lowercase form of each word with its line number is stored in the index.
     */
    public void buildIndex(Scanner scanner) {
        int lineNum = 0;

        while (scanner.hasNextLine()) {
            lineNum++;
            String token;

            while ((token = scanner.findInLine(PATTERN)) != null) {
                token = token.toLowerCase();

                // Get the list of line numbers for token.
                List<Integer> lines = index.getOrDefault(token, new ArrayList<>());
                lines.add(lineNum);
                index.put(token, lines);    // Store a new list of line numbers.
            }

            scanner.nextLine();
        }
    }

    // Display frequencies of word occurrences.
    public void showWordFrequency() {
        for (String word : index.keySet()) {
            System.out.printf("%-17s %s%n", word, index.get(word).size());
        }
    }

    /**
     * Displays the index, one word per line.
     */
    public void showIndex() {
        index.forEach((k, v) -> System.out.printf("%-17s %s%n", k, index.get(k)));
        System.out.println("Word Frequency: ----------");
        showWordFrequency();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Path path = Path.of(System.getProperty("user.dir").concat("/JavaTutorial.txt"));
        Scanner scanner = new Scanner(path.toFile());
        IndexGeneratorWithMap generator = new IndexGeneratorWithMap();
        generator.buildIndex(scanner);
        generator.showIndex();
    }
}
