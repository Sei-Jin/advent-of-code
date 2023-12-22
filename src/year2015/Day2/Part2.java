package year2015.Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Part2 {
    public static void main (String[] args) throws FileNotFoundException {

        File fileInput = new File("input.txt");
        Scanner in = new Scanner(fileInput).useDelimiter("[x\n]");

        int totalRibbonLength = 0;

        while (in.hasNextInt()) {
            int length = in.nextInt();
            int width = in.nextInt();
            int height = in.nextInt();

            ArrayList<Integer> sideLengths = new ArrayList<>();
            sideLengths.add(length);
            sideLengths.add(width);
            sideLengths.add(height);

            Collections.sort(sideLengths);

            int wrapLength = 2 * sideLengths.get(0) + 2 * sideLengths.get(1);
            int bowLength = length * width * height;

            totalRibbonLength += wrapLength + bowLength;
        }

        System.out.println(totalRibbonLength);
    }
}
