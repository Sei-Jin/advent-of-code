package aoc.event.year2017;

import aoc.Runner;
import aoc.Solver;

import java.util.Collections;
import java.util.List;

public class Day01 implements Solver {
    
    /// Stores the puzzle input as a list of digits.
    private final List<Integer> digits;
    
    /// Initializes the solution.
    ///
    /// @param input the puzzle input.
    public Day01(String input) {
        this.digits = Collections.unmodifiableList(parse(input));
    }
    
    /// Parses the puzzle input as a list of digits.
    ///
    /// The puzzle input is in the form `#####...`, where each `#` is a digit. A digit is any
    /// `Integer` in the range `0–9`.
    ///
    /// @param input the puzzle input.
    /// @return a list of digits.
    private List<Integer> parse(String input) {
        return input.chars()
                .map(Character::getNumericValue)
                .boxed()
                .toList();
    }
    
    /// Calculates the sum of the digits that repeat at a given offset value, `repetitionOffset`.
    ///
    /// The list is "circular", so the digit after the last digit is the first digit.
    ///
    /// @param repetitionOffset the offset between the indices of the digits that are compared.
    /// @return the sum of the digits that have matching pairs at their offset index.
    private int sumRepeatingDigits(int repetitionOffset) {
        var sum = 0;
        
        for (var index = 0; index < digits.size(); index++) {
            final var first = digits.get(index);
            final var second = digits.get((index + repetitionOffset) % digits.size());
            
            if (first.equals(second)) {
                sum += digits.get(index);
            }
        }
        
        return sum;
    }
    
    /// Calculates the sum of all digits that match the next digit in the list.
    ///
    /// - Time Complexity: O(n)
    /// - Space Complexity: O(n)
    ///
    /// @return the solution to the captcha.
    @Override
    public Integer partOne() {
        return sumRepeatingDigits(1);
    }
    
    /// Calculates the sum of all digits that have a matching digit halfway around the list.
    ///
    /// - Time Complexity: O(n)
    /// - Space Complexity: O(n)
    ///
    /// @return the solution to the new captcha.
    @Override
    public Integer partTwo() {
        return sumRepeatingDigits(digits.size() / 2);
    }
    
    /// Runs the solution.
    public static void main(String[] args) {
        Runner.runAndPrint(2017, 1);
    }
}
