package advent_of_code.year_2021.__02__dive;

import advent_of_code.PuzzleSolver;

import java.util.List;

/**
 * --- Day 2: Dive! ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the product of the final horizontal position and the final depth.
     */
    @Override
    public Object partOne(List<String> inputLines)
    {
        int horizontalPosition = 0;
        int depth = 0;
        
        for (String line : inputLines)
        {
            String[] values = line.split(" ");
            
            Direction direction = switch (values[0])
            {
                case "forward" -> Direction.FORWARD;
                case "down" -> Direction.DOWN;
                case "up" -> Direction.UP;
                default -> throw new IllegalStateException("Unexpected value: " + values[0]);
            };
            
            int distance = Integer.parseInt(values[1]);
            
            switch (direction)
            {
                case FORWARD -> horizontalPosition += distance;
                case DOWN -> depth += distance;
                case UP -> depth -= distance;
            }
        }
        
        return horizontalPosition * depth;
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return The product of the final horizontal position and the final depth.
     */
    public Object partTwo(List<String> inputLines)
    {
        int horizontalPosition = 0;
        int depth = 0;
        int aim = 0;
        
        for (String line : inputLines)
        {
            String[] values = line.split(" ");
            
            Direction direction = switch (values[0])
            {
                case "forward" -> Direction.FORWARD;
                case "down" -> Direction.DOWN;
                case "up" -> Direction.UP;
                default -> throw new IllegalStateException("Unexpected value: " + values[0]);
            };
            
            int units = Integer.parseInt(values[1]);
            
            switch (direction)
            {
                case FORWARD ->
                {
                    horizontalPosition += units;
                    depth += aim * units;
                }
                case DOWN -> aim += units;
                case UP -> aim -= units;
            }
        }
        
        return horizontalPosition * depth;
    }
    
    
    private enum Direction
    {
        DOWN, FORWARD, UP
    }
}
