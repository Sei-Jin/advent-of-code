package year2015.Day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;

public class Part1 {
    
    public static void main(String[] args) throws IOException {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        for (String line : inputLines) {
            partOne(line);
        }
    }
    
    
    private static void partOne(String line) {
        Position2D currentPosition = new Position2D(0, 0);
        
        HashSet<String> previousPositions = new HashSet<>();
        
        previousPositions.add(currentPosition.toString());
        int housesVisited = 1;
        
        for (int i = 0; i < line.length(); i++) {
            switch (line.charAt(i)) {
                case '>' -> currentPosition.X++;
                case '<' -> currentPosition.X--;
                case '^' -> currentPosition.Y++;
                case 'v' -> currentPosition.Y--;
            }
            
            if (!previousPositions.contains(currentPosition.toString())) {
                previousPositions.add(currentPosition.toString());
                housesVisited++;
            }
        }
        
        System.out.println(housesVisited + " houses received at least one present.");
    }
    
    
    private static class Position2D {
        int X, Y;
        
        Position2D (int X, int Y) {
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
