package aoc.event.year2022.day05.supplyStacks;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class Solution implements Solver {
    
    private final Pattern MOVE_PATTERN = Pattern.compile(
            "move (\\d+) from (\\d+) to (\\d+)"
    );
    
    private final List<LinkedList<Character>> stacks;
    private final List<Step> procedure;
    
    public Solution(String input) {
        final var lines = input.lines().toList();
        
        final var emptyLineIndex = getEmptyLineIndex(lines);
        final var stacksInput = lines.subList(0, emptyLineIndex - 1);
        final var procedureInput = lines.subList(emptyLineIndex + 1, lines.size());
        
        this.stacks = parseStacks(stacksInput);
        this.procedure = parseProcedure(procedureInput);
    }
    
    /// Finds the index of the first empty line in the puzzle.
    ///
    /// @param inputLines the puzzle input.
    /// @return the index of the first empty line in the puzzle input.
    /// @throws IllegalArgumentException if there are no empty lines present.
    private static int getEmptyLineIndex(List<String> inputLines) {
        for (var i = 0; i < inputLines.size(); i++) {
            if (inputLines.get(i).isEmpty()) {
                return i;
            }
        }
        
        throw new IllegalArgumentException("There are no empty lines in the puzzle input.");
    }
    
    /// Parses the puzzle input for the stacks of crates.
    ///
    /// The stacks are in the form:
    ///
    /// ```
    /// ...........[F]...
    /// ...[B].....[E]...
    /// ...[A].[C].[D]...
    /// ....#...#...#....
    ///```
    ///
    /// Where:
    ///
    /// - Each `.` represents a space and should be ignored.
    /// - `[A]`, `[B]` and `[C]` are crates.
    ///   - `[A]` and `[B]` are in the first stack.
    ///   - `[C]` is in the second stack.
    /// - `#` is the id of a stack.
    ///
    /// The ids of the stacks are ignored and not included in `stacksInput`. The id values start
    /// at 1 and increases by 1 each time from left to right, therefore these values are
    /// predictable and unnecessary to parse. For the crates, only the characters between the
    /// square braces are added to each stack.
    ///
    /// @param stacksInput the first part of the puzzle input, containing the input data for
    ///                            the stacks.
    /// @return the stacks of crates.
    private static List<LinkedList<Character>> parseStacks(List<String> stacksInput) {
        final var stacks = new ArrayList<LinkedList<Character>>();
        
        for (var stackIndex = 1; stackIndex < stacksInput.getFirst().length(); stackIndex += 4) {
            final var crateCharacters = getCrateCharacters(stacksInput, stackIndex);
            stacks.add(crateCharacters);
        }
        
        return stacks;
    }
    
    /// Parses the puzzle input for a single stack of crates.
    ///
    /// The stack is parsed from bottom to top for the characters in-between the square braces.
    ///
    /// @param stacksInput the first part of the puzzle input, containing the input data for
    ///                            the stacks of crates.
    /// @param stackIndex  the index of the stack of crates.
    /// @return a stack of crates.
    private static LinkedList<Character> getCrateCharacters(
            List<String> stacksInput,
            int stackIndex) {
        final var crateCharacters = new LinkedList<Character>();
        
        for (var index = stacksInput.size() - 1; index >= 0; index--) {
            final var crateCharacter = stacksInput.get(index).charAt(stackIndex);
            
            if (!Character.isWhitespace(crateCharacter)) {
                crateCharacters.add(crateCharacter);
            } else {
                break;
            }
        }
        
        return crateCharacters;
    }
    
    /// Parses the puzzle input for the list of steps in the crate-moving procedure.
    ///
    /// Each step is in the form `move X from A to B`, where:
    ///
    /// - `X` is the number of crates to be moved.
    /// - `A` is the stack where the crates are taken from.
    /// - `B` is the stack where the crates are moved to.
    ///
    /// @param procedureInput the second part of the puzzle input, containing the input data
    ///                               for the stacks.
    /// @return the steps in the crate-moving procedure.
    private List<Step> parseProcedure(List<String> procedureInput) {
        final var procedure = new ArrayList<Step>();
        
        for (final var line : procedureInput) {
            final var matcher = MOVE_PATTERN.matcher(line);
            
            if (matcher.matches()) {
                final var cratesToMove = Integer.parseInt(matcher.group(1));
                final var fromStack = Integer.parseInt(matcher.group(2));
                final var toStack = Integer.parseInt(matcher.group(3));
                
                procedure.add(new Step(cratesToMove, fromStack, toStack));
            } else {
                throw new IllegalArgumentException("Invalid procedure format encountered: " + line);
            }
        }
        
        return procedure;
    }
    
    private List<LinkedList<Character>> createDeepCopy(List<LinkedList<Character>> stacks) {
        final var stacksCopy = new ArrayList<LinkedList<Character>>();
        
        for (final var stack : stacks) {
            stacksCopy.add(new LinkedList<>(stack));
        }
        
        return stacksCopy;
    }
    
    /// Determines the crates left at the top of each stack after the crate-moving procedure has
    /// executed.
    ///
    /// The puzzle input is split into two sections:
    ///
    /// - The first section contains the data for the stacked crates.
    /// - The second section contains the data for the crate-moving procedure. The crate-moving
    /// procedure is a series of steps on how the crates should be moved between the stacks.
    ///
    /// @return the crates at the top of each stack concatenated together after the crate-moving
    ///         procedure has executed.
    @Override
    public Object partOne() {
        final var stacksCopy = createDeepCopy(stacks);
        
        for (final var step : procedure) {
            for (var cratesMoved = 0; cratesMoved < step.cratesToMove; cratesMoved++) {
                final var character = stacksCopy.get(step.fromStack - 1).removeLast();
                stacksCopy.get(step.toStack - 1).addLast(character);
            }
        }
        
        return getTopCrates(stacksCopy);
    }
    
    /// Determines the crates left at the top of each stack after the crate-moving procedure has
    /// executed.
    ///
    /// @return the crates at the top of each stack concatenated together after the crate-moving
    ///         procedure has executed.
    @Override
    public Object partTwo() {
        final var stacksCopy = createDeepCopy(stacks);
        
        for (final var step : procedure) {
            final var temporary = new LinkedList<Character>();
            
            for (var cratesMoved = 0; cratesMoved < step.cratesToMove; cratesMoved++) {
                final var character = stacksCopy.get(step.fromStack - 1).removeLast();
                temporary.addFirst(character);
            }
            
            for (final var character : temporary) {
                stacksCopy.get(step.toStack - 1).addLast(character);
            }
        }
        
        return getTopCrates(stacksCopy);
    }
    
    /// Concatenates the crates at the top of each stack.
    ///
    /// @param stacks the stacks of crates.
    /// @return the crates at the top of each stack, concatenated together as a string.
    private String getTopCrates(List<LinkedList<Character>> stacks) {
        final var topCrates = new StringBuilder();
        
        for (final var stack : stacks) {
            topCrates.append(stack.getLast());
        }
        
        return topCrates.toString();
    }
    
    /// Stores the data for each step in the crate-moving procedure.
    record Step(int cratesToMove, int fromStack, int toStack) {}
    
    public static void main(String[] args) {
        Runner.runAndPrint(2022, 5);
    }
}
