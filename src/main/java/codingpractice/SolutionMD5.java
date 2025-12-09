package codingpractice;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Scanner;

/**
 * Given an alphanumeric string, s, denoting a password, compute and print its MD5 encryption value.
 * <p>
 * Input Format
 * <p>
 * A single alphanumeric string denoting s.
 * <p>
 * Constraints
 * <p>
 * <pre>
 *         6 <= s <=20
 *         String s consists of English alphabetic letters (i.e.,  and/or decimal digits (i.e., 0 through 9) only.
 *     </pre>
 */
public class SolutionMD5 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        try (Scanner in = new Scanner(System.in)) {
            /*
             * Java 15 version
             *
             String password = in.nextLine();
             byte[] bytes = md.digest(password.getBytes());
             String hex = "";

             for (byte i : bytes) {
                 hex += String.format("%02x", i);
             }

             System.out.print(hex);

             */

            // Java 17 version
            String password = in.nextLine();
            System.out.println(HexFormat.of().formatHex(md.digest(password.getBytes())));
        }
    }
}
