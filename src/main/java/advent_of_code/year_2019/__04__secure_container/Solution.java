package advent_of_code.year_2019.__04__secure_container;

import advent_of_code.PuzzleSolver;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Solution implements PuzzleSolver
{
    @Override
    public Object partOne(List<String> inputLines)
    {
        List<Integer> rangeNumbers = Arrays.stream(inputLines
                .getFirst()
                .split("-"))
                .map(Integer::parseInt)
                .toList();
        
        int startNumber = rangeNumbers.getFirst();
        int endNumber = rangeNumbers.getLast();
        
        int validPasswords = 0;
        
        for (int number = startNumber; number <= endNumber; number++)
        {
            List<Integer> digits = getDigits(number);
            
            if (validPassword(digits))
            {
                validPasswords++;
            }
        }
        
        return validPasswords;
    }
    
    
    private List<Integer> getDigits(int number)
    {
        List<Integer> digits = new LinkedList<>();
        
        while (number > 0)
        {
            digits.addFirst(number % 10);
            number = number / 10;
        }
        
        return digits;
    }
    
    
    private static boolean validPassword(List<Integer> digits)
    {
        boolean containsDouble = false;
        
        for (int index = 0; index < digits.size() - 1; index++)
        {
            // Do the digits decrease from left to right?
            if (digits.get(index) > digits.get(index + 1))
            {
                return false;
            }
            
            if (Objects.equals(digits.get(index), digits.get(index + 1)))
            {
                containsDouble = true;
            }
        }
        
        return containsDouble;
    }
    
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        return null;
    }
}
