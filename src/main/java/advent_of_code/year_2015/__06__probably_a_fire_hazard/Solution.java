package advent_of_code.year_2015.__06__probably_a_fire_hazard;

import advent_of_code.PuzzleSolver;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

/**
 * --- Day 6: Probably a Fire Hazard ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the number of lit lights after following the instructions.
     */
    public Object partOne(List<String> inputLines)
    {
        boolean[][] lightGrid = new boolean[1000][1000];
        
        for (String line : inputLines)
        {
            Instruction instruction = getInstruction(line);
            
            for (int row = instruction.topCorner.y; row <= instruction.bottomCorner.y; row++)
            {
                for (int column = instruction.topCorner.x; column <= instruction.bottomCorner.x; column++)
                {
                    switch (instruction.operation)
                    {
                        case ON -> lightGrid[row][column] = true;
                        case OFF -> lightGrid[row][column] = false;
                        case TOGGLE -> lightGrid[row][column] = !lightGrid[row][column];
                    }
                }
            }
        }
        
        int totalLightsTurnedOn = 0;
        
        for (int row = 0; row < lightGrid.length; row++)
        {
            for (int column = 0; column < lightGrid[0].length; column++)
            {
                if (lightGrid[row][column])
                {
                    totalLightsTurnedOn++;
                }
            }
        }
        
        return totalLightsTurnedOn;
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the total brightness of all lights combined after following Santa's instructions.
     */
    public Object partTwo(List<String> inputLines)
    {
        int[][] lightGrid = new int[1000][1000];
        
        for (String line : inputLines)
        {
            Instruction instruction = getInstruction(line);
            
            for (int row = instruction.topCorner.y; row <= instruction.bottomCorner.y; row++)
            {
                for (int column = instruction.topCorner.x; column <= instruction.bottomCorner.x; column++)
                {
                    switch (instruction.operation)
                    {
                        case ON -> lightGrid[row][column]++;
                        case OFF ->
                        {
                            if (lightGrid[row][column] > 0)
                            {
                                lightGrid[row][column]--;
                            }
                        }
                        case TOGGLE -> lightGrid[row][column] += 2;
                    }
                }
            }
        }
        
        int totalBrightness = 0;
        
        for (int row = 0; row < lightGrid.length; row++)
        {
            for (int column = 0; column < lightGrid[0].length; column++)
            {
                totalBrightness += lightGrid[row][column];
            }
        }
        
        return totalBrightness;
    }
    
    
    private static Instruction getInstruction(String line)
    {
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
        
        if (line.contains(Operation.ON.getValue()))
        {
            operation = Operation.ON;
        }
        else if (line.contains(Operation.OFF.getValue()))
        {
            operation = Operation.OFF;
        }
        else if (line.contains(Operation.TOGGLE.getValue()))
        {
            operation = Operation.TOGGLE;
        }
        
        return new Instruction(operation, topCorner, bottomCorner);
    }
    
    
    private record Instruction(Operation operation, Point topCorner, Point bottomCorner) {}
    
    
    private enum Operation
    {
        ON("on"),
        OFF("off"),
        TOGGLE("toggle");
        
        private final String value;
        
        Operation(String value)
        {
            this.value = value;
        }
        
        private String getValue()
        {
            return value;
        }
    }
}
