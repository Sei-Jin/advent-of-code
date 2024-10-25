package advent_of_code.year2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * --- Day 1: Calorie Counting ---
 */
public class Day01
{
    public static void main(String[] args) throws IOException
    {
        List<String> inputLines = Files.readAllLines(Path.of("input.txt"));
        
        partOne(inputLines);
        partTwo(inputLines);
    }
    
    
    private static void partOne(List<String> inputLines)
    {
        List<Integer> elfCalories = getElfCalories(inputLines);
        
        Collections.sort(elfCalories);
        
        int mostCalories = elfCalories.getLast();
        
        System.out.println("The largest amount of calories carried by an elf is: " + mostCalories);
    }
    
    
    private static void partTwo(List<String> inputLines)
    {
        List<Integer> elfCalories = getElfCalories(inputLines);
        
        Collections.sort(elfCalories);
        
        int topThreeCalorieTotal = elfCalories.subList(elfCalories.size() - 3, elfCalories.size())
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        
        System.out.println("The total calories carried by the elves with" +
                " the the three largest amounts of calories is: " + topThreeCalorieTotal);
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
}
