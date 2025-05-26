package aoc.event.year2022;

import aoc.DeprecatedSolver2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class Day05 implements DeprecatedSolver2 {
    
    private final Pattern MOVE_PATTERN = Pattern.compile(
            "move (\\d+) from (\\d+) to (\\d+)"
    );
    
    private final List<LinkedList<Character>> stacks;
    private final List<Move> procedure;
    
    /// Initializes the solution with the parsed puzzle data.
    ///
    /// The puzzle input is split into two sections:
    ///
    /// - The first section contains the data for the stacked crates.
    /// - The second section contains a series of steps on how the crates should be moved between
    ///  the stacks.
    ///
    /// @param input the puzzle input.
    public Day05(String input) {
        final var lines = input.lines().toList();
        
        final var emptyLineIndex = getEmptyLineIndex(lines);
        final var stacksInput = lines.subList(0, emptyLineIndex - 1);
        final var procedureInput = lines.subList(emptyLineIndex + 1, lines.size());
        
        this.stacks = parseStacks(stacksInput);
        this.procedure = parseProcedure(procedureInput);
    }
    
    private static int getEmptyLineIndex(List<String> inputLines) {
        for (var i = 0; i < inputLines.size(); i++) {
            if (inputLines.get(i).isEmpty()) {
                return i;
            }
        }
        
        throw new IllegalArgumentException("There are no empty lines in the puzzle input.");
    }
    
    /// Parses the stack input.
    ///
    /// The stacks are in the form:
    ///
    /// ```
    /// ........[F]
    /// [B].....[E]
    /// [A].[C].[D]
    ///```
    ///
    /// - Each `.` represents a space and should be ignored.
    /// - `[A]`, `[B]` and `[C]` are crates, where `[A]` and `[B]` are in the first stack, and `[C]`
    ///  is in the second stack.
    ///
    /// Only the characters between the square braces are added to each stack.
    ///
    /// @param stacksInput the input for the stacks.
    /// @return the parsed stack input.
    private static List<LinkedList<Character>> parseStacks(List<String> stacksInput) {
        final var stacks = new ArrayList<LinkedList<Character>>();
        
        for (var stackIndex = 1; stackIndex < stacksInput.getFirst().length(); stackIndex += 4) {
            final var crateCharacters = parseCrateCharacters(stacksInput, stackIndex);
            stacks.add(crateCharacters);
        }
        
        return stacks;
    }
    
    private static LinkedList<Character> parseCrateCharacters(
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
    
    /// Parses the procedure input.
    ///
    /// Each move is in the form `move X from A to B`, where:
    ///
    /// - `X` is the number of crates to be moved.
    /// - `A` is the stack where the crates are taken from.
    /// - `B` is the stack where the crates are moved to.
    ///
    /// @param procedureInput the procedure input.
    /// @return the parsed procedure input.
    private List<Move> parseProcedure(List<String> procedureInput) {
        final var procedure = new ArrayList<Move>();
        
        for (final var line : procedureInput) {
            final var matcher = MOVE_PATTERN.matcher(line);
            
            if (matcher.matches()) {
                final var cratesToMove = Integer.parseInt(matcher.group(1));
                final var fromStack = Integer.parseInt(matcher.group(2));
                final var toStack = Integer.parseInt(matcher.group(3));
                
                procedure.add(new Move(cratesToMove, fromStack, toStack));
            } else {
                throw new IllegalArgumentException(
                        "Invalid procedure format encountered: " + line
                );
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
    /// @return the crates left at the top of each stack concatenated together.
    @Override
    public String partOne() {
        final var stacksCopy = createDeepCopy(stacks);
        
        for (final var step : procedure) {
            for (var cratesMoved = 0; cratesMoved < step.amount; cratesMoved++) {
                final var character = stacksCopy.get(step.from - 1).removeLast();
                stacksCopy.get(step.to - 1).addLast(character);
            }
        }
        
        return concatenateTopCrates(stacksCopy);
    }
    
    /// Determines the crates left at the top of each stack after the crate-moving procedure has
    /// executed.
    ///
    /// @return the crates left at the top of each stack concatenated together.
    @Override
    public String partTwo() {
        final var stacksCopy = createDeepCopy(stacks);
        
        for (final var step : procedure) {
            final var temporary = new LinkedList<Character>();
            
            for (var cratesMoved = 0; cratesMoved < step.amount; cratesMoved++) {
                final var character = stacksCopy.get(step.from - 1).removeLast();
                temporary.addFirst(character);
            }
            
            for (final var character : temporary) {
                stacksCopy.get(step.to - 1).addLast(character);
            }
        }
        
        return concatenateTopCrates(stacksCopy);
    }
    
    private String concatenateTopCrates(List<LinkedList<Character>> stacks) {
        final var topCrates = new StringBuilder();
        
        for (final var stack : stacks) {
            topCrates.append(stack.getLast());
        }
        
        return topCrates.toString();
    }
    
    record Move(int amount, int from, int to) {}
}
