package aoc.event.year2017.day02.corruptionChecksum;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/// --- Day 2: Corruption Checksum ---
public class Solution implements Solver {
    
    private static final List<List<Integer>> INPUT = new ArrayList<>();
    private static final Pattern NUMBER_PATTERN = Pattern.compile("(\\d+)");
    
    @Override
    public void parse(String input) {
        input.lines()
                .forEach(line -> {
                    final var numbers = NUMBER_PATTERN.matcher(line)
                            .results()
                            .mapToInt(match -> Integer.parseInt(match.group()))
                            .boxed()
                            .toList();
                    
                    INPUT.add(numbers);
                });
    }
    
    /// @return the checksum for the spreadsheet.
    @Override
    public Integer partOne() {
        return INPUT.stream()
                .mapToInt(Solution::findDifference)
                .sum();
    }
    
    private static int findDifference(List<Integer> numbers) {
        var min = numbers.getFirst();
        var max = numbers.getFirst();
        
        for (final var number : numbers) {
            if (min > number) {
                min = number;
            }
            
            if (max < number) {
                max = number;
            }
        }
        
        return max - min;
    }
    
    /// @return the sum of each row's divisible result.
    @Override
    public Integer partTwo() {
        return INPUT.stream()
                .mapToInt(Solution::findDivisible)
                .sum();
    }
    
    private static int findDivisible(List<Integer> numbers) {
        for (int outer = 0; outer < numbers.size(); outer++) {
            for (int inner = 0; inner < numbers.size(); inner++) {
                if (outer == inner) {
                    continue;
                }
                
                boolean isDivisible = ((numbers.get(outer) % numbers.get(inner)) == 0);
                
                if (isDivisible) {
                    return numbers.get(outer) / numbers.get(inner);
                }
            }
        }
        
        throw new IllegalArgumentException("No divisible numbers were found");
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2017, 2);
    }
}
