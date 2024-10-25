package advent_of_code.year2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * --- Day 2: Dive! ---
 */
public class Day02
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
        partTwo(inputLines);
    }
    
    
    private static void partOne(List<String> inputLines)
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
        
        int product = horizontalPosition * depth;
        
        System.out.println("The product of the final horizontal position and the final depth is: " + product);
    }
    
    
    private static void partTwo(List<String> inputLines)
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
        
        int product = horizontalPosition * depth;
        
        System.out.println("The product of the final horizontal position and the final depth is: " + product);
    }
    
    
    private enum Direction
    {
        DOWN, FORWARD, UP
    }
}
