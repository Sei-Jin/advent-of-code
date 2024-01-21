package year2015;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;

/**
 * --- Day 3: Perfectly Spherical Houses in a Vacuum ---
 */
public class Day03 {
    
    public static void main(String[] args) throws IOException {
        
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        for (String line : inputLines) {
            partOne(line);
            partTwo(line);
        }
    }
    
    
    private static void partOne(String line) {
        
        Position2D currentPosition = new Position2D(0, 0);
        
        HashSet<String> previousPositions = new HashSet<>();
        
        previousPositions.add(currentPosition.toString());
        int uniqueHousesVisited = 1;
        
        for (int i = 0; i < line.length(); i++) {
            updatePosition(currentPosition, line.charAt(i));
            
            if (newHouseVisited(previousPositions, currentPosition)) {
                uniqueHousesVisited++;
            }
        }
        
        System.out.println(uniqueHousesVisited + " houses received at least one present.");
    }
    
    
    private static void partTwo(String line) {
        
        Position2D currentPositionSanta = new Position2D(0, 0);
        Position2D currentPositionRobot = new Position2D(0, 0);
        
        HashSet<String> previousPositions = new HashSet<>();
        
        previousPositions.add(currentPositionSanta.toString());
        int uniqueHousesVisited = 1;
        
        for (int i = 0; i < line.length(); i++) {
            if (i % 2 == 0) {
                updatePosition(currentPositionSanta, line.charAt(i));
                
                if (newHouseVisited(previousPositions, currentPositionSanta)) {
                    uniqueHousesVisited++;
                }
            } else {
                updatePosition(currentPositionRobot, line.charAt(i));
                
                if (newHouseVisited(previousPositions, currentPositionRobot)) {
                    uniqueHousesVisited++;
                }
            }
        }
        
        System.out.println(uniqueHousesVisited + " houses received at least one present.");
    }
    
    
    private static boolean newHouseVisited(HashSet<String> previousPositions, Position2D currentPositionRobot) {
        
        if (!previousPositions.contains(currentPositionRobot.toString())) {
            previousPositions.add(currentPositionRobot.toString());
            return true;
        }
        
        return false;
    }
    
    
    private static void updatePosition(Position2D position, char direction) {
        
        switch (direction) {
            case '>' -> position.X++;
            case '<' -> position.X--;
            case '^' -> position.Y++;
            case 'v' -> position.Y--;
        }
    }
    
    
    private static class Position2D {
        
        int X, Y;
        
        Position2D(int X, int Y) {
            this.X = X;
            this.Y = Y;
        }
        
        @Override
        public String toString() {
            return "Position2D{" +
                    "X=" + X +
                    ", Y=" + Y +
                    '}';
        }
    }
}
