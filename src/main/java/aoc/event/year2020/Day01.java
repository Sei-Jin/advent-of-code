package aoc.event.year2020;

import aoc.Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/// # [2020-01: Report Repair](https://adventofcode.com/2020/day/1)
public class Day01 implements Solver<Integer, Integer> {
    
    private static final int TARGET_SUM = 2020;
    private final List<Integer> numbers;
    
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
    ///
    /// Runs in `O(n)` time using `O(n)` space
    @Override
    public Integer partOne() {
        var twoTuple = twoSum(numbers);
        return twoTuple[0] * twoTuple[1];
    }
    
    private static int[] twoSum(List<Integer> numbers) {
        var differences = new HashMap<Integer, Integer>();
        for (var number : numbers) {
            if (!differences.containsKey(number)) {
                var difference = TARGET_SUM - number;
                differences.put(difference, number);
            }
            else {
                return new int[]{number, differences.get(number)};
            }
        }
        throw new IllegalArgumentException(
            "There were no two numbers with a sum equal to the target value."
        );
    }
    
    /// Calculates the product of the three numbers that sum to the target value.
    ///
    /// Runs in `O(n^2)` time using `O(n)` space
    @Override
    public Integer partTwo() {
        var threeTuple = threeSum(new ArrayList<>(numbers));
        return threeTuple[0] * threeTuple[1] * threeTuple[2];
    }
    
    private int[] threeSum(List<Integer> numbers) {
        numbers.sort(Integer::compareTo);
        for (var i = 0; i < numbers.size() - 2; i++) {
            var left = i + 1;
            var right = numbers.size() - 1;
            while (left < right) {
                var sum = numbers.get(i) + numbers.get(left) + numbers.get(right);
                if (sum > TARGET_SUM) {
                    right--;
                }
                else if (sum < TARGET_SUM) {
                    left++;
                }
                else {
                    return new int[]{numbers.get(i), numbers.get(left), numbers.get(right)};
                }
            }
        }
        throw new IllegalArgumentException(
            "There were no three numbers with a sum equal to the target value."
        );
    }
}
