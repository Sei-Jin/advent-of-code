package year2017;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * --- Day 5: A Maze of Twisty Trampolines, All Alike ---
 */
public class Day05
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
        partTwo(inputLines);
    }
    
    
    private static void partOne(List<String> inputLines)
    {
        int steps = 0;
        
        List<Integer> instructions = getInstructions(inputLines);
        
        int nextInstruction = instructions.getFirst();
        
        while (nextInstruction >= 0 && nextInstruction < instructions.size())
        {
            int previousInstruction = nextInstruction;
            
            int jumpOffset = instructions.get(nextInstruction);
            
            nextInstruction += jumpOffset;
            
            updateInstruction(instructions, previousInstruction, 1);

            steps++;
        }
        
        System.out.println("The number of steps it takes to reach the exit is: " + steps);
    }
    
    
    private static void partTwo(List<String> inputLines)
    {
        int steps = 0;
        
        List<Integer> instructions = getInstructions(inputLines);
        
        int nextInstruction = instructions.getFirst();
        
        while (nextInstruction >= 0 && nextInstruction < instructions.size())
        {
            int previousInstruction = nextInstruction;
            
            int jumpOffset = instructions.get(nextInstruction);
            
            nextInstruction += jumpOffset;
            
            if (jumpOffset >= 3)
            {
                updateInstruction(instructions, previousInstruction, -1);
            }
            else
            {
                updateInstruction(instructions, previousInstruction, 1);
            }
            
            steps++;
        }
        
        System.out.println("The number of steps it now takes to reach the exit is: " + steps);
    }
    
    
    private static List<Integer> getInstructions(List<String> inputLines)
    {
        return inputLines.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
    
    
    private static void updateInstruction(List<Integer> instructions, int instruction, int change)
    {
        instructions.set(instruction, instructions.get(instruction) + change);
    }
}
