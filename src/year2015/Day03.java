package year2015;

import java.awt.*;
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
        
        Point currentPosition = new Point();
        
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
        
        Point currentPositionSanta = new Point();
        Point currentPositionRobot = new Point();
        
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
    
    
    private static boolean newHouseVisited(HashSet<String> previousPositions, Point currentPositionRobot) {
        
        if (!previousPositions.contains(currentPositionRobot.toString())) {
            previousPositions.add(currentPositionRobot.toString());
            return true;
        }
        
        return false;
    }
    
    
    private static void updatePosition(Point position, char direction) {
        
        switch (direction) {
            case '>' -> position.x++;
            case '<' -> position.x--;
            case '^' -> position.y++;
            case 'v' -> position.y--;
        }
    }
}
