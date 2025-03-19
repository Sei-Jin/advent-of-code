package aoc.event.year2017;

import aoc.Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class Day02 implements Solver {
    
    /// Compiles a pattern that matches integers.
    private static final Pattern PATTERN = Pattern.compile("(\\d+)");
    
    /// Stores the puzzle input as a list of lists of numbers.
    private final List<List<Integer>> numberLists;
    
    /// Initializes the solution.
    ///
    /// @param input the puzzle input.
    public Day02(String input) {
        this.numberLists = Collections.unmodifiableList(parse(input));
    }
    
    /// Parses the solution input for a list of number lists.
    ///
    /// Each line of the puzzle input is in the form: `###### ...`. Where each `#`
    /// represents an integer.
    ///
    /// @param input the puzzle input.
    /// @return a list of number lists.
    private List<List<Integer>> parse(String input) {
        final var numberLists = new ArrayList<List<Integer>>();
        
        input.lines().forEach(line -> {
            var numberList = PATTERN.matcher(line)
                    .results()
                    .mapToInt(match -> Integer.parseInt(match.group()))
                    .boxed()
                    .toList();
            
            numberLists.add(numberList);
        });
        
        return numberLists;
    }
    
    /// Calculates the sum of the maximum difference for all rows.
    ///
    /// @return the sum of the maximum differences for all rows.
    @Override
    public Integer partOne() {
        var sum = 0;
        
        for (final var list : numberLists) {
            sum += findMaximumDifference(list);
        }
        
        return sum;
    }
    
    /// Calculates the maximum difference between any two numbers in the given list.
    ///
    /// The maximum difference is the difference between the smallest and largest numbers in the
    /// list.
    ///
    /// @param list a list of numbers.
    /// @return the maximum difference between any two numbers in the list.
    private static int findMaximumDifference(List<Integer> list) {
        var min = list.getFirst();
        var max = list.getFirst();
        
        for (final var number : list) {
            if (min > number) {
                min = number;
            }
            
            if (max < number) {
                max = number;
            }
        }
        
        return max - min;
    }
    
    /// Calculates the total of the divisible quotients from all rows.
    ///
    /// @return the sum of the of divisible quotients.
    @Override
    public Integer partTwo() {
        var sum = 0;
        
        for (final var list : numberLists) {
            sum += findDivisibleQuotient(list);
        }
        
        return sum;
    }
    
    /// Finds the only two numbers in the list that are divisible, and returns their quotient.
    ///
    /// Numbers are divisible if and only if they divide evenly without leaving a remainder.
    ///
    /// @param list a list of numbers.
    /// @return the quotient of the only two divisible list.
    private static int findDivisibleQuotient(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i == j) {
                    continue;
                }
                
                boolean isDivisible = ((list.get(i) % list.get(j)) == 0);
                
                if (isDivisible) {
                    return list.get(i) / list.get(j);
                }
            }
        }
        
        throw new IllegalArgumentException("No divisible numbers were found");
    }
}
