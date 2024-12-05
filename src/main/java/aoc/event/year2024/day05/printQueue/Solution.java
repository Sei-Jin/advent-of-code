package aoc.event.year2024.day05.printQueue;

import aoc.PuzzleSolver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Solution implements PuzzleSolver
{
    @Override
    public Object partOne(List<String> inputLines)
    {
        int emptyLineIndex = getEmptyLineIndex(inputLines);
        
        List<String> orderingRulesInput = inputLines.subList(0, emptyLineIndex);
        List<String> pageNumberInput = inputLines.subList(emptyLineIndex + 1, inputLines.size());
        
        HashMap<Integer, HashSet<Integer>> orderingRules = getOrderingRules(orderingRulesInput);
        
        int sumValidUpdateMiddlePageNumbers = 0;
        
        for (String input : pageNumberInput)
        {
            List<Integer> pageNumbers = Arrays.stream(input.split(","))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();
            
            HashSet<Integer> previousPageNumbers = new HashSet<>();
            
            if (isValidUpdate(pageNumbers, orderingRules, previousPageNumbers))
            {
                int middleIndex = pageNumbers.size() / 2;
                sumValidUpdateMiddlePageNumbers += pageNumbers.get(middleIndex);
            }
        }
        
        return sumValidUpdateMiddlePageNumbers;
    }
    
    private static int getEmptyLineIndex(List<String> inputLines)
    {
        for (int index = 0; index < inputLines.size(); index++)
        {
            if (inputLines.get(index).isEmpty())
            {
                return index;
            }
        }
        
        return 0;
    }
    
    private static HashMap<Integer, HashSet<Integer>> getOrderingRules(List<String> orderingRulesInput)
    {
        HashMap<Integer, HashSet<Integer>> orderingRules = new HashMap<>();
        
        for (String rule : orderingRulesInput)
        {
            List<Integer> pageNumbers = Arrays.stream(rule.split("\\|"))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();
            
            int precedingNumber = pageNumbers.getFirst();
            int succeedingNumber = pageNumbers.getLast();
            
            if (orderingRules.containsKey(precedingNumber))
            {
                orderingRules.get(precedingNumber).add(succeedingNumber);
            }
            else
            {
                HashSet<Integer> succeedingNumbers = new HashSet<>();
                succeedingNumbers.add(succeedingNumber);
                orderingRules.put(precedingNumber, succeedingNumbers);
            }
        }
        
        return orderingRules;
    }
    
    private static boolean isValidUpdate(
            List<Integer> pageNumbers,
            HashMap<Integer, HashSet<Integer>> orderingRules,
            HashSet<Integer> previousPageNumbers
    )
    {
        boolean isValidUpdate = true;
        
        for (int pageNumber : pageNumbers)
        {
            if (orderingRules.containsKey(pageNumber))
            {
                HashSet<Integer> intersection = new HashSet<>(orderingRules.get(pageNumber));
                intersection.retainAll(previousPageNumbers);
                
                if (!intersection.isEmpty())
                {
                    isValidUpdate = false;
                }
            }
            
            previousPageNumbers.add(pageNumber);
        }
        
        return isValidUpdate;
    }
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        return null;
    }
}
