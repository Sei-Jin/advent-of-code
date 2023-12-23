package year2016.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Part1 {
    public static void main(String[] args) throws FileNotFoundException {

        File inputFile = new File("input.txt");
        Scanner in = new Scanner(inputFile);

        String[] sequence = in.nextLine().split(", ");

        final char NORTH = 'N';
        final char EAST = 'E';
        final char WEST = 'W';
        final char SOUTH = 'S';

        char currentDirection = NORTH;
        int xPosition = 0;
        int yPosition = 0;

        for (String s : sequence) {

            // Turning right
            if (s.charAt(0) == 'R') {
                // Change Direction
                if (currentDirection == NORTH) {
                    currentDirection = EAST;
                } else if (currentDirection == EAST) {
                    currentDirection = SOUTH;
                } else if (currentDirection == SOUTH) {
                    currentDirection = WEST;
                } else {
                    currentDirection = NORTH;
                }
            }

            // Turning left
            if (s.charAt(0) == 'L') {
                // Change Direction
                if (currentDirection == NORTH) {
                    currentDirection = WEST;
                } else if (currentDirection == WEST) {
                    currentDirection = SOUTH;
                } else if (currentDirection == SOUTH) {
                    currentDirection = EAST;
                } else {
                    currentDirection = NORTH;
                }
            }

            // Calculate position change
            int distance = Integer.parseInt(s.substring(1));
            if (currentDirection == NORTH) {
                yPosition += distance;
            } else if (currentDirection == EAST) {
                xPosition += distance;
            } else if (currentDirection == SOUTH) {
                yPosition -= distance;
            } else {
                xPosition -= distance;
            }
        }

        int totalDistance = Math.abs(xPosition) + Math.abs(yPosition);
        System.out.println(totalDistance);
    }
}
