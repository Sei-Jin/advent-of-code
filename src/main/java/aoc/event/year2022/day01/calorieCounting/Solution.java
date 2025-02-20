package aoc.event.year2022.day01.calorieCounting;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Solution implements Solver {
    
    /// Stores the sublist sums.
    private final List<Integer> sublistSums;
    
    /// Initializes the solution.
    public Solution(String input) {
        final var lists = Collections.unmodifiableList(parse(input));
        sublistSums = Collections.unmodifiableList(calculateSums(lists));
    }
    
    /// Parses the puzzle input for the list of lists of numbers.
    ///
    /// An example puzzle input format is:
    ///
    /// ```
    /// 6000
    /// 3000
    /// 1000
    /// ....
    /// 2000
    /// ....
    /// 5000
    /// 5000
    /// ```
    ///
    /// Here there are three sub-lists:
    /// - The first sub-list contains three items with values of `6000`, `3000`, and `1000`.
    /// - The second sub-list contains a single item with a value of `2000`.
    /// - The third sub-list contains two items with values of `5000` and `5000`.
    ///
    /// @param input the puzzle input.
    /// @return a list of sub-lists of values.
    private static List<List<Integer>> parse(String input) {
        var lists = new ArrayList<List<Integer>>();
        lists.add(new ArrayList<>());
        
        input.lines().forEach(line -> {
            if (line.isEmpty()) {
                lists.add(new ArrayList<>());
            } else {
                lists.getLast().add(Integer.parseInt(line));
            }
        });
        
        return lists;
    }
    
    /// Calculates the sum of each sublist.
    ///
    /// @param lists the input sub-lists.
    /// @return a list of the sums of each sublist.
    private static List<Integer> calculateSums(List<List<Integer>> lists) {
        final var sums = new ArrayList<Integer>();
        
        for (final var list : lists) {
            var sum = 0;
            
            for (final var number : list) {
                sum += number;
            }
            
            sums.add(sum);
        }
        
        return sums;
    }
    
    /// Calculates the max sublist sum.
    ///
    /// - Time Complexity: `O(n)` - `n` is the number of lines in the puzzle input.
    /// - Space Complexity: `O(n)` - `n` is the number of lines in the puzzle input.
    ///
    /// @return the max sublist sum.
    @Override
    public Integer partOne() {
        return sublistSums.stream()
                .reduce(0, Integer::max);
    }
    
    /// Calculates the sum of the three largest sublist sums.
    ///
    /// - Time Complexity: `O(m log m)` - `m` is the number of sublist sums.
    /// - Space Complexity: `O(n)` - `n` is the number of lines in the puzzle input.
    ///
    /// @return the sum of the three largest sublist sums.
    @Override
    public Integer partTwo() {
        return sublistSums.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum();
    }
    
    /// Runs the solution.
    public static void main(String[] args) {
        Runner.runAndPrint(2022, 1);
    }
}
