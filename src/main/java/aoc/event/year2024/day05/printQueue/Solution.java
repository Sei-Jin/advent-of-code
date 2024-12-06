package aoc.event.year2024.day05.printQueue;

import aoc.PuzzleSolver;

import java.util.*;

public class Solution implements PuzzleSolver
{
    @Override
    public Object partOne(List<String> inputLines)
    {
        int emptyLineIndex = getEmptyLineIndex(inputLines);
        
        List<String> orderingRulesInput = inputLines.subList(0, emptyLineIndex);
        List<String> updatesInput = inputLines.subList(emptyLineIndex + 1, inputLines.size());
        
        HashMap<Integer, HashSet<Integer>> orderingRules = getOrderingRules(orderingRulesInput);
        List<List<Integer>> updates = getUpdates(updatesInput);
        
        List<List<Integer>> validUpdates = selectUpdates(updates, orderingRules, true);
        return calculateMiddlePageNumberSum(validUpdates);
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
        
        return -1;
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
    
    private static List<List<Integer>> getUpdates(List<String> pageNumberInput)
    {
        List<List<Integer>> updates = new ArrayList<>();
        
        for (String input : pageNumberInput)
        {
            List<Integer> update = Arrays.stream(input.split(","))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .toList();
            
            updates.add(update);
        }
        
        return updates;
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
    
    private static List<List<Integer>> selectUpdates(
            List<List<Integer>> updates,
            HashMap<Integer, HashSet<Integer>> orderingRules,
            boolean selectValidUpdates
    )
    {
        List<List<Integer>> selectedUpdates = new ArrayList<>();
        
        for (List<Integer> update : updates)
        {
            HashSet<Integer> previousPageNumbers = new HashSet<>();
            
            if (selectValidUpdates)
            {
                if (isValidUpdate(update, orderingRules, previousPageNumbers))
                {
                    selectedUpdates.add(update);
                }
            }
            else
            {
                if (!isValidUpdate(update, orderingRules, previousPageNumbers))
                {
                    selectedUpdates.add(update);
                }
            }
        }
        
        return selectedUpdates;
    }
    
    private static int calculateMiddlePageNumberSum(List<List<Integer>> validUpdates)
    {
        int sumMiddlePageNumbers = 0;
        
        for (List<Integer> update : validUpdates)
        {
            int middleIndex = update.size() / 2;
            sumMiddlePageNumbers += update.get(middleIndex);
        }
        
        return sumMiddlePageNumbers;
    }
    
    @Override
    public Object partTwo(List<String> inputLines)
    {
        int emptyLineIndex = getEmptyLineIndex(inputLines);
        
        List<String> orderingRulesInput = inputLines.subList(0, emptyLineIndex);
        List<String> updatesInput = inputLines.subList(emptyLineIndex + 1, inputLines.size());
        
        HashMap<Integer, HashSet<Integer>> orderingRules = getOrderingRules(orderingRulesInput);
        List<List<Integer>> updates = getUpdates(updatesInput);
        
        List<List<Integer>> invalidUpdates = selectUpdates(updates, orderingRules, false);
        return calculateMiddlePageNumberSum(invalidUpdates);
    }
}
