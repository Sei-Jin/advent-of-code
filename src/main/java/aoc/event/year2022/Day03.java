package aoc.event.year2022;

import aoc.Solver;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Gatherers;

/// # [2022-03: Rucksack Reorganization](https://adventofcode.com/2022/day/3)
public class Day03 implements Solver<Integer, Integer> {
    
    private final List<List<Character>> lines;
    
    public Day03(String input) {
        lines = parse(input);
    }
    
    private static List<List<Character>> parse(String input) {
        return input.lines()
            .map(line -> line.chars()
                .mapToObj(c -> (char) c)
                .toList())
            .toList();
    }
    
    /// Maps lowercase letters to the values 1 to 26 and uppercase letters to the values 27 to 52.
    private static int calculatePriority(char sharedItem) {
        if (sharedItem >= 'a' && sharedItem <= 'z') {
            return sharedItem - 96;
        }
        else if (sharedItem >= 'A' && sharedItem <= 'Z') {
            return sharedItem - 38;
        }
        throw new IllegalArgumentException();
    }
    
    /// Calculates the sum of the priority values for the shared characters.
    ///
    /// Each line contains one shared character between the first and second half.
    @Override
    public Integer partOne() {
        return lines
            .stream()
            .mapToInt(line -> {
                var midPoint = line.size() / 2;
                var firstHalf = new HashSet<>(line.subList(0, midPoint));
                var secondHalf = line.subList(midPoint, line.size());
                
                var sharedItem = secondHalf
                    .stream()
                    .filter(firstHalf::contains)
                    .findFirst()
                    .orElseThrow();
                return calculatePriority(sharedItem);
            })
            .sum();
    }
    
    /// Calculates the sum of the priority values for the shared characters.
    ///
    /// Each three-line group contains one shared character.
    @Override
    public Integer partTwo() {
        return lines
            .stream()
            .gather(Gatherers.windowFixed(3))
            .mapToInt(group -> {
                var first = new HashSet<>(group.get(0));
                var second = new HashSet<>(group.get(1));
                var third = group.get(2);
                
                var sharedItem = third
                    .stream()
                    .filter(first::contains)
                    .filter(second::contains)
                    .findFirst()
                    .orElseThrow();
                return calculatePriority(sharedItem);
            })
            .sum();
    }
}
