package aoc.event.year2024;

import aoc.Solver;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/// # [2024-05: Print Queue](https://adventofcode.com/2024/day/5)
public class Day05 implements Solver<Integer, Integer> {
    
    private final Map<Integer, Set<Integer>> rules;
    private final List<List<Integer>> updates;
    
    public Day05(String input) {
        var lines = input.lines().toList();
        var emptyLineIndex = findEmptyLineIndex(lines);
        
        var rulesInput = lines.subList(0, emptyLineIndex);
        var updatesInput = lines.subList(emptyLineIndex + 1, lines.size());
        
        rules = parseRules(rulesInput);
        updates = parseUpdates(updatesInput);
    }
    
    private static int findEmptyLineIndex(List<String> lines) {
        return IntStream.range(0, lines.size())
            .filter(i -> lines.get(i).isEmpty())
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("No empty lines found."));
    }
    
    private static Map<Integer, Set<Integer>> parseRules(List<String> rulesInput) {
        var rules = new HashMap<Integer, Set<Integer>>();
        for (var rule : rulesInput) {
            var parts = Arrays.stream(rule.split("\\|"))
                .mapToInt(Integer::parseInt)
                .toArray();
            
            var predecessor = parts[0];
            var successor = parts[1];
            
            if (rules.containsKey(predecessor)) {
                rules.get(predecessor).add(successor);
            } else {
                var successors = new HashSet<Integer>();
                successors.add(successor);
                rules.put(predecessor, successors);
            }
        }
        return rules;
    }
    
    private static List<List<Integer>> parseUpdates(List<String> updatesInput) {
        return updatesInput
            .stream()
            .map(input ->
                Arrays
                    .stream(input.split(","))
                    .mapToInt(Integer::parseInt)
                    .boxed()
                    .collect(Collectors.toList()))
            .toList();
    }
    
    private static boolean isValidUpdate(List<Integer> update, Map<Integer, Set<Integer>> rules) {
        var seen = new HashSet<Integer>();
        for (var current : update) {
            var successors = rules.get(current);
            if (successors != null) {
                for (var element : seen) {
                    if (successors.contains(element)) {
                        return false;
                    }
                }
            }
            seen.add(current);
        }
        return true;
    }
    
    private static int sumMiddle(List<List<Integer>> updates) {
        return updates
            .stream()
            .map(update -> {
                int middle = update.size() / 2;
                return update.get(middle);
            })
            .reduce(0, Integer::sum);
    }
    
    /// Calculates the sum of the middle page numbers for valid updates.
    @Override
    public Integer partOne() {
        var validUpdates = updates
            .stream()
            .filter(update -> isValidUpdate(update, rules))
            .toList();
        return sumMiddle(validUpdates);
    }
    
    /// Calculates the sum of the middle page numbers for the corrected invalid updates.
    @Override
    public Integer partTwo() {
        var filteredUpdates = updates
            .stream()
            .filter(update -> !isValidUpdate(update, rules))
            .toList();
        
        var comparator = createComparator(rules);
        for (var update : filteredUpdates) {
            update.sort(comparator);
        }
        
        return sumMiddle(filteredUpdates);
    }
    
    private static Comparator<Integer> createComparator(Map<Integer, Set<Integer>> rules) {
        return (predecessor, successor) -> {
            if (predecessor.equals(successor)) {
                return 0;
            }
            
            var set = rules.get(predecessor);
            if (set.contains(successor)) {
                return 1;
            }
            else {
                return -1;
            }
        };
    }
}
