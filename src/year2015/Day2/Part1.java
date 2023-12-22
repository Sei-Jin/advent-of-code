package year2015.Day2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
    public static void main (String[] args) throws FileNotFoundException {

        File fileInput = new File("input.txt");
        Scanner in = new Scanner(fileInput).useDelimiter("[x\n]");

        int totalArea = 0;

        while (in.hasNextInt()) {
            int length = in.nextInt();
            int width = in.nextInt();
            int height = in.nextInt();

            int area1 = length * width;
            int area2 = width * height;
            int area3 = height * length;

            int minArea = Integer.min(Integer.min(area1, area2), area3);
            int presentArea = 2 * (area1 + area2 + area3) + minArea;

            totalArea += presentArea;
        }

        System.out.println(totalArea);
    }
}
