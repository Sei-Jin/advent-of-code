package aoc.event.year2018;

import aoc.Solver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/// # [2018-02: Inventory Management System](https://adventofcode.com/2018/day/2)
public class Day02 implements Solver<Integer, String> {
    
    private final List<List<Character>> characterLists;
    
    public Day02(String input) {
        characterLists = parse(input);
    }
    
    private static List<List<Character>> parse(String input) {
        return input
            .lines()
            .map(line -> IntStream.range(0, line.length())
                .mapToObj(line::charAt)
                .toList())
            .toList();
    }
    
    /// Calculates the checksum for the list of box ids.
    ///
    /// To calculate the checksum, multiply the number of box ids that contain exactly two of any
    /// character with the number of box ids that contain exactly three of any character.
    ///
    /// - Time Complexity: `O(n)`
    /// - Space Complexity: `O(n)`
    ///
    /// @return the checksum for the list of box IDs.
    @Override
    public Integer partOne() {
        var twoCount = 0;
        var threeCount = 0;
        for (var list : characterLists) {
            var counts = createCountMap(list);
            if (counts.containsValue(2)) {
                twoCount++;
            }
            if (counts.containsValue(3)) {
                threeCount++;
            }
        }
        return twoCount * threeCount;
    }
    
    private static <T> Map<T, Integer> createCountMap(List<T> list) {
        var counts = new HashMap<T, Integer>();
        for (var element : list) {
            var count = counts.getOrDefault(element, 0) + 1;
            counts.put(element, count);
        }
        return counts;
    }
    
    /// Creates a new string using the common characters of the two correct box ids.
    ///
    /// The two correct box ids are the box ids that differ by exactly one character.
    ///
    /// - Time Complexity: `O(n^2)`
    /// - Space Complexity: `O(n)`
    ///
    /// @return the characters common between the two correct box IDs.
    @Override
    public String partTwo() {
        for (var i = 0; i < characterLists.size(); i++) {
            for (var j = 0; j < characterLists.size(); j++) {
                if (i == j) {
                    continue;
                }
                var first = characterLists.get(i);
                var second = characterLists.get(j);
                var differences = countDifferences(first, second);
                if (differences == 1) {
                    var removeIndex = findFirstDifferentIndex(first, second);
                    var common = new ArrayList<>(first);
                    common.remove(removeIndex);
                    return buildString(common);
                }
            }
        }
        throw new IllegalStateException("No box IDs differed by exactly one character.");
    }
    
    private static int countDifferences(List<Character> first, List<Character> second) {
        var min = Math.min(first.size(), second.size());
        return (int) IntStream.range(0, min)
            .filter(i -> first.get(i) != second.get(i))
            .count();
    }
    
    private static int findFirstDifferentIndex(List<Character> first, List<Character> second) {
        var min = Math.min(first.size(), second.size());
        return IntStream.range(0, min)
            .filter(i -> first.get(i) != second.get(i))
            .findFirst()
            .orElseThrow();
    }
    
    private static String buildString(List<Character> list) {
        return list
            .stream()
            .map(String::valueOf)
            .collect(Collectors.joining());
    }
}
