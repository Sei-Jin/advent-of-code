package aoc.event.year2022.day05.supplyStacks;

import aoc.PuzzleSolver;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution implements PuzzleSolver
{
    /// Determines the crates left at the top of each stack after the crate-moving procedure has executed.
    ///
    /// The puzzle input is split into two sections:
    ///
    /// - The first section contains the data for the stacked crates.
    /// - The second section contains the data for the crate-moving procedure. The crate-moving procedure
    ///   is a series of steps on how the crates should be moved between the stacks.
    ///
    /// - Time Complexity: O(n)
    ///     - All methods are done in linear time.
    ///
    /// - Space Complexity: O(n)
    ///     - The data for the stacks and the procedure steps are parsed and stored before the procedure is
    ///       executed. The data for both sections scale linearly with the puzzle input.
    ///     - Space required could be reduced by combining the procedure parsing and execution steps. Only one
    ///       step in the procedure would need to be stored at a time if each step was executed before the next
    ///       step was parsed. This would avoid storing all the procedure steps ahead of time, but would come with
    ///       the downside of combining the two methods into one.
    ///
    /// @param inputLines the puzzle input.
    /// @return the crates at the top of each stack concatenated together after the crate-moving procedure
    ///         has executed.
    @Override
    public Object partOne(List<String> inputLines)
    {
        int emptyLineIndex = getEmptyLineIndex(inputLines);
        List<String> stacksInput = inputLines.subList(0, emptyLineIndex - 1);
        List<String> procedureInput = inputLines.subList(emptyLineIndex + 1, inputLines.size());
        
        List<LinkedList<Character>> stacks = getStacks(stacksInput);
        List<Step> procedure = getProcedure(procedureInput);
        
        executeProcedureOneCrateAtATime(stacks, procedure);
        return getTopCrates(stacks);
    }
    
    /// Finds the index of the first empty line in the puzzle.
    ///
    /// @param inputLines the puzzle input.
    /// @return the index of the first empty line in the puzzle input.
    /// @throws IllegalArgumentException if there are no empty lines present.
    private static int getEmptyLineIndex(List<String> inputLines)
    {
        for (int index = 0; index < inputLines.size(); index++)
        {
            if (inputLines.get(index).isEmpty())
            {
                return index;
            }
        }
        
        throw new IllegalArgumentException("There are no empty lines in the puzzle input.");
    }
    
    /// Parses the puzzle input for the stacks of crates.
    ///
    /// The stacks are in the form:
    ///
    /// ```
    ///         [F]
    /// [B]     [E]
    /// [A] [C] [D]
    ///  #   #   #
    /// ```
    ///
    /// Where:
    ///
    /// - `[A]`, `[B]` and `[C]` are crates.
    ///     - `[A]` and `[B]` are in the first stack.
    ///     - `[C]` is in the second stack.
    /// - `#` is the id of a stack.
    ///
    /// The ids of the stacks are ignored and not included in the given `stacksInput`. The id values start
    /// at 1 and increases by 1 each time from left to right, therefore these values are predictable and
    /// unnecessary to parse.
    ///
    /// For the crates, only the characters between the square braces are added to each stack.
    ///
    /// @param stacksInput the first part of the puzzle input, containing the input data for the stacks.
    /// @return the stacks of crates.
    private static List<LinkedList<Character>> getStacks(List<String> stacksInput)
    {
        List<LinkedList<Character>> stacks = new ArrayList<>();
        
        for (int stackIndex = 1; stackIndex < stacksInput.getFirst().length(); stackIndex += 4)
        {
            LinkedList<Character> crateCharacters = getCrateCharacters(stacksInput, stackIndex);
            stacks.add(crateCharacters);
        }
        
        return stacks;
    }
    
    /// Parses the puzzle input for a single stack of crates.
    ///
    /// The stack is parsed from bottom to top for the characters in-between the square braces.
    ///
    /// @param stacksInput the first part of the puzzle input, containing the input data for the stacks of crates.
    /// @param stackIndex the index of the stack of crates.
    /// @return a stack of crates.
    private static LinkedList<Character> getCrateCharacters(List<String> stacksInput, int stackIndex)
    {
        LinkedList<Character> crateCharacters = new LinkedList<>();
        
        for (int index = stacksInput.size() - 1; index >= 0; index--)
        {
            char crateCharacter = stacksInput.get(index).charAt(stackIndex);
            
            if (!Character.isWhitespace(crateCharacter))
            {
                crateCharacters.add(crateCharacter);
            }
            else
            {
                break;
            }
        }
        
        return crateCharacters;
    }
    
    /// This record holds the data for each step in the crate-moving procedure.
    record Step(int cratesToMove, int fromStack, int toStack) {}
    
    /// Parses the puzzle input for the list of steps in the crate-moving procedure.
    ///
    /// Each step is in the form `move X from A to B`, where:
    ///
    /// - X is the number of crates to be moved.
    /// - A is the stack where the crates are taken from.
    /// - B is the stack where the crates are moved to.
    ///
    /// @param procedureInput the second part of the puzzle input, containing the input data for the stacks.
    /// @return the steps in the crate-moving procedure.
    private List<Step> getProcedure(List<String> procedureInput)
    {
        List<Step> procedure = new ArrayList<>();
        
        String regex = "move (\\d+) from (\\d+) to (\\d+)";
        Pattern pattern = Pattern.compile(regex);
        
        for (String line : procedureInput)
        {
            Matcher matcher = pattern.matcher(line);
            
            if (matcher.matches())
            {
                int cratesToMove = Integer.parseInt(matcher.group(1));
                int fromStack = Integer.parseInt(matcher.group(2));
                int toStack = Integer.parseInt(matcher.group(3));
                
                procedure.add(new Step(cratesToMove, fromStack, toStack));
            }
            else
            {
                throw new IllegalArgumentException("Invalid procedure format encountered: " + line);
            }
        }
        
        return procedure;
    }
    
    /// Executes the crate-moving procedure, moving the crates between the stacks one at a time.
    ///
    /// @param stacks the stacks of crates.
    /// @param procedure the crate-moving procedure.
    private void executeProcedureOneCrateAtATime(List<LinkedList<Character>> stacks, List<Step> procedure)
    {
        for (Step step : procedure)
        {
            for (int cratesMoved = 0; cratesMoved < step.cratesToMove; cratesMoved++)
            {
                char character = stacks.get(step.fromStack - 1).removeLast();
                stacks.get(step.toStack - 1).addLast(character);
            }
        }
    }
    
    /// Concatenates the crates at the top of each stack.
    ///
    /// @param stacks the stacks of crates.
    /// @return the crates at the top of each stack, concatenated together as a string.
    private String getTopCrates(List<LinkedList<Character>> stacks)
    {
        StringBuilder topCrates = new StringBuilder();
        
        for (LinkedList<Character> stack : stacks)
        {
            topCrates.append(stack.getLast());
        }
        
        return topCrates.toString();
    }
    
    /// Determines the crates left at the top of each stack after the crate-moving procedure has executed.
    ///
    /// - Time Complexity: O(n)
    ///     - Same as part one.
    ///
    /// - Space Complexity: O(n)
    ///     - Same as part one.
    ///
    /// @param inputLines the puzzle input.
    /// @return the crates at the top of each stack concatenated together after the crate-moving procedure
    ///         has executed.
    @Override
    public Object partTwo(List<String> inputLines)
    {
        int emptyLineIndex = getEmptyLineIndex(inputLines);
        List<String> stacksInput = inputLines.subList(0, emptyLineIndex - 1);
        List<String> procedureInput = inputLines.subList(emptyLineIndex + 1, inputLines.size());
        
        List<LinkedList<Character>> stacks = getStacks(stacksInput);
        List<Step> procedure = getProcedure(procedureInput);
        
        executeProcedureAllCratesAtOnce(stacks, procedure);
        return getTopCrates(stacks);
    }
    
    /// Executes the crate-moving procedure, moving the crates between the stacks all at once.
    ///
    /// @param stacks the stacks of crates.
    /// @param procedure the crate-moving procedure.
    private void executeProcedureAllCratesAtOnce(List<LinkedList<Character>> stacks, List<Step> procedure)
    {
        for (Step step : procedure)
        {
            List<Character> temporary = new LinkedList<>();
            
            for (int cratesMoved = 0; cratesMoved < step.cratesToMove; cratesMoved++)
            {
                char character = stacks.get(step.fromStack - 1).removeLast();
                temporary.addFirst(character);
            }
            
            for (char character : temporary)
            {
                stacks.get(step.toStack - 1).addLast(character);
            }
        }
    }
}
