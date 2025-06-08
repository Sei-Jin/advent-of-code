package aoc.event.year2017;

import aoc.Solver;

import java.util.List;
import java.util.stream.IntStream;

/// # [2017-01: Inverse Captcha](https://adventofcode.com/2017/day/1)
public class Day01 implements Solver<Integer, Integer> {
    
    private final List<Integer> digits;
    
    public Day01(String input) {
        digits = parse(input);
    }
    
    private List<Integer> parse(String input) {
        return input
            .chars()
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
        return IntStream
            .range(0, digits.size())
            .filter(i -> {
                var first = digits.get(i);
                var second = digits.get((i + repetitionOffset) % digits.size());
                return first.equals(second);
            })
            .map(digits::get)
            .sum();
    }
    
    /// Calculates the sum of all digits that match the next digit in the list.
    @Override
    public Integer partOne() {
        return sumRepeatingDigits(1);
    }
    
    /// Calculates the sum of all digits that have a matching digit halfway around the list.
    @Override
    public Integer partTwo() {
        return sumRepeatingDigits(digits.size() / 2);
    }
}
