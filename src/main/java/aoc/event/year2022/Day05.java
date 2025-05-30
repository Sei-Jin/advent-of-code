package aoc.event.year2022;

import aoc.Solver;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/// # [2022-05: Supply Stacks](https://adventofcode.com/2022/day/5)
public class Day05 implements Solver<String, String> {
    
    private static final Pattern MOVE_PATTERN = Pattern.compile(
        "move (\\d+) from (\\d+) to (\\d+)"
    );
    
    private final List<Deque<Character>> stacks;
    private final List<Move> procedure;
    
    public Day05(String input) {
        var lines = input.lines().toList();
        
        var emptyLineIndex = IntStream.range(0, lines.size())
            .filter(i -> lines.get(i).isEmpty())
            .findFirst()
            .orElseThrow();
        
        var stacksInput = lines.subList(0, emptyLineIndex - 1);
        var procedureInput = lines.subList(emptyLineIndex + 1, lines.size());
        
        this.stacks = parseStacks(stacksInput);
        this.procedure = parseProcedure(procedureInput);
    }
    
    private static List<Deque<Character>> parseStacks(List<String> input) {
        var stacks = new ArrayList<Deque<Character>>();
        for (var i = 1; i < input.getFirst().length(); i += 4) {
            var stack = parseStack(input, i);
            stacks.add(stack);
        }
        return stacks;
    }
    
    private static Deque<Character> parseStack(List<String> input, int i) {
        var stack = new ArrayDeque<Character>();
        for (var j = input.size() - 1; j >= 0; j--) {
            var character = input.get(j).charAt(i);
            if (!Character.isWhitespace(character)) {
                stack.add(character);
            }
        }
        return stack;
    }
    
    private static List<Move> parseProcedure(List<String> input) {
        return input
            .stream()
            .map(MOVE_PATTERN::matcher)
            .filter(Matcher::matches)
            .map(matcher -> {
                var cratesToMove = Integer.parseInt(matcher.group(1));
                var fromStack = Integer.parseInt(matcher.group(2)) - 1;
                var toStack = Integer.parseInt(matcher.group(3)) - 1;
                return new Move(cratesToMove, fromStack, toStack);
            })
            .toList();
    }
    
    private List<Deque<Character>> createDeepCopy(List<Deque<Character>> stacks) {
        var stacksCopy = new ArrayList<Deque<Character>>();
        for (var stack : stacks) {
            stacksCopy.add(new ArrayDeque<>(stack));
        }
        return stacksCopy;
    }
    
    /// Determines the crates left at the top of each stack after moving the crates between the
    /// stacks one at a time.
    @Override
    public String partOne() {
        var stacksCopy = createDeepCopy(stacks);
        move(stacksCopy, procedure);
        return stackTops(stacksCopy);
    }
    
    private static void move(List<Deque<Character>> stacks, List<Move> procedure) {
        for (var step : procedure) {
            var from = stacks.get(step.fromStack());
            var to = stacks.get(step.toStack());
            
            for (var i = 0; i < step.cratesToMove(); i++) {
                var character = from.removeLast();
                to.addLast(character);
            }
        }
    }
    
    /// Determines the crates left at the top of each stack after moving the crates between the
    /// stacks in groups.
    @Override
    public String partTwo() {
        var stacksCopy = createDeepCopy(stacks);
        moveInGroups(stacksCopy, procedure);
        return stackTops(stacksCopy);
    }
    
    private static void moveInGroups(List<Deque<Character>> stacks, List<Move> procedure) {
        for (var step : procedure) {
            var from = stacks.get(step.fromStack());
            var to = stacks.get(step.toStack());
            
            var temp = new ArrayDeque<Character>();
            for (var i = 0; i < step.cratesToMove(); i++) {
                var character = from.removeLast();
                temp.addFirst(character);
            }
            for (var character : temp) {
                to.addLast(character);
            }
        }
    }
    
    private static String stackTops(List<Deque<Character>> stacks) {
        var stackTops = new StringBuilder();
        for (var stack : stacks) {
            stackTops.append(stack.getLast());
        }
        return stackTops.toString();
    }
    
    private record Move(int cratesToMove, int fromStack, int toStack) {}
}
