package advent_of_code.year_2022.__01__calorie_counting;

import advent_of_code.PuzzleSolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * --- Day 1: Calorie Counting ---
 */
public class Solution implements PuzzleSolver
{
    /**
     * @param inputLines the puzzle input.
     * @return the largest amount of calories carried by an elf.
     */
    @Override
    public Object partOne(List<String> inputLines)
    {
        List<Integer> elfCalories = getElfCalories(inputLines);
        
        Collections.sort(elfCalories);
        
        return elfCalories.getLast();
    }
    
    
    private static List<Integer> getElfCalories(List<String> inputLines)
    {
        List<Integer> elfCalories = new ArrayList<>();
        
        int totalCalories = 0;
        
        for (int lineNumber = 0; lineNumber < inputLines.size(); lineNumber++)
        {
            String line = inputLines.get(lineNumber);
            
            if (line.isEmpty())
            {
                elfCalories.add(totalCalories);
                
                totalCalories = 0;
            }
            else if (lineNumber == inputLines.size() - 1)
            {
                int calories = Integer.parseInt(line);
                
                totalCalories += calories;
                
                elfCalories.add(totalCalories);
            }
            else
            {
                int calories = Integer.parseInt(line);
                
                totalCalories += calories;
            }
        }
        
        return elfCalories;
    }
    
    
    /**
     * @param inputLines the puzzle input.
     * @return the total calories carried by the elves with the three largest amounts of calories.
     */
    @Override
    public Object partTwo(List<String> inputLines)
    {
        List<Integer> elfCalories = getElfCalories(inputLines);
        
        Collections.sort(elfCalories);
        
        return elfCalories.subList(elfCalories.size() - 3, elfCalories.size())
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
