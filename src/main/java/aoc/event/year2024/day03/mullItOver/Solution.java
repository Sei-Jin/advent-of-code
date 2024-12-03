package aoc.event.year2024.day03.mullItOver;

import aoc.PuzzleSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution implements PuzzleSolver
{
    /// Determines the sum of all multiplication instructions in the program memory.
    ///
    /// The puzzle input represents a program memory that was corrupted, and contains a series of instructions
    /// to follow.
    ///
    /// @param programMemory the puzzle input.
    /// @return the total sum of all multiplication instructions.
    @Override
    public Object partOne(List<String> programMemory)
    {
        int totalProduct = 0;
        
        for (String programSection : programMemory)
        {
            List<String> instructions = getValidInstructions(programSection);
            
            for (String instruction : instructions)
            {
                totalProduct += getProduct(instruction);
            }
        }
        
        return totalProduct;
    }
    
    /// Returns the list of valid instructions from a section of the program.
    ///
    /// @param programSection a section of the program.
    /// @return the list of valid instructions.
    private static List<String> getValidInstructions(String programSection)
    {
        List<String> instructions = new ArrayList<>();
        
        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)");
        Matcher matcher = pattern.matcher(programSection);
        
        while (matcher.find())
        {
            String instruction = matcher.group();
            instructions.add(instruction);
        }
        
        return instructions;
    }
    
    /// Calculates the product of a multiplication instruction.
    ///
    /// @param instruction a multiplication instruction.
    /// @return the product of the instruction.
    private static int getProduct(String instruction)
    {
        int product = 1;
        
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(instruction);
        
        while (matcher.find())
        {
            int factor = Integer.parseInt(matcher.group());
            product *= factor;
        }
        
        return product;
    }
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        return null;
    }
}
