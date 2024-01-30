package year2017;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 5: A Maze of Twisty Trampolines, All Alike ---
 */
public class Day05
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        List<Integer> instructions = getInstructions(inputLines);
        
        partOne(instructions);
    }
    
    
    private static List<Integer> getInstructions(List<String> inputLines)
    {
        List<Integer> instructions = new ArrayList<>();
        
        for (String line : inputLines) {
            instructions.add(Integer.parseInt((line)));
        }
        
        return instructions;
    }
    
    
    private static void partOne(List<Integer> instructions)
    {
        int steps = 0;
        
        int nextInstruction = instructions.getFirst();
        
        while (nextInstruction >= 0 && nextInstruction < instructions.size())
        {
            int previousInstruction = nextInstruction;
            
            nextInstruction += instructions.get(nextInstruction);
            
            instructions.set(previousInstruction, instructions.get(previousInstruction) + 1);

            steps++;
        }
        
        System.out.println("The number of steps it takes to reach the exit is: " + steps);
    }
}
