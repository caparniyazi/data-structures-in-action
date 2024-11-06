package com.caparniyazi.ds.recursion;

/**
 * We can always write an iterative solution to any problem that is solvable by recursion.
 * However, the recursive solutions will be easier to conceptualize and should, therefore, lead to
 * methods that are easier to write, read, and debug—all of which are very desirable attributes
 * of code.
 */
public class RecursiveMethods {
    /**
     * Recursive method length.
     *
     * @param str The string
     * @return The length of the string.
     */
    public static int length(String str) {
        if (str == null || str.isEmpty()) { // The base case.
            return 0;
        } else {
            return 1 + length(str.substring(1));    // The recursive case.
        }
    }

    /**
     * The argument string is displayed, one character per line.
     *
     * @param str The string
     */
    public static void printChars(String str) {
        if (str == null || str.isEmpty()) {
            return;
        } else {
            System.out.println(str.charAt(0));
            printChars(str.substring(1));
        }
    }

    /**
     * The argument string is displayed in reverse.
     *
     * @param str The string
     */
    public static void printCharsReverse(String str) {
        if (str == null || str.isEmpty()) {
            return;
        } else {
            printCharsReverse(str.substring(1));
            System.out.println(str.charAt(0));
        }
    }

    /**
     * Recursive algorithm that determines whether a specified target char is present
     * in a string.
     *
     * @param str The string
     * @param ch  The target char being sought for.
     * @return true if target is present, or false if it is not.
     */
    public static boolean searchString(String str, char ch) {
        if (str == null || str.isEmpty()) {
            return false;
        } else if (str.charAt(0) == ch) {
            return true;
        } else {
            return searchString(str.substring(1), ch);
        }
    }

    /**
     * Recursive method that forms the integer sum of all digit characters in a string.
     * For example, the result of toNumber("3ac4") would be 7.
     *
     * @param str The input string.
     * @return The integer sum of all digit characters in a string.
     */
    public static int toNumber(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }
        char next = str.charAt(0);

