package year2015.Day6;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    
    public static void main(String[] args) throws IOException {

        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
    }
    
    
    private static void partOne(List<String> inputLines) {
        
        boolean[][] lightGrid = new boolean[1000][1000];
        
        for (String line : inputLines) {
            Instruction instruction = getInstruction(line);
            
            for (int row = instruction.topCorner.y; row <= instruction.bottomCorner.y; row++) {
                for (int column = instruction.topCorner.x; column <= instruction.bottomCorner.x; column++) {
                    switch (instruction.operation) {
                        case ON -> lightGrid[row][column] = true;
                        case OFF -> lightGrid[row][column] = false;
                        case TOGGLE -> lightGrid[row][column] = !lightGrid[row][column];
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
        
        System.out.println("After following the instructions, the number of lit lights is: " + onLights);
    }
    
    
    private static Instruction getInstruction (String line) {
        
        int[] values = Arrays.stream(line.split("[^0-9]+"))
                .skip(1)
                .mapToInt(Integer::parseInt)
                .toArray();
        
        int topCornerX = values[0];
        int topCornerY = values[1];
        Point topCorner = new Point(topCornerX, topCornerY);
        
        int bottomCornerX = values[2];
        int bottomCornerY = values[3];
        Point bottomCorner = new Point(bottomCornerX, bottomCornerY);
        
        Operation operation = null;
        if (line.contains(Operation.ON.getValue())) {
            operation = Operation.ON;
        } else if (line.contains(Operation.OFF.getValue())) {
            operation = Operation.OFF;
        } else if (line.contains(Operation.TOGGLE.getValue())) {
            operation = Operation.TOGGLE;
        }
        
        return new Instruction(operation, topCorner, bottomCorner);
    }


    private record Instruction (Operation operation, Point topCorner, Point bottomCorner) {}
    
    
    private enum Operation {
        
        ON ("on"),
        OFF ("off"),
        TOGGLE ("toggle");
        
        private final String value;
        
        Operation(String value) {
            this.value = value;
        }
        
        private String getValue() { return value; }
    }
}
