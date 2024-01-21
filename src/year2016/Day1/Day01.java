package year2016.Day1;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;

/**
 * --- Day 1: No Time for a Taxicab ---
 */
public class Day01 {
    
    public static void main(String[] args) throws IOException {
        
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        for (String line : inputLines) {
            partOne(line);
            partTwo(line);
        }
    }
    
    
    private static void partOne(String line) {
        
        Point point = new Point();
        Direction currentDirection = Direction.NORTH;
        
        String[] moveSequence = line.split(", ");
        
        for (String move : moveSequence) {
            char turningDirection = move.charAt(0);
            int distance = Integer.parseInt(move.substring(1));
            
            currentDirection = changeDirection(currentDirection, turningDirection);
            
            switch (currentDirection) {
                case NORTH -> point.y += distance;
                case EAST -> point.x += distance;
                case SOUTH -> point.y -= distance;
                case WEST -> point.x -= distance;
            }
        }
        
        int destinationDistance = Math.abs(point.x) + Math.abs(point.y);
        System.out.println("The Easter Bunny HQ is " + destinationDistance + " blocks away.");
    }
    
    
    private static void partTwo(String line) {
        
        Point point = new Point();
        Direction currentDirection = Direction.NORTH;
        
        HashSet<String> pointsVisited = new HashSet<>();
        pointsVisited.add(point.toString());
        
        String[] moveSequence = line.split(", ");
        
        for (String move : moveSequence) {
            
            char turningDirection = move.charAt(0);
            int distance = Integer.parseInt(move.substring(1));
            
            currentDirection = changeDirection(currentDirection, turningDirection);
            
            for (int blocksTravelled = 0; blocksTravelled < distance; blocksTravelled++) {
                
                switch (currentDirection) {
                    case NORTH -> point.y++;
                    case EAST -> point.x++;
                    case SOUTH -> point.y--;
                    case WEST -> point.x--;
                }
                
                if (pointsVisited.contains(point.toString())) {
                    int currentDistance = Math.abs(point.x) + Math.abs(point.y);
                    System.out.println("The distance of the first location visited twice is: " + currentDistance);
                    return;
                } else {
                    pointsVisited.add(point.toString());
                }
            }
        }
    }
    
    
    private static Direction changeDirection(Direction currentDirection, char turningDirection) {
        
        if (turningDirection == 'R') {
            currentDirection = switch (currentDirection) {
                case NORTH -> Direction.EAST;
                case EAST -> Direction.SOUTH;
                case SOUTH -> Direction.WEST;
                case WEST -> Direction.NORTH;
            };
        }
        
        if (turningDirection == 'L') {
            currentDirection = switch (currentDirection) {
                case NORTH -> Direction.WEST;
                case WEST -> Direction.SOUTH;
                case SOUTH -> Direction.EAST;
                case EAST -> Direction.NORTH;
            };
        }
        
        return currentDirection;
    }
    
    
    private enum Direction {
        NORTH, EAST, SOUTH, WEST;
    }
}
