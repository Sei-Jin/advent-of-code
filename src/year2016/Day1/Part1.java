package year2016.Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 * --- Day 1: No Time for a Taxicab ---
 */
public class Part1 {
    
    public static void main(String[] args) throws IOException {
        
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        for (String line : inputLines) {
            partOne(line);
        }
    }
    
    
    private static void partOne (String line) {
        
        Point point = new Point(0, 0);
        Direction currentDirection = Direction.NORTH;
        
        String[] moveSequence = line.split(", ");
        
        for (String move : moveSequence) {
            char turningDirection = move.charAt(0);
            int distance = Integer.parseInt(move.substring(1));
            
            currentDirection = changeDirection(currentDirection, turningDirection);
            
            switch (currentDirection) {
                case NORTH -> point.Y += distance;
                case EAST  -> point.X += distance;
                case SOUTH -> point.Y -= distance;
                case WEST  -> point.X -= distance;
            }
        }
        
        int destinationDistance = Math.abs(point.X) + Math.abs(point.Y);
        System.out.println("The Easter Bunny HQ is " + destinationDistance + " blocks away.");
    }
    
    
    private static class Point {
        int X, Y;
        
        Point (int X, int Y) {
            this.X = X;
            this.Y = Y;
        }
    }
    
    
    private static Direction changeDirection(Direction currentDirection, char turningDirection) {
        
        if (turningDirection == 'R') {
            currentDirection = switch (currentDirection) {
                case NORTH -> Direction.EAST;
                case EAST  -> Direction.SOUTH;
                case SOUTH -> Direction.WEST;
                case WEST  -> Direction.NORTH;
            };
        }
        
        if (turningDirection == 'L') {
            currentDirection = switch (currentDirection) {
                case NORTH -> Direction.WEST;
                case WEST  -> Direction.SOUTH;
                case SOUTH -> Direction.EAST;
                case EAST  -> Direction.NORTH;
            };
        }
        
        return currentDirection;
    }
    
    
    private enum Direction {
        NORTH, EAST, SOUTH, WEST;
    }
}