        if (Character.isDigit(next)) {
            return Character.digit(next, 10) + toNumber(str.substring(1));
        } else {
            return toNumber(str.substring(1));
        }
    }

    /**
     * Recursive method that returns a string with each character in its argument
     * repeated. For example, if the string passed to stutter is "hello", stutter will return the
     * string "hheelllloo".
     *
     * @param str The string.
     * @return each character in its argument repeated.
     */
    public static String stutter(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        } else {
            return "" + str.charAt(0) + str.charAt(0) + stutter(str.substring(1));
        }
    }

    public static int mystery(int n) {
        if (n == 0)
            return 0;
        else
            return n * n + mystery(n - 1);
    }

    /**
     * Recursive factorial method.
     *
     * @param n The integer whose factorial is being computed.
     * @return n!
     * @pre n >= 0
     */
    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }

        if (n == 0) {   // Stopping/base case.
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    /**
     * Recursive power method.
     *
     * @param x The number being raised. x^n = x . x ^ (n-1)
     * @param n The exponent.
     * @return x raised to the power of n.
     */
    public static double power(double x, int n) {
        if (n < 0) {
            return 1.0 / power(x, -n);
        }

        if (n == 0) {
            return 1.0;
        } else {
            return x * power(x, n - 1);
        }
    }

    /**
     * Recursive greatest common divisor method.
     * Method gcd is an example of tail recursion. In tail recursion,
     * the last thing a method does is to call itself.
     *
     * @param a The larger number
     * @param b The smaller number
     * @return Greatest common divisor of a and b.
     * @pre a > 0 and b > 0
     */
    public static double gcd(int a, int b) {
        if (a % b == 0) {
            return b;
        } else {
            return gcd(b, a % b);
        }
    }

    /**
     * Recursive  method to calculate Fibonacci numbers.
     * This solution is very inefficient because of multiple calls to fibonacci with the
     * same argument. For example, calculating fibonacci(5) results in calls to fibonacci(4) and
     * fibonacci(3). Calculating fibonacci(4) results in calls to fibonacci(3) (second call) and
     * also fibonacci(2).
     * <p/>
     * Because of the redundant method calls, the time required to calculate fibonacci(n) increases
     * exponentially with n. For example, if n is 100, there are approximately 2100 activation frames.
     * This number is approximately 1030. If you could process one million activation frames per
     * second, it would still take 1024 seconds, which is approximately 3 × 1016 years.
     *
     * @param n The position of the Fibonacci number being calculated.
     * @return The Fibonacci number.
     * @throws IllegalStateException if n <= 0
     * @pre n >= 1
     */
    public static int fibonacci(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n <= 0");
        }

        if (n <= 2) {
            return 1;
        } else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    /**
     * Recursive O(n) method to calculate Fibonacci numbers.
     *
     * @param fibCurrent The current Fibonacci number.
     * @param fibPrev    The previous Fibonacci number.
     * @param n          The count of Fibonacci numbers left to calculate.
     * @return The value of the Fibonacci number calculated so far.
     * @pre n >=1
     */
    private static long fibo(long fibCurrent, long fibPrev, int n) {
        if (n == 1) {
            return fibCurrent;
        } else {
            return fibo(fibCurrent + fibPrev, fibCurrent, n - 1);
        }
    }

    /**
     * Wrapper method for calculating Fibonacci numbers.
     *
     * @param n The position of the desired Fibonacci number.
     * @return The value of the nth Fibonacci number.
     * @pre n >= 0
     */
    public static long fibonacciStart(int n) {
        if (n == 0) {
            return 0;
        } else {
            return fibo(1, 0, n - 1);
        }
    }

    /**
     * Recursive linear search method.
     *
     * @param items    The array being searched.
     * @param target   The item being searched for.
     * @param posFirst The position of the current first item.
     * @return The subscript of target if found, otherwise -1.
     */
    private static int linearSearch(Object[] items, Object target, int posFirst) {
        if (posFirst == items.length) { // The base is an empty array.
            return -1;
        } else if (target.equals(items[posFirst])) { // The other base case is when the 1st array item matches the target.
            return posFirst;
        } else {
            // Search the array excluding the 1st item and return the result.
            return linearSearch(items, target, posFirst + 1);   // The recursive step is to search the rest of the array.
        }
    }

    /**
     * Wrapper for recursive linear search method.
     * The sole purpose of this method is to call the recursive method, passing on its arguments with
     * 0 as a third argument, and return its result.
     *
     * @param items  The array being searched.
     * @param target The item being searched for.
     * @return The subscript of target if found; otherwise -1.
     */
    public static int linearSearch(Object[] items, Object target) {
        return linearSearch(items, target, 0);
    }

    /**
     * Recursive binary search method.
     *
     * @param items  The array being searched.
     * @param target The item being searched for.
     * @param first  The subscript of the first item.
     * @param last   The subscript of the last item.
     * @param <T>    The item type.
     * @return The subscript of target if found, otherwise -1.
     */
    private static <T> int binarySearch(T[] items, Comparable<T> target, int first, int last) {
        if (first > last) {
            return -1;   // Base case for unsuccessful search
        } else {
            int mid = (first + last) / 2;
            int compResult = target.compareTo(items[mid]);
            if (compResult == 0) {
                return mid;
            } else if (compResult < 0) {
                return binarySearch(items, target, first, mid - 1);
            } else {
                return binarySearch(items, target, mid + 1, last);
            }
        }
    }

    /**
     * Wrapper for recursive binary search method.
     * <p/>
     * Because we eliminate at least half of the array elements from consideration with each recursive
     * call, binary search is an O(log n) algorithm. To verify this, an unsuccessful search of an array
     * of size 16 could result in our searching arrays of size 16, 8, 4, 2, and 1 to determine that the
     * target was not present. Thus, an array of size 16 requires a total of 5 probes in the worst case
     * (16 is 24, so 5 is log 216 + 1). If we double the array size, we would need to make only 6 probes
     * for an array of size 32 in the worst case (32 is 25, so 6 is log2 32 + 1).
     * <p/>
     * The Java API class Arrays contains a binarySearch method. It can be called with sorted
     * arrays of primitive types or with sorted arrays of objects. If the objects in the array are not
     * mutually comparable or if the array is not sorted, the results are undefined. If there are
     * multiple copies of the target value in the array, there is no guarantee as to which one will
     * be found. This is the same as for our binarySearch method.
     *
     * @param items  The array being searched.
     * @param target The item being searched for.
     * @param <T>    The item type.
     * @return The subscript of target if found, otherwise -1.
     */
    public static <T> int binarySearch(T[] items, Comparable<T> target) {
        return binarySearch(items, target, 0, items.length - 1);
    }

    /**
     * Method to return the max integer in an array.
     *
     * @param arr   The array of integers to search.
     * @param index The index of the current value to consider.
     * @return The max value in the array.
     */
    private static int maximum(int[] arr, int index) {
        if (index == arr.length - 1) {
            return arr[index];
        }
        int maxResult = maximum(arr, index + 1);
        return Math.max(arr[index], maxResult);
    }

    /**
     * Wrapper for recursive max method.
     *
     * @param arr The array of integers to search.
     * @return The max value in the array.
     */
    public static int maximum(int[] arr) {
        return maximum(arr, 0);
    }

    /**
     * Method to return the sum of integer in an array.
     *
     * @param arr   The array of integers to sum.
     * @param index The index of the current value to consider.
     * @return The sum of values in the array.
     */
    private static int sum(int[] arr, int index) {
        if (index == arr.length - 1) {
            return arr[index];
        }
        int sum = sum(arr, index + 1);
        return arr[index] + sum;
    }

    /**
     * Wrapper for recursive sum method.
     *
     * @param arr The array of integers to sum.
     * @return The sum of values in the array.
     */
    public static int sum(int[] arr) {
        return sum(arr, 0);
    }


    /**
     * Recursive linear search method.
     *
     * @param items   The array being searched.
     * @param target  The item being searched for.
     * @param posLast The position of the current first item.
     * @return The subscript of target if found, otherwise -1.
     */
    private static int linearSearchReverse(Object[] items, Object target, int posLast) {
        if (posLast == -1) {
            return -1;
        } else if (target.equals(items[posLast])) { // The other base case is when the 1st array item matches the target.
            return posLast;
        } else {
            // Search the array excluding the last item and return the result.
            return linearSearchReverse(items, target, posLast - 1);   // The recursive step is to search the rest of the array.
        }
    }

    /**
     * Wrapper for recursive linear search method.
     * The sole purpose of this method is to call the recursive method, passing on its arguments with
     * 0 as a third argument, and return its result.
     *
     * @param items  The array being searched.
     * @param target The item being searched for.
     * @return The subscript of target if found; otherwise -1.
     */
    public static int linearSearchReverse(Object[] items, Object target) {
        return linearSearchReverse(items, target, items.length - 1);
    }
}
