package aoc.event.year2017.day01.inverseCaptcha;

import aoc.Runner;
import aoc.Solver;

import java.util.ArrayList;
import java.util.List;

public class Solution implements Solver {
    
    /// Stores the puzzle input as a list of digits.
    private List<Integer> INPUT = new ArrayList<>();
    
    /// Converts the puzzle input to a list of digits.
    ///
    /// The puzzle input is in the form `#####...`, where each `#` is a digit. A digit is any
    /// `Integer` in the range `0–9`.
    ///
    /// @param input the puzzle input.
    @Override
    public void parse(String input) {
        INPUT = input.chars()
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
        
        for (var index = 0; index < INPUT.size(); index++) {
            final var first = INPUT.get(index);
            final var second = INPUT.get((index + repetitionOffset) % INPUT.size());
            
            if (first.equals(second)) {
                sum += INPUT.get(index);
            }
        }
        
        return sum;
    }
    
    /// Calculates the sum of all digits that match the next digit in the list.
    ///
    /// Time Complexity: O(n)
    /// Space Complexity: O(n)
    ///
    /// @return the solution to the captcha.
    @Override
    public Integer partOne() {
        return sumRepeatingDigits(1);
    }
    
    /// Calculates the sum of all digits that have a matching digit halfway around the list.
    ///
    /// Time Complexity: O(n)
    /// Space Complexity: O(n)
    ///
    /// @return the solution to the new captcha.
    @Override
    public Integer partTwo() {
        return sumRepeatingDigits(INPUT.size() / 2);
    }
    
    /// Runs the solution.
    public static void main(String[] args) {
        Runner.runAndPrint(2017, 1);
    }
}
