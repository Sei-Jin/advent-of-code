package year2015.Day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {

        File inputFile = new File("input.txt");
        Scanner in = new Scanner(inputFile);

        boolean[][] lightGrid = new boolean[1000][1000];

        while (in.hasNext()) {

            String operation = in.next();
            if (operation.compareTo("turn") == 0) {
                operation = in.next();
            }

            in.useDelimiter("[ ,\n]");
            int topCornerX = in.nextInt();
            int topCornerY = in.nextInt();

            in.next();
            int bottomCornerX = in.nextInt();
            int bottomCornerY = in.nextInt();

            for (int row = topCornerY; row <= bottomCornerY; row++) {
                for (int column = topCornerX; column <= bottomCornerX; column++) {
                    if (operation.compareTo("on") == 0) {
                        lightGrid[row][column] = true;
                    }

                    if (operation.compareTo("off") == 0) {
                        lightGrid[row][column] = false;
                    }

                    if (operation.compareTo("toggle") == 0) {
                        lightGrid[row][column] = !lightGrid[row][column];
                    }
                }
            }

        }

        int onLights = 0;

        // Count how many lights are turned on
        for (int row = 0; row < lightGrid.length; row++) {
            for (int column = 0; column < lightGrid[0].length; column++) {
                if (lightGrid[row][column]) {
                    onLights++;
                }
            }
        }

        System.out.println(onLights);
    }
}
