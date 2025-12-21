package codingpractice;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Scanner;

public class SolutionSHA256 {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

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
