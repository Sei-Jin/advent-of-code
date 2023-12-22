package year2015.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part2 {
    public static void main(String[] args) throws FileNotFoundException {

        File inFile = new File("input.txt");
        Scanner in = new Scanner(inFile);

        String parenthesis = "";
        while (in.hasNextLine()) {
            parenthesis = in.nextLine();
        }

        int floor = 0;
        for (int i = 0; i < parenthesis.length(); i++) {
            if (parenthesis.charAt(i) == '(') {
                floor++;
            } else {
                floor--;
            }

            if (floor < 0) {
                System.out.println(i + 1);
                break;
            }
        }
    }
}
