package aoc.event.year2024;

import aoc.Solver;

import java.util.*;
import java.util.stream.IntStream;

/// # [2024-01: Historian Hysteria](https://adventofcode.com/2024/day/1)
public class Day01 implements Solver<Integer, Integer> {
    
    private final IdLists idLists;
    
    public Day01(String input) {
        idLists = parse(input);
    }
    
    private static IdLists parse(String input) {
        var first = new ArrayList<Integer>();
        var second = new ArrayList<Integer>();
        
        input
            .lines()
            .forEach(line -> {
                var parts = line.split("\\s+");
                first.add(Integer.parseInt(parts[0]));
                second.add(Integer.parseInt(parts[1]));
            });
        
        return new IdLists(
            Collections.unmodifiableList(first),
            Collections.unmodifiableList(second)
        );
    }
    
    @Override
    public Integer partOne() {
        var mutableFirst = new ArrayList<>(idLists.first());
        var mutableSecond = new ArrayList<>(idLists.second());
        
        Collections.sort(mutableFirst);
        Collections.sort(mutableSecond);
        
        return IntStream.range(0, mutableFirst.size())
            .map(i -> {
                var first = mutableFirst.get(i);
                var second = mutableSecond.get(i);
                return Math.abs(first - second);
            })
            .sum();
    }
    
    @Override
    public Integer partTwo() {
        var secondFrequencies = calculateFrequencies(idLists.second());
        return idLists.first()
            .stream()
            .mapToInt(id -> {
                var frequency = secondFrequencies.getOrDefault(id, 0);
                return id * frequency;
            })
            .sum();
    }
    
    private static Map<Integer, Integer> calculateFrequencies(List<Integer> list) {
        var frequencies = new HashMap<Integer, Integer>();
        for (var number : list) {
            var frequency = frequencies.getOrDefault(number, 0) + 1;
            frequencies.put(number, frequency);
        }
        return frequencies;
    }
    
    private record IdLists(List<Integer> first, List<Integer> second) {}
}
