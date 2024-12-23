package aoc.event.year2024.day01.historianHysteria;

import aoc.PuzzleSolver;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution implements PuzzleSolver
{
    /// Calculates the total distance between the numbers of the two lists.
    ///
    /// @param inputLines the puzzle input.
    /// @return the total distance between the numbers of the two lists.
    @Override
    public Object partOne(List<String> inputLines)
    {
        NumberLists numberLists = getNumberLists(inputLines);
        
        Collections.sort(numberLists.firstList());
        Collections.sort(numberLists.secondList());
        
        return calculateTotalDistance(numberLists);
    }
    
    /// Calculates the similarity score between the two lists.
    ///
    /// @param inputLines the puzzle input.
    /// @return the similarity score between the two lists.
    @Override
    public Object partTwo(List<String> inputLines)
    {
        NumberLists numberLists = getNumberLists(inputLines);
        
        HashMap<Integer, Integer> secondListNumberFrequency = getFrequencies(
                numberLists);
        
        return calculateSimilarityScore(numberLists, secondListNumberFrequency);
    }
    
    /// This record class stores the data for the two lists of numbers.
    ///
    /// @param firstList the first list of numbers.
    /// @param secondList the second list of numbers.
    private record NumberLists(List<Integer> firstList, List<Integer> secondList) {}
    
    /// Parses the puzzle input for the numbers in each list.
    ///
    /// The puzzle input is in the form:
    ///
    /// ```
    /// A...B
    /// A...B
    /// A...B
    /// ```
    ///
    /// Where:
    /// - Each `.` represents a space and should be ignored.
    /// - Each `A` represents a number in the first list.
    /// - Each `B` represents a number in the second list.
    ///
    /// The puzzle input is parsed line by line, where the first number is placed in the first list
    /// and the second number is placed in the second list. Also note that the numbers can be longer
    /// than a single digit.
    ///
    /// @param inputLines the puzzle input.
    /// @return a record of the two number lists.
    private static NumberLists getNumberLists(List<String> inputLines)
    {
        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();
        
        String regex = "(\\d+)\\s+(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        
        for (String line : inputLines)
        {
            Matcher matcher = pattern.matcher(line);
            
            if (matcher.find())
            {
                firstList.add(Integer.valueOf(matcher.group(1)));
                secondList.add(Integer.valueOf(matcher.group(2)));
            }
            else
            {
                throw new IllegalArgumentException(
                        "Encountered unexpected format in puzzle input on line: %s".formatted(line)
                );
            }
        }
        
        return new NumberLists(firstList, secondList);
    }
    
    /// Calculates a frequency map of the numbers in the second list.
    ///
    /// @param numberLists the two lists of numbers.
    /// @return a frequency map of the numbers in the second list.
    private static HashMap<Integer, Integer> getFrequencies(NumberLists numberLists)
    {
        HashMap<Integer, Integer> secondListNumberFrequency = new HashMap<>();
        
        for (int number : numberLists.secondList())
        {
            int frequency = secondListNumberFrequency.getOrDefault(number, 0) + 1;
            secondListNumberFrequency.put(number, frequency);
        }
        
        return secondListNumberFrequency;
    }
    
    /// Calculates the total distance between the numbers of the two lists.
    ///
    /// The total distance is calculated by comparing the values in each list from smallest to
    /// largest, finding the difference between them, and adding it to a running total.
    ///
    /// @param numberLists the two number lists (sorted).
    /// @return the total distance between the numbers of the two lists.
    private static int calculateTotalDistance(NumberLists numberLists)
    {
        int totalDistance = 0;
        
        for (int index = 0; index < numberLists.firstList().size(); index++)
        {
            int firstNumber = numberLists.firstList().get(index);
            int secondNumber = numberLists.secondList().get(index);
            
            int distance = Math.abs(firstNumber - secondNumber);
            totalDistance += distance;
        }
        
        return totalDistance;
    }
    
    /// Calculates the similarity score between the two lists.
    ///
    /// To calculate the similarity score between the two lists, the numbers in one list are
    /// multiplied by their frequency in the other list and added to a running total.
    ///
    /// Here each number in the first list is multiplied by its frequency in the second list, but
    /// the calculation could have been done the other way around, where each number in the second
    /// list is multiplied by its frequency in the first.
    ///
    /// @param numberLists the two lists of numbers.
    /// @param secondListNumberFrequency a frequency map of the numbers in the second list.
    /// @return the similarity score between the two lists.
    private static int calculateSimilarityScore(
            NumberLists numberLists,
            HashMap<Integer, Integer> secondListNumberFrequency)
    {
        int similarityScore = 0;
        
        for (int number : numberLists.firstList())
        {
            int frequency = secondListNumberFrequency.getOrDefault(number, 0);
            similarityScore += number * frequency;
        }
        
        return similarityScore;
    }
}
