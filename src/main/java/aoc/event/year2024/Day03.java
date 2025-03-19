package aoc.event.year2024;

import aoc.DeprecatedSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 implements DeprecatedSolver
{
    /// This enum stores the regular expression for each type of instruction.
    enum Instruction
    {
        MULTIPLY("mul\\(\\d+,\\d+\\)"),
        DO("do\\(\\)"),
        DO_NOT("don't\\(\\)");
        
        private final String regex;
        
        Instruction(String regex)
        {
            this.regex = regex;
        }
        
        public String getRegex()
        {
            return regex;
        }
    }
    
    /// Creates a pattern matcher for the given section of the program.
    ///
    /// The puzzle input represents a program with corrupted memory and contains a series of
    /// multiplication instructions to follow. A multiplication instruction is represented as
    /// `mul(X,Y)` and multiplies the two numbers `X` and `Y`.
    ///
    /// The pattern that is matched depends on if all multiplications are enabled or not. If not all
    /// multiplications are enabled then `do` and `don't` instructions should also be matched.
    ///
    /// @param programSection a section of the program.
    /// @param allMultiplicationsEnabled true if all multiplication instructions are
    ///         enabled, false if otherwise.
    /// @return a pattern matcher for the section of the program.
    private static Matcher createPatternMatcher(
            String programSection,
            boolean allMultiplicationsEnabled)
    {
        StringBuilder regex = new StringBuilder(Instruction.MULTIPLY.getRegex());
        
        if (!allMultiplicationsEnabled)
        {
            regex.append("|");
            regex.append(Instruction.DO_NOT.getRegex());
            regex.append("|");
            regex.append(Instruction.DO.getRegex());
        }
        
        Pattern pattern = Pattern.compile(regex.toString());
        return pattern.matcher(programSection);
    }
    
    /// Returns the list of valid instructions from a section of the program.
    ///
    /// A valid instruction is any instruction that is matched by the pattern matcher.
    ///
    /// @param patternMatcher a matcher for valid instructions.
    /// @return the list of valid instructions.
    private static List<String> parseValidInstructions(Matcher patternMatcher)
    {
        List<String> instructions = new ArrayList<>();
        
        while (patternMatcher.find())
        {
            String instruction = patternMatcher.group();
            instructions.add(instruction);
        }
        
        return instructions;
    }
    
    /// Calculates the product of a multiplication instruction.
    ///
    /// A multiplication instruction is in the form `mul(X,Y)`, where `X` and `Y` are the two
    /// numbers to be multiplied together.
    ///
    /// @param instruction a multiplication instruction.
    /// @return the product of the instruction.
    private static int calculateProduct(String instruction)
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
    
    /// Calculates the sum of all multiplication instructions in program memory.
    ///
    /// Time Complexity: O(n)
    /// - One pass is completed over the puzzle input to find and store the instructions. Then each
    /// instruction is executed sequentially.
    ///
    /// Space Complexity: O(n)
    /// - All instructions from the puzzle input are stored. If the entire puzzle input consists of
    /// instructions, then the entire puzzle input will be stored.
    ///
    /// @param programMemory the puzzle input.
    /// @return the total sum of all multiplication instructions.
    @Override
    public Object partOne(List<String> programMemory)
    {
        int sumOfProducts = 0;
        
        for (String programSection : programMemory)
        {
            Matcher patternMatcher = createPatternMatcher(programSection, true);
            List<String> instructions = parseValidInstructions(patternMatcher);
            
            for (String instruction : instructions)
            {
                sumOfProducts += calculateProduct(instruction);
            }
        }
        
        return sumOfProducts;
    }
    
    /// Calculates the sum of all ***enabled*** multiplication instructions in program memory.
    ///
    /// - The program starts with instructions enabled.
    /// - A `don't` instruction disables the following instructions.
    /// - A `do` instruction enables the following instructions.
    ///
    /// Time Complexity: O(n)
    /// - Same as part one.
    ///
    /// Space Complexity: O(n)
    /// - Same as part one.
    ///
    /// @param programMemory the puzzle input.
    /// @return the total sum of all enabled multiplication instructions.
    @Override
    public Object partTwo(List<String> programMemory)
    {
        int sumOfProducts = 0;
        boolean enableMultiplications = true;
        
        for (String programSection : programMemory)
        {
            Matcher patternMatcher = createPatternMatcher(programSection, false);
            List<String> instructions = parseValidInstructions(patternMatcher);
            
            for (String instruction : instructions)
            {
                if (instruction.matches(Instruction.DO_NOT.regex))
                {
                    enableMultiplications = false;
                }
                else if (instruction.matches(Instruction.DO.regex))
                {
                    enableMultiplications = true;
                }
                else if (enableMultiplications)
                {
                    sumOfProducts += calculateProduct(instruction);
                }
            }
        }
        
        return sumOfProducts;
    }
}
