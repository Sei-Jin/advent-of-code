package year2016.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class Part2 {

    public static char NORTH = 'N';
    public static char EAST = 'E';
    public static char WEST = 'W';
    public static char SOUTH = 'S';


    public static void main(String[] args) throws FileNotFoundException {

        File inputFile = new File("input.txt");
        Scanner in = new Scanner(inputFile);

        String[] sequence = in.nextLine().split(", ");

        char currentDirection = NORTH;
        int xPosition = 0;
        int yPosition = 0;

        HashSet<String> positions = new HashSet<>();
        positions.add(xPosition + " " + yPosition);

        for (String s : sequence) {

            // Update the direction we are facing
            currentDirection = determineDirection(currentDirection, s.charAt(0));

            // Determine the distance to be travelled
            int distance = Integer.parseInt(s.substring(1));

            // If we run into a position we already visited before, calculate the current distance
            for (int blocks = 1; blocks <= distance; blocks++) {

                // Update the current position
                if (currentDirection == NORTH) {
                    yPosition++;
                } else if (currentDirection == EAST) {
                    xPosition++;
                } else if (currentDirection == SOUTH) {
                    yPosition--;
                } else {
                    xPosition--;
                }
                String currentPosition = xPosition + " " + yPosition;

                // Check if we have already visited this position before
                if (positions.contains(currentPosition)) {
                    int currentDistance = Math.abs(xPosition) + Math.abs(yPosition);
                    System.out.println(currentDistance);
                    return;
                } else {
                    positions.add(currentPosition);
                }
            }
        }
    }


    static char determineDirection(char currentDirection, char turnDirection) {

        char newDirection = 0;

        // Turning Right
        if (turnDirection == 'R') {
            // Change Direction
            if (currentDirection == NORTH) {
                newDirection = EAST;
            } else if (currentDirection == EAST) {
                newDirection = SOUTH;
            } else if (currentDirection == SOUTH) {
                newDirection = WEST;
            } else {
                newDirection = NORTH;
            }
        }

        // Turning left
        if (turnDirection == 'L') {
            // Change Direction
            if (currentDirection == NORTH) {
                newDirection = WEST;
            } else if (currentDirection == WEST) {
                newDirection = SOUTH;
            } else if (currentDirection == SOUTH) {
                newDirection = EAST;
            } else {
                newDirection = NORTH;
            }
        }

        return newDirection;
    }
}
