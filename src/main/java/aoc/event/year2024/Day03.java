package aoc.event.year2024;

import aoc.Solver;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

/// # [2024-03: Mull It Over](https://adventofcode.com/2024/day/3)
public class Day03 implements Solver<Integer, Integer> {
    
    private static final Pattern inputPattern = Pattern.compile(
        Instruction.MULTIPLY.getRegex()
            + "|" + Instruction.DO.getRegex()
            + "|" + Instruction.DO_NOT.getRegex()
    );
    private static final Pattern numberPattern = Pattern.compile("\\d+");
    private final List<String> instructions;
    
    public Day03(String input) {
        instructions = parse(input);
    }
    
    private static List<String> parse(String input) {
        return inputPattern
            .matcher(input)
            .results()
            .map(MatchResult::group)
            .toList();
    }
    
    private static int calculateProduct(String instruction) {
        return numberPattern
            .matcher(instruction)
            .results()
            .mapToInt(result -> Integer.parseInt(result.group()))
            .reduce(1, (a, b) -> a * b);
    }
    
    @Override
    public Integer partOne() {
        return instructions
            .stream()
            .mapToInt(Day03::calculateProduct)
            .sum();
    }
    
    @Override
    public Integer partTwo() {
        var sum = 0;
        var enableMultiply = true;
        
        for (var instruction : instructions) {
            if (instruction.matches(Instruction.DO_NOT.getRegex())) {
                enableMultiply = false;
            } else if (instruction.matches(Instruction.DO.getRegex())) {
                enableMultiply = true;
            } else if (enableMultiply) {
                sum += calculateProduct(instruction);
            }
        }
        
        return sum;
    }
    
    private enum Instruction {
        MULTIPLY("mul\\(\\d+,\\d+\\)"),
        DO("do\\(\\)"),
        DO_NOT("don't\\(\\)");
        
        private final String regex;
        
        Instruction(String regex) {
            this.regex = regex;
        }
        
        public String getRegex() {
            return regex;
        }
    }
}
