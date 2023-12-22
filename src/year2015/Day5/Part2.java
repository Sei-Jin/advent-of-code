package year2015.Day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {

        File inputFile = new File("input.txt");
        Scanner in = new Scanner(inputFile);

        int niceStringTotal = 0;

        while (in.hasNextLine()) {
            String line = in.nextLine();

            boolean niceString = true;

            // The line contains a pair of any two letters that appears at least twice in the string without overlapping
            if (!line.matches("^.*([a-z]{2}).*\\1.*$")) {
                niceString = false;
            }

            // The line contains at least one letter that repeats with exactly 1 letter between them
            if (!line.matches("^.*([a-z]).\\1.*$")) {
                niceString = false;
            }

            if (niceString) {
                niceStringTotal++;
            }
        }

        System.out.println(niceStringTotal);
    }
}
