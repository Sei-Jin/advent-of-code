package year2017.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {

        File inFile = new File("input.txt");
        Scanner in = new Scanner(inFile);

        String sequence = in.nextLine();

        int total = 0;

        for (int i = 0; i < sequence.length() - 1; i++) {
            if (sequence.charAt(i) == sequence.charAt(i + 1)) {
                total += Character.getNumericValue(sequence.charAt(i));
            }
        }

        if (sequence.charAt(sequence.length() - 1) == sequence.charAt(0)) {
            total += Character.getNumericValue(sequence.charAt(sequence.length() - 1));
        }

        System.out.println(total);
    }
}
