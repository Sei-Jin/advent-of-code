package aoc.event.year2024.day01.historianHysteria;

import aoc.PuzzleSolver;

import java.util.*;

public class Solution implements PuzzleSolver
{
    @Override
    public Object partOne(List<String> inputLines)
    {
        numberLists numberLists = getNumberLists(inputLines);
        
        Collections.sort(numberLists.firstList());
        Collections.sort(numberLists.secondList());
        
        return calculateTotalDistance(numberLists);
    }
    
    private static int calculateTotalDistance(numberLists numberLists)
    {
        int totalDistance = 0;
        
        for (int index = 0; index < numberLists.firstList().size(); index++)
        {
            int distance = Math.abs(numberLists.firstList().get(index) - numberLists.secondList().get(index));
            totalDistance += distance;
        }
        
        return totalDistance;
    }
    
    private static numberLists getNumberLists(List<String> inputLines)
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
        
        return new numberLists(firstList, secondList);
    }
    
    private record numberLists(List<Integer> firstList, List<Integer> secondList) {}
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        numberLists numberLists = getNumberLists(inputLines);
        
        HashMap<Integer, Integer> secondListNumberFrequency = new HashMap<>();
        
        for (int number : numberLists.secondList())
        {
            int frequency = secondListNumberFrequency.getOrDefault(number, 0) + 1;
            secondListNumberFrequency.put(number, frequency);
        }
        
        int similarityScore = 0;
        
        for (int number : numberLists.firstList())
        {
            similarityScore += number * secondListNumberFrequency.getOrDefault(number, 0);
        }
        
        return similarityScore;
    }
}
