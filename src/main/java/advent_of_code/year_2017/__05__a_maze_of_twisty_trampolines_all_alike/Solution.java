package advent_of_code.year_2017.__05__a_maze_of_twisty_trampolines_all_alike;

import advent_of_code.PuzzleSolver;

import java.util.List;
import java.util.stream.Collectors;

/**
 * --- Day 5: A Maze of Twisty Trampolines, All Alike ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the number of steps it takes to reach the exit.
     */
    @Override
    public Object partOne(List<String> inputLines)
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
        
        return steps;
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the number of steps it now takes to reach the exit.
     */
    @Override
    public Object partTwo(List<String> inputLines)
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
        
        return steps;
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
