package aoc.event.year2024.day01.historianHysteria;

import aoc.PuzzleSolver;

import java.util.*;

public class Solution implements PuzzleSolver
{
    /// Calculates the total distance between the numbers of the two lists.
    ///
    /// @param inputLines the puzzle input.
    /// @return the total distance between the numbers of the two lists.
    @Override
    public Object partOne(List<String> inputLines)
    {
        numberLists numberLists = getNumberLists(inputLines);
        
        Collections.sort(numberLists.firstList());
        Collections.sort(numberLists.secondList());
        
        return calculateTotalDistance(numberLists);
    }
    
    /// This record class stores the data for the two lists of numbers.
    ///
    /// @param firstList the first list of numbers.
    /// @param secondList the second list of numbers.
    private record numberLists(List<Integer> firstList, List<Integer> secondList) {}
    
    /// Parses the puzzle input for the numbers in each list.
    ///
    /// The puzzle input is parsed line by line, where the first number is placed in the first list and the second
    /// number is placed in the second list.
    ///
    /// @param inputLines the puzzle input.
    /// @return a record of the two number lists.
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
            
            firstList.add(numbers.getFirst());
            secondList.add(numbers.getLast());
        }
        
        return new numberLists(firstList, secondList);
    }
    
    /// Calculates the total distance between the numbers of the two lists.
    ///
    /// The total distance is calculated by comparing the values in each list from smallest to largest, finding the
    /// difference between them, and adding it to a running total.
    ///
    /// @param numberLists the two number lists (sorted).
    /// @return the total distance between the numbers of the two lists.
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
    
    /// Calculates the similarity score between the two lists.
    ///
    /// @param inputLines the puzzle input.
    /// @return the similarity score between the two lists.
    @Override
    public Object partTwo(List<String> inputLines)
    {
        numberLists numberLists = getNumberLists(inputLines);
        
        HashMap<Integer, Integer> secondListNumberFrequency = getSecondNumberListFrequency(numberLists);
        
        return calculateSimilarityScore(numberLists, secondListNumberFrequency);
    }
    
    /// Calculates a frequency map of the numbers in the second list.
    ///
    /// @param numberLists the two lists of numbers.
    /// @return a frequency map of the numbers in the second list.
    private static HashMap<Integer, Integer> getSecondNumberListFrequency(numberLists numberLists)
    {
        HashMap<Integer, Integer> secondListNumberFrequency = new HashMap<>();
        
        for (int number : numberLists.secondList())
        {
            int frequency = secondListNumberFrequency.getOrDefault(number, 0) + 1;
            secondListNumberFrequency.put(number, frequency);
        }
        
        return secondListNumberFrequency;
    }
    
    /// Calculates the similarity score between the two lists.
    ///
    /// To calculate the similarity score between the two lists, the numbers in one list are multiplied by their
    /// frequency in the other list and added to a running total.
    ///
    /// Here each number in the first list is multiplied by its frequency in the second list, but the calculation
    /// could have been done the other way around, where each number in the second list is multiplied by its
    /// frequency in the first.
    ///
    /// @param numberLists the two lists of numbers.
    /// @param secondListNumberFrequency a frequency map of the numbers in the second list.
    /// @return the similarity score between the two lists.
    private static int calculateSimilarityScore(
            numberLists numberLists,
            HashMap<Integer, Integer> secondListNumberFrequency)
    {
        int similarityScore = 0;
        
        for (int number : numberLists.firstList())
        {
            similarityScore += number * secondListNumberFrequency.getOrDefault(number, 0);
        }
        
        return similarityScore;
    }
}
