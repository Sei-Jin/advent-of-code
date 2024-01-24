package year2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * --- Day 2: 1202 Program Alarm ---
 */
public class Day02
{
    
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        for (String line : inputLines) {
            partOne(line);
        }
    }
    
    
    private static void partOne(String line)
    {
        List<Integer> intcode = Arrays.stream(line.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        
        // Restore program state before running the program
        intcode.set(1, 12);
        intcode.set(2, 2);
        
        // Run the program
        for (int position = 0; position < intcode.size(); position += 4)
        {
            int opcode = intcode.get(position);
            
            switch (opcode)
            {
                case 1 -> add(intcode, position);
                case 2 -> multiply(intcode, position);
                case 99 -> position = intcode.size();
            }
        }
        
        System.out.println("The value left at position 0 after the program halts is: " + intcode.getFirst());
    }
    
    
    private static void add(List<Integer> intcode, int position)
    {
        int firstInputPosition = intcode.get(position + 1);
        int secondInputPosition = intcode.get(position + 2);
        int outputPosition = intcode.get(position + 3);
        
        int sum = intcode.get(firstInputPosition) + intcode.get(secondInputPosition);
        
        intcode.set(outputPosition, sum);
    }
    
    
    private static void multiply(List<Integer> intcode, int position)
    {
        int firstInputPosition = intcode.get(position + 1);
        int secondInputPosition = intcode.get(position + 2);
        int outputPosition = intcode.get(position + 3);
        
        int product = intcode.get(firstInputPosition) * intcode.get(secondInputPosition);
        
        intcode.set(outputPosition, product);
    }
}
