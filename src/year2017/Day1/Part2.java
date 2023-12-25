package year2017.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {

        File inFile = new File("sample.txt");
        Scanner in = new Scanner(inFile);

        String sequence = in.nextLine();

        int total = 0;

        for (int i = 0; i < sequence.length(); i++) {
            if (sequence.charAt(i) == sequence.charAt((i + 1) % sequence.length())) {
                total += Character.getNumericValue(sequence.charAt(i));
            }
        }

        System.out.println(total);
    }
}
