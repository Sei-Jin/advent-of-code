package aoc.event.year2024.day05.printQueue;

import aoc.Runner;
import aoc.Solver;

import java.util.*;
import java.util.stream.Collectors;

public class Solution implements Solver {
    
    private final HashMap<Integer, HashSet<Integer>> orderingRules;
    private final List<List<Integer>> updates;
    
    public Solution(String input) {
        final var lines = input.lines().toList();
        int emptyLineIndex = getEmptyLineIndex(lines);
        
        List<String> orderingRulesInput = lines.subList(0, emptyLineIndex);
        List<String> updatesInput = lines.subList(emptyLineIndex + 1, lines.size());
        
        orderingRules = getOrderingRules(orderingRulesInput);
        updates = getUpdates(updatesInput);
    }
    
    /// Calculates the sum of the middle page numbers for valid updates.
    ///
    /// @return the sum of the middle page numbers for valid updates.
    @Override
    public Object partOne() {
        List<List<Integer>> validUpdates = selectUpdates(updates, orderingRules, true);
        return calculateMiddlePageNumberSum(validUpdates);
    }
    
    /// Calculates the sum of the middle page numbers for the corrected invalid updates.
    ///
    /// @return the sum of the middle page numbers for the corrected invalid updates.
    @Override
    public Object partTwo() {
        List<List<Integer>> invalidUpdates = selectUpdates(updates, orderingRules, false);
        correctPageNumberOrder(invalidUpdates, orderingRules);
        return calculateMiddlePageNumberSum(invalidUpdates);
    }
    
    /// Finds the index of the first empty line in the puzzle input if it exists.
    ///
    /// @param inputLines the puzzle input.
    /// @return the index of the first empty line in the puzzle input.
    /// @throws IllegalArgumentException if the puzzle input does not contain any empty lines.
    private static int getEmptyLineIndex(List<String> inputLines) {
        for (int index = 0; index < inputLines.size(); index++) {
            if (inputLines.get(index).isEmpty()) {
                return index;
            }
        }
        
        throw new IllegalArgumentException("The puzzle input does not contain any empty lines.");
    }
    
    /// Parses the first section of the puzzle input for the ordering rules.
    ///
    /// @param orderingRulesInput the first section of the puzzle input, containing the ordering
    ///                           rules.
    /// @return the ordering rules.
    private static HashMap<Integer, HashSet<Integer>> getOrderingRules(
        List<String> orderingRulesInput) {
        HashMap<Integer, HashSet<Integer>> orderingRules = new HashMap<>();
        
        for (String rule : orderingRulesInput) {
            List<Integer> pageNumbers = Arrays.stream(rule.split("\\|"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
            
            int precedingNumber = pageNumbers.getFirst();
            int succeedingNumber = pageNumbers.getLast();
            
            if (orderingRules.containsKey(precedingNumber)) {
                orderingRules.get(precedingNumber).add(succeedingNumber);
            } else {
                HashSet<Integer> succeedingNumbers = new HashSet<>();
                succeedingNumbers.add(succeedingNumber);
                orderingRules.put(precedingNumber, succeedingNumbers);
            }
        }
        
        return orderingRules;
    }
    
    /// Parses the second section of the puzzle input for the list of updates.
    ///
    /// @param updatesInput the second section of the puzzle input, containing the list of updates.
    /// @return the list of updates.
    private static List<List<Integer>> getUpdates(List<String> updatesInput) {
        List<List<Integer>> updates = new ArrayList<>();
        
        for (String input : updatesInput) {
            List<Integer> update = Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
            
            updates.add(update);
        }
        
        return updates;
    }
    
    /// Creates a list of either all the valid, or all the invalid updates in `updates`.
    ///
    /// If `selectValidUpdates` is true then all valid updates in `updates` are added to the list.
    /// If false, invalid updates are added instead.
    ///
    /// @param updates            a list of updates.
    /// @param orderingRules      rules the updates should follow.
    /// @param selectValidUpdates determines if valid or invalid updates are selected.
    /// @return a list of either all the valid updates or all the invalid updates.
    private static List<List<Integer>> selectUpdates(
        List<List<Integer>> updates,
        HashMap<Integer, HashSet<Integer>> orderingRules,
        boolean selectValidUpdates) {
        List<List<Integer>> selectedUpdates = new ArrayList<>();
        
        for (List<Integer> update : updates) {
            boolean isValidUpdate = isValidUpdate(update, orderingRules);
            
            if (selectValidUpdates == isValidUpdate) {
                selectedUpdates.add(update);
            }
        }
        
        return selectedUpdates;
    }
    
    /// Determines if an update follows the ordering rules or not.
    ///
    /// An update is valid if its page numbers follow the ordering rules, and is invalid if its
    /// follow the ordering rules.
    ///
    /// @param update        an update. Each update contains a list of page numbers.
    /// @param orderingRules rules the ordering of the page numbers should follow.
    /// @return true if the update follows the ordering rules, or false otherwise.
    private static boolean isValidUpdate(
        List<Integer> update,
        HashMap<Integer, HashSet<Integer>> orderingRules) {
        HashSet<Integer> previousPageNumbers = new HashSet<>();
        
        for (int pageNumber : update) {
            if (orderingRules.containsKey(pageNumber)) {
                HashSet<Integer> intersection = new HashSet<>(orderingRules.get(pageNumber));
                intersection.retainAll(previousPageNumbers);
                
                if (!intersection.isEmpty()) {
                    return false;
                }
            }
            
            previousPageNumbers.add(pageNumber);
        }
        
        return true;
    }
    
    /// Sorts the page numbers in the updates according to the ordering rules.
    ///
    /// @param updates       a list of updates.
    /// @param orderingRules rules that the page numbers should be ordered by.
    private static void correctPageNumberOrder(
        List<List<Integer>> updates,
        HashMap<Integer, HashSet<Integer>> orderingRules) {
        Comparator<Integer> comparator = getComparator(orderingRules);
        
        for (List<Integer> update : updates) {
            update.sort(comparator);
        }
    }
    
    /// Creates a custom comparator that follows the ordering rules.
    ///
    /// @param orderingRules rules that the page numbers should be ordered by.
    /// @return a custom comparator that follows the ordering rules.
    private static Comparator<Integer> getComparator(HashMap<Integer, HashSet<Integer>> orderingRules) {
        return (predecessor, successor) ->
        {
            if (orderingRules.getOrDefault(predecessor, new HashSet<>()).contains(successor)) {
                return 1;
            } else if (predecessor.equals(successor)) {
                return 0;
            } else {
                return -1;
            }
        };
    }
    
    /// Calculates the sum of all the middle page numbers in the list of updates.
    ///
    /// @param updates a list of updates.
    /// @return the sum of all the middle page numbers in the list of updates.
    private static int calculateMiddlePageNumberSum(List<List<Integer>> updates) {
        int sumMiddlePageNumbers = 0;
        
        for (List<Integer> update : updates) {
            int middleIndex = update.size() / 2;
            sumMiddlePageNumbers += update.get(middleIndex);
        }
        
        return sumMiddlePageNumbers;
    }
    
    public static void main(String[] args) {
        Runner.runAndPrint(2024, 5);
    }
}
