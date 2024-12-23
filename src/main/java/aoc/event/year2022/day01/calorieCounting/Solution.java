package aoc.event.year2022.day01.calorieCounting;

import aoc.PuzzleSolver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution implements PuzzleSolver
{
    /// Calculates the maximum calories carried by an elf.
    ///
    /// The puzzle input represents a list food items that are carried by elves. The value for each item is the
    /// amount of calories that item contains. The group of items carried by each elf are separated by blank lines.
    ///
    /// - Time Complexity: O(n)
    ///     - A single pass is done over the puzzle input.
    ///
    /// - Space Complexity: O(n)
    ///     - One total is stored per elf, therefore at most one value is stored per two lines of input.
    ///
    /// @param inputLines the puzzle input.
    /// @return the maximum calories carried by an elf.
    @Override
    public Object partOne(List<String> inputLines)
    {
        return getTotalCaloriesCarriedPerElf(inputLines)
                .stream()
                .reduce(Integer::max)
                .orElse(0);
    }
    
    /// Calculates the sum of the calories from the three elves with the highest total calories.
    ///
    /// - Time Complexity: O(n log n)
    ///     - The list of calories carried per elf is sorted.
    ///
    /// - Space Complexity: O(n)
    ///     - One total is stored per elf, therefore at most one value is stored per two lines of input.
    ///
    /// @param inputLines the puzzle input.
    /// @return the sum of the calories from the three elves with the highest total calories.
    @Override
    public Object partTwo(List<String> inputLines)
    {
        List<Integer> totalCaloriesCarriedPerElf = getTotalCaloriesCarriedPerElf(inputLines);
        
        totalCaloriesCarriedPerElf.sort(Comparator.reverseOrder());
        
        return totalCaloriesCarriedPerElf
                .stream()
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum();
    }
    
    /// Calculates and returns the total calories of the items carried by each elf.
    ///
    /// @param inputLines the puzzle input.
    /// @return a list containing the total calories of the items carried by each elf.
    private static List<Integer> getTotalCaloriesCarriedPerElf(List<String> inputLines)
    {
        List<Integer> totalCaloriesCarriedPerElf = new ArrayList<>();
        int elfCaloriesSum = 0;
        
        for (String line : inputLines)
        {
            if (line.isEmpty())
            {
                totalCaloriesCarriedPerElf.add(elfCaloriesSum);
                elfCaloriesSum = 0;
            }
            else
            {
                elfCaloriesSum += Integer.parseInt(line);
            }
        }
        
        totalCaloriesCarriedPerElf.add(elfCaloriesSum);
        
        return totalCaloriesCarriedPerElf;
    }
}
