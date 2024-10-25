package advent_of_code.year2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
        
        for (String line : inputLines)
        {
            partOne(line);
            partTwo(line);
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
        
        runProgram(intcode);
        
        System.out.println("The value left at position 0 after the program halts is: " + intcode.getFirst());
    }
    
    
    private static void partTwo(String line)
    {
        List<Integer> defaultIntcode = Arrays.stream(line.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
        
        for (int noun = 0; noun <= 99; noun++)
        {
            for (int verb = 0; verb <= 99; verb++)
            {
                List<Integer> intcode = new ArrayList<>(List.copyOf(defaultIntcode));
                
                intcode.set(1, noun);
                intcode.set(2, verb);
                
                runProgram(intcode);
                
                if (intcode.getFirst() == 19690720)
                {
                    System.out.println(100 * noun + verb);
                    return;
                }
            }
        }
    }
    
    
    private static void runProgram(List<Integer> intcode)
    {
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
