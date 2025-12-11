package codingpractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * You are given a 2D array. An hourglass in an array is a portion shaped like this:
 * <pre>
 * a b c
 *  ´d
 * e f g
 * </pre>
 * Actually, there are many hourglasses in the array.
 * The sum of an hourglass is the sum of all the numbers within it.
 * In this problem, you have to print the largest sum among all the hourglasses in the array.
 *
 * @pre Each integer will be between -9 and 9 inclusive.
 */
public class SolutionMaxHourGlass {
    // Data fields
    static int[][] grid = new int[6][6];

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            List<List<Integer>> arr = new ArrayList<>();
            IntStream.range(0, 6).forEach(i -> {
                try {
                    arr.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                            .map(Integer::parseInt).collect(toList()));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            grid = arr.stream().map(x -> x.stream().mapToInt(Integer::intValue).toArray()).toArray(int[][]::new);
            System.out.println(sumOfHourGlass(grid));
        }
    }

    /**
     * The helper method to find the max sum of hour glasses in the array.
     *
     * @param arr The input array.
     * @return The sum of the max hourglass.
     */
    private static int sumOfHourGlass(int[][] arr) {
        int max = -53;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int sum =
                        (
                                arr[i][j] +
                                        arr[i][j + 1] +
                                        arr[i][j + 2] +
                                        arr[i + 1][j + 1] +
                                        arr[i + 2][j] +
                                        arr[i + 2][j + 1] +
                                        arr[i + 2][j + 2]);
                max = Math.max(max, sum);
            }
        }

        return max;
    }
}
