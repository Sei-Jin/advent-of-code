package aoc.event.year2022.day01.calorieCounting;

import aoc.DeprecatedSolver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution implements DeprecatedSolver
{
    /// Parses the puzzle input for the list of lists of calories.
    ///
    /// The puzzle input represents a list food items that are carried by elves. The value for
    /// each item is the amount of calories that item contains. The group of items carried by
    /// each elf are separated by blank lines.
    ///
    /// The puzzle input is in the form:
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
    /// Here there are three lists of calories:
    /// - The first list contains three items with calorie values of `6000`, `3000`, and `1000`.
    /// - The second list contains a single item with a calorie value of `2000`.
    /// - The third list contains two items with calorie values of `5000` and `5000`.
    ///
    ///
    /// @param puzzleInput the puzzle input.
    /// @return a list containing the total calories of the items carried by each elf.
    private static List<List<Integer>> parseCaloriesLists(List<String> puzzleInput)
    {
        List<List<Integer>> calorieLists = new ArrayList<>();
        calorieLists.add(new ArrayList<>());
        
        for (String line : puzzleInput)
        {
            if (line.isEmpty())
            {
                calorieLists.add(new ArrayList<>());
            }
            else
            {
                int calories = Integer.parseInt(line);
                calorieLists.getLast().add(calories);
            }
        }
        
        return calorieLists;
    }
    
    /// Creates a new list of the sums of each calorie list.
    ///
    /// @param calorieLists the lists of calories
    /// @return a list of the sums of each calorie list.
    private static List<Integer> calculateCalorieSums(List<List<Integer>> calorieLists)
    {
        List<Integer> calorieSums = new ArrayList<>();
        
        for (List<Integer> list : calorieLists)
        {
            int calorieSum = 0;
            
            for (int calories : list)
            {
                calorieSum += calories;
            }
            
            calorieSums.add(calorieSum);
        }
        
        return calorieSums;
    }
    
    /// Calculates the maximum calories carried by an elf.
    ///
    /// Time Complexity: `O(n)`
    /// - `n` is the number of lines in the puzzle input.
    ///
    /// Space Complexity: `O(n)`
    /// - `n` is the number of lines in the puzzle input.
    ///
    /// @param puzzleInput the puzzle input.
    /// @return the maximum calories carried by an elf.
    @Override
    public Object partOne(List<String> puzzleInput)
    {
        List<List<Integer>> calorieLists = parseCaloriesLists(puzzleInput);
        List<Integer> calorieSums = calculateCalorieSums(calorieLists);
        
        return calorieSums.stream()
                .reduce(Integer::max)
                .orElse(0);
    }
    
    /// Calculates the sum of the calories from the three elves with the highest total calories.
    ///
    /// Time Complexity: `O(m log m)`
    /// - `m` is the number of calorie sums.
    /// - The list of calorie sums is sorted.
    ///
    /// Space Complexity: `O(n)`
    /// - `n` is the number of lines in the puzzle input.
    ///
    /// @param puzzleInput the puzzle input.
    /// @return the sum of the calories from the three elves with the highest total calories.
    @Override
    public Object partTwo(List<String> puzzleInput)
    {
        List<List<Integer>> calorieLists = parseCaloriesLists(puzzleInput);
        List<Integer> calorieSums = calculateCalorieSums(calorieLists);
        
        return calorieSums.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum();
    }
}
