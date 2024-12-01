package aoc.event.year2024.day01.historianHysteria;

import aoc.PuzzleSolver;

import java.util.*;

public class Solution implements PuzzleSolver
{
    @Override
    public Object partOne(List<String> inputLines)
    {
        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();
        
        for (String line : inputLines)
        {
            String allWhitespace = "\\s+";
            
            List<Integer> numbers = Arrays.stream(line.split(allWhitespace))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();
            
            int firstNumber = numbers.getFirst();
            int secondNumber = numbers.getLast();
            
            firstList.add(firstNumber);
            secondList.add(secondNumber);
        }
        
        Collections.sort(firstList);
        Collections.sort(secondList);
        
        int totalDistance = 0;
        
        for (int index = 0; index < firstList.size(); index++)
        {
            int distance = Math.abs(firstList.get(index) - secondList.get(index));
            totalDistance += distance;
        }
        
        return totalDistance;
    }
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        return null;
    }
}
