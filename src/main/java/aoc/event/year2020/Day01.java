package aoc.event.year2020;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Day01 implements Solver {
    
    private static final int TARGET_SUM = 2020;
    
    private final List<Integer> numbers;
    
    /// Initializes the solution with the parsed puzzle input.
    ///
    /// The puzzle input contains one number on each line.
    public Day01(String input) {
        numbers = Collections.unmodifiableList(parse(input));
    }
    
    private static List<Integer> parse(String input) {
        return input.lines()
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }
    
    /// Calculates the product of the two numbers that sum to the target value.
    @Override
    public Integer partOne() {
        final var twoTuple = twoSum(numbers);
        return twoTuple[0] * twoTuple[1];
    }
    
    /// Runs in `O(n)` time using `O(n)` space
    private static int[] twoSum(List<Integer> numbers) {
        final var targetDifferences = new HashMap<Integer, Integer>();
        
        for (final var number : numbers) {
            if (!targetDifferences.containsKey(number)) {
                final var difference = TARGET_SUM - number;
                targetDifferences.put(difference, number);
            } else {
                return new int[]{number, targetDifferences.get(number)};
            }
        }
        
        throw new IllegalArgumentException(
                "There were no two numbers with a sum equal to the target value."
        );
    }
    
    /// Calculates the product of the three numbers that sum to the target value.
    @Override
    public Integer partTwo() {
        final var threeTuple = threeSum(new ArrayList<>(numbers));
        return threeTuple[0] * threeTuple[1] * threeTuple[2];
    }
    
    /// Runs in `O(n^2)` time using `O(n)` space
    private int[] threeSum(List<Integer> numbers) {
        numbers.sort(Integer::compareTo);
        
        for (var current = 0; current < numbers.size() - 2; current++)
        {
            var left = current + 1;
            var right = numbers.size() - 1;
            
            while (left < right)
            {
                final var sum = numbers.get(current) + numbers.get(left) + numbers.get(right);
                
                if (sum > TARGET_SUM) {
                    right--;
                } else if (sum < TARGET_SUM) {
                    left++;
                } else {
                    return new int[]{numbers.get(current), numbers.get(left), numbers.get(right)};
                }
            }
        }
        
        throw new IllegalArgumentException(
                "There were no three numbers with a sum equal to the target value."
        );
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2020, 1);
    }
}
