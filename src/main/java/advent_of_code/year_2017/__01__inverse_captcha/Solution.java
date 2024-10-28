package advent_of_code.year_2017.__01__inverse_captcha;

import advent_of_code.PuzzleSolver;

import java.util.List;

/**
 * <p>--- Day 1: Inverse Captcha ---
 *
 * <p>The goal is to solve a captcha. To solve a captcha implement the following rules:
 * <ul>
 *     <li>{@link Solution#partOne}: Calculate the sum of all the digits in the input that have a matching digit
 *     immediately after.</li>
 *     <li>{@link Solution#partTwo}: Calculate the sum of all the digits in the input that have a matching digit
 *     halfway around the list.</li>
 * </ul>
 */
public class Solution implements PuzzleSolver
{
    /**
     * Calculates the sum of all the digits in the input that have a matching digit immediately after.
     * The list is "circular", so the digit after the last digit is the first digit.
     *
     * @param inputLines the puzzle input.
     * @return the solution to the captcha.
     */
    @Override
    public Object partOne(List<String> inputLines)
    {
        List<Integer> digits = getDigits(inputLines);
        
        int repetitionOffset = 1;
        
        return sumRepeatingDigits(digits, repetitionOffset);
    }
    
    
    /**
     * Converts the first {@code String} in the puzzle input to a {@code List} of digits. A digit is an
     * {@code Integer} that ranges from {@code 0–9}.
     *
     * @param inputLines the puzzle input.
     * @return the first line of the puzzle input as a {@code List} of digits.
     */
    private static List<Integer> getDigits(List<String> inputLines)
    {
        String inputLine = inputLines.getFirst();
        
        return inputLine.chars()
                .map(Character::getNumericValue)
                .boxed()
                .toList();
    }
    
    
    /**
     * Calculates the sum of the digits that repeat at a given offset value, {@code repetitionOffset}.
     *
     * @param digits a {@code List} of digits ({@code Integers} that range from {@code 0–9}).
     * @param repetitionOffset the offset between the indices of the digits that should be compared.
     * @return the sum of the digits that have matching pairs at their offset index.
     */
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
    
    
    /**
     * Calculates the sum of all the digits in the input that have a matching digit halfway around the list.
     * The list is "circular", so the digit after the last digit is the first digit.
     *
     * @param inputLines the puzzle input.
     * @return the solution to the new captcha.
     */
    @Override
    public Object partTwo(List<String> inputLines)
    {
        List<Integer> digits = getDigits(inputLines);
        
        int repetitionOffset = digits.size() / 2;
        
        return sumRepeatingDigits(digits, repetitionOffset);
    }
}
