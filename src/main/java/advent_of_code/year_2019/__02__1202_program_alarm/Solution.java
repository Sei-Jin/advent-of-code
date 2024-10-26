package advent_of_code.year_2019.__02__1202_program_alarm;

import advent_of_code.PuzzleSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * --- Day 2: 1202 Program Alarm ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the value left at position 0 after the program halts.
     */
    @Override
    public Object partOne(List<String> inputLines)
    {
        String inputLine = inputLines.getFirst();
        
        List<Integer> intcode = Arrays.stream(inputLine.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        
        // Restore program state before running the program
        intcode.set(1, 12);
        intcode.set(2, 2);
        
        runProgram(intcode);
        
        return intcode.getFirst();
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the input noun and verb that cause the program to produce the output 19690720, or -1 if there are none.
     */
    @Override
    public Object partTwo(List<String> inputLines)
    {
        String inputLine = inputLines.getFirst();
        
        List<Integer> defaultIntcode = Arrays.stream(inputLine.split(","))
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
                    return 100 * noun + verb;
                }
            }
        }
        
        return -1;
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
