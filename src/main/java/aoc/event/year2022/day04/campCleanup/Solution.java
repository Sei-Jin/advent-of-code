package aoc.event.year2022.day04.campCleanup;

import aoc.PuzzleSolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution implements PuzzleSolver
{
    @Override
    public Object partOne(List<String> inputLines)
    {
        int fullyContainedPairs = 0;
        
        for (String line : inputLines)
        {
            List<Pair> pairs = getPairs(line);
            boolean fullyContainedPair = isFullyContainedPair(pairs);
            
            if (fullyContainedPair)
            {
                fullyContainedPairs++;
            }
        }
        
        return fullyContainedPairs;
    }
    
    record Pair(int startingSection, int endingSection) {}
    
    private static List<Pair> getPairs(String line)
    {
        List<Integer> inputNumbers = Arrays.stream(line.split("[-,]"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
        
        List<Pair> pairs = new ArrayList<>();
        
        for (int index = 0; index < inputNumbers.size(); index += 2)
        {
            pairs.add(new Pair(inputNumbers.get(index), inputNumbers.get(index + 1)));
        }
        
        return pairs;
    }
    
    private static boolean isFullyContainedPair(List<Pair> pairs)
    {
        Pair firstPair = pairs.getFirst();
        Pair secondPair = pairs.getLast();
        
        boolean firstContainsSecond = (
                firstPair.startingSection <= secondPair.startingSection &&
                firstPair.endingSection >= secondPair.endingSection
        );
        
        boolean secondContainsFirst = (
                secondPair.startingSection <= firstPair.startingSection &&
                secondPair.endingSection >= firstPair.endingSection
        );
        
        return (firstContainsSecond || secondContainsFirst);
    }
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        return null;
    }
}
