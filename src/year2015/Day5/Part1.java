package year2015.Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {

        File inputFile = new File("input.txt");
        Scanner in = new Scanner(inputFile);

        int niceStrings = 0;

        while (in.hasNextLine()) {
            String line = in.nextLine();

            boolean nice = true;

            // The line must contain at least 3 vowels (aeiou only)
            if (!line.matches("^([^aeiou]*[aeiou]){3,}[^aeiou]*$")) {
                nice = false;
            }

            // The line must contain at least one letter that appears twice in a row
            if (!line.matches("^.*([a-z])\\1.*$")) {
                nice = false;
            }

            // The line cannot contain the strings "ab", "cd", "pq", and "xy"
            if (line.contains("ab")) {
                nice = false;
            }
            if (line.contains("cd")) {
                nice = false;
            }
            if (line.contains("pq")) {
                nice = false;
            }
            if (line.contains("xy")) {
                nice = false;
            }

            if (nice) {
                niceStrings++;
            }
        }

        System.out.println(niceStrings);
    }
}
