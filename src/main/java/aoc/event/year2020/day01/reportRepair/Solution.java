package aoc.event.year2020.day01.reportRepair;

import aoc.Solver;
import aoc.Runner;

import java.util.Collections;
import java.util.List;

public class Solution implements Solver {
    
    private final List<Integer> numbers;
    
    public Solution(String input) {
        numbers = Collections.unmodifiableList(parse(input));
    }
    
    private static List<Integer> parse(String input) {
        return input.lines()
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }
    
    @Override
    public Integer partOne() {
        for (var i = 0; i < numbers.size(); i++) {
            for (var j = 0; j < numbers.size(); j++) {
                if (i == j) {
                    continue;
                }
                
                final var sum = numbers.get(i) + numbers.get(j);
                
                if (sum == 2020) {
                    return numbers.get(i) * numbers.get(j);
                }
            }
        }
        
        return 0;
    }
    
    @Override
    public Integer partTwo() {
        for (var i = 0; i < numbers.size(); i++) {
            for (var j = 0; j < numbers.size(); j++) {
                for (var k = 0; k < numbers.size(); k++) {
                    if (i == j || j == k || k == i) {
                        continue;
                    }
                    
                    final var sum = numbers.get(i) + numbers.get(j) + numbers.get(k);
                    
                    if (sum == 2020) {
                        return numbers.get(i) * numbers.get(j) * numbers.get(k);
                    }
                }
            }
        }
        
        return 0;
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2020, 1);
    }
}
