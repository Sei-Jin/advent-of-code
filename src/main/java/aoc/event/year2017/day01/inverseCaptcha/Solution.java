package aoc.event.year2017.day01.inverseCaptcha;

import aoc.PuzzleSolver;

import java.util.List;

public class Solution implements PuzzleSolver
{
    /// Calculates the sum of all digits that match the next digit in the list.
    ///
    /// The list is "circular", so the digit after the last digit is the first digit.
    ///
    /// - Time Complexity: O(n)
    ///     - One comparison is done for each of the digits in the input array.
    ///
    /// - Space Complexity: O(n)
    ///     - The puzzle input is converted and stored as a list of digits.
    ///
    /// @param inputLines the puzzle input.
    /// @return the solution to the captcha.
    @Override
    public Object partOne(List<String> inputLines)
    {
        List<Integer> digits = getDigits(inputLines);
        int repetitionOffset = 1;
        
        return sumRepeatingDigits(digits, repetitionOffset);
    }
    
    /// Converts the puzzle input to a `List` of digits.
    ///
    /// The puzzle input is in the form `#####...`, where each `#` is a digit.
    /// A digit is any `Integer` in the range `0–9`.
    ///
    /// @param inputLines the puzzle input.
    /// @return the puzzle input as a list of digits.
    private static List<Integer> getDigits(List<String> inputLines)
    {
        String inputLine = inputLines.getFirst();
        
        return inputLine
                .chars()
                .map(Character::getNumericValue)
                .boxed()
                .toList();
    }
    
    /// Calculates the sum of the digits that repeat at a given offset value, `repetitionOffset`.
    ///
    /// @param digits the puzzle input as a list of digits.
    /// @param repetitionOffset the offset between the indices of the digits that are compared.
    /// @return the sum of the digits that have matching pairs at their offset index.
    private static int sumRepeatingDigits(List<Integer> digits, int repetitionOffset)
    {
        int repeatingDigitsSum = 0;
        
        for (int index = 0; index < digits.size(); index++)
        {
            if (digits.get(index).equals(digits.get((index + repetitionOffset) % digits.size())))
            {
                repeatingDigitsSum += digits.get(index);
            }
        }
        
        return repeatingDigitsSum;
    }
    
    /// Calculates the sum of all digits that have a matching digit halfway around the list.
    ///
    /// - Time Complexity: O(n)
    ///     - Same as part one.
    ///
    /// - Space Complexity: O(n)
    ///     - Same as part one.
    ///
    /// @param inputLines the puzzle input.
    /// @return the solution to the new captcha.
    @Override
    public Object partTwo(List<String> inputLines)
    {
        List<Integer> digits = getDigits(inputLines);
        int repetitionOffset = digits.size() / 2;
        
        return sumRepeatingDigits(digits, repetitionOffset);
    }
}
