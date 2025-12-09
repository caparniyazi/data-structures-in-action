package codingpractice;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Class to check if the given number is ODD, PRIME or PALINDROME.
 *
 * @pre The first number read from the console is the number n, the number of pairs to be supplied.
 * Space separates the consecutive pair of numbers entered afterward.
 * So that first number is either one, two, or three for ODD, PRIME, and PALINDROME check respectively,
 * and the second number is the actual number to be checked.
 */
public class SolutionOddPrimePalindrome {
    static boolean isOdd(int n) {
        return n % 2 == 1;
    }

    static boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    static boolean isPalindrome(int n) {
        int originalValue = n;
        int reversed = 0;

        while (n > 0) {
            int digit = n % 10;
            reversed = reversed * 10 + digit;
            n = n / 10;
        }

        return originalValue == reversed;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int n = sc.nextInt();
            String[] inputs = new String[n];
            sc.nextLine();

            for (int i = 0; i < n; i++) {
                inputs[i] = sc.nextLine();
            }

            for (int i = 0; i < n; i++) {
                int[] arr = Arrays.stream(inputs[i].split(" ")).mapToInt(Integer::parseInt).toArray();

                if (arr[0] == 1) {
                    System.out.println(isOdd(arr[1]) ? "ODD" : "EVEN");
                } else if (arr[0] == 2) {
                    System.out.println(isPrime(arr[1]) ? "PRIME" : "COMPOSITE");
                } else if (arr[0] == 3) {
                    System.out.println(isPalindrome(arr[1]) ? "PALINDROME" : "NOT PALINDROME");
                } else {
                    throw new IllegalArgumentException("Illegal argument supplied");
                }
            }
        }
    }
}
