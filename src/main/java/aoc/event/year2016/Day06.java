package aoc.event.year2016;

import aoc.Solver;
import aoc.util.Common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/// # [2016-06: Signals and Noise](https://adventofcode.com/2016/day/6)
public class Day06 implements Solver<String, String> {
    
    private final List<Map<Character, Integer>> countMaps;
    
    public Day06(String input) {
        var lines = parse(input);
        countMaps = createCountMaps(lines);
    }
    
    private List<List<Character>> parse(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException();
        }
        var lines = input.lines().toList();
        
        var columns = new ArrayList<List<Character>>();
        for (int x = 0; x < lines.getFirst().length(); x++) {
            var column = new ArrayList<Character>();
            for (var line : lines) {
                column.add(line.charAt(x));
            }
            columns.add(column);
        }
        return columns;
    }
    
    private List<Map<Character, Integer>> createCountMaps(List<List<Character>> lines) {
        return lines
            .stream()
            .map(Common::countEntries)
            .toList();
    }
    
    @Override
    public String partOne() {
        var message = new StringBuilder();
        for (var map : countMaps) {
            var mostFrequent = map
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow();
            message.append(mostFrequent);
        }
        return message.toString();
    }
    
    @Override
    public String partTwo() {
        var message = new StringBuilder();
        for (var map : countMaps) {
            var leastFrequent = map
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElseThrow();
            message.append(leastFrequent);
        }
        return message.toString();
    }
}
