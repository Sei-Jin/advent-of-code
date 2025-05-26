package aoc.event.year2024;

import aoc.Solver;

import java.util.*;
import java.util.stream.Collectors;

public class Day05 implements Solver {
    
    private final Map<Integer, Set<Integer>> orderingRules;
    private final List<List<Integer>> updates;
    
    public Day05(String input) {
        var lines = input.lines().toList();
        var emptyLineIndex = getEmptyLineIndex(lines);
        
        var orderingRulesInput = lines.subList(0, emptyLineIndex);
        var updatesInput = lines.subList(emptyLineIndex + 1, lines.size());
        
        orderingRules = parseOrderingRules(orderingRulesInput);
        updates = parseUpdates(updatesInput);
    }
    
    /// Finds the index of the first empty line in the puzzle input if it exists.
    ///
    /// @param inputLines the puzzle input.
    /// @return the index of the first empty line in the puzzle input.
    /// @throws IllegalArgumentException if the puzzle input does not contain any empty lines.
    private static int getEmptyLineIndex(List<String> inputLines) {
        for (var index = 0; index < inputLines.size(); index++) {
            if (inputLines.get(index).isEmpty()) {
                return index;
            }
        }
        
        throw new IllegalArgumentException("The puzzle input does not contain any empty lines.");
    }
    
    /// Parses the first section of the puzzle input for the ordering rules.
    ///
    /// @param orderingRulesInput the first section of the puzzle input, containing the ordering
    /// @return the ordering rules.
    private static Map<Integer, Set<Integer>> parseOrderingRules(
        List<String> orderingRulesInput
    ) {
        var orderingRules = new HashMap<Integer, Set<Integer>>();
        
        for (var rule : orderingRulesInput) {
            var pageNumbers = Arrays.stream(rule.split("\\|"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
            
            var precedingNumber = pageNumbers.getFirst();
            var succeedingNumber = pageNumbers.getLast();
            
            if (orderingRules.containsKey(precedingNumber)) {
                orderingRules.get(precedingNumber).add(succeedingNumber);
            } else {
                var succeedingNumbers = new HashSet<Integer>();
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
    private static List<List<Integer>> parseUpdates(List<String> updatesInput) {
        var updates = new ArrayList<List<Integer>>();
        
        for (var input : updatesInput) {
            var update = Arrays.stream(input.split(","))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
            
            updates.add(update);
        }
        
        return updates;
    }
    
    /// Calculates the sum of all the middle page numbers in the list of updates.
    ///
    /// @param updates a list of updates.
    /// @return the sum of all the middle page numbers in the list of updates.
    private static int calculateMiddlePageNumberSum(List<List<Integer>> updates) {
        var sumMiddlePageNumbers = 0;
        
        for (var update : updates) {
            int middleIndex = update.size() / 2;
            sumMiddlePageNumbers += update.get(middleIndex);
        }
        
        return sumMiddlePageNumbers;
    }
    
    /// Calculates the sum of the middle page numbers for valid updates.
    ///
    /// @return the sum of the middle page numbers for valid updates.
    @Override
    public Integer partOne() {
        var validUpdates = selectUpdates(updates, orderingRules, true);
        return calculateMiddlePageNumberSum(validUpdates);
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
        Map<Integer, Set<Integer>> orderingRules,
        boolean selectValidUpdates
    ) {
        var selectedUpdates = new ArrayList<List<Integer>>();
        
        for (var update : updates) {
            var isValidUpdate = isValidUpdate(update, orderingRules);
            
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
        Map<Integer, Set<Integer>> orderingRules
    ) {
        var previousPageNumbers = new HashSet<Integer>();
        
        for (var pageNumber : update) {
            if (orderingRules.containsKey(pageNumber)) {
                var intersection = new HashSet<>(orderingRules.get(pageNumber));
                intersection.retainAll(previousPageNumbers);
                
                if (!intersection.isEmpty()) {
                    return false;
                }
            }
            
            previousPageNumbers.add(pageNumber);
        }
        
        return true;
    }
    
    /// Calculates the sum of the middle page numbers for the corrected invalid updates.
    ///
    /// @return the sum of the middle page numbers for the corrected invalid updates.
    @Override
    public Integer partTwo() {
        var invalidUpdates = selectUpdates(updates, orderingRules, false);
        correctPageNumberOrder(invalidUpdates, orderingRules);
        return calculateMiddlePageNumberSum(invalidUpdates);
    }
    
    /// Sorts the page numbers in the updates according to the ordering rules.
    ///
    /// @param updates       a list of updates.
    /// @param orderingRules rules that the page numbers should be ordered by.
    private static void correctPageNumberOrder(
        List<List<Integer>> updates,
        Map<Integer, Set<Integer>> orderingRules
    ) {
        var comparator = getComparator(orderingRules);
        
        for (var update : updates) {
            update.sort(comparator);
        }
    }
    
    /// Creates a custom comparator that follows the ordering rules.
    ///
    /// @param orderingRules rules that the page numbers should be ordered by.
    /// @return a custom comparator that follows the ordering rules.
    private static Comparator<Integer> getComparator(Map<Integer, Set<Integer>> orderingRules) {
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
}
