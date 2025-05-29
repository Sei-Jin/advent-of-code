package aoc.event.year2023;

import aoc.Solver;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/// # [2023-01: Trebuchet?!](https://adventofcode.com/2023/day/1)
public class Day01 implements Solver<Integer, Integer> {
    
    private static final Map<String, Integer> digitMap = Map.of(
        "one", 1,
        "two", 2,
        "three", 3,
        "four", 4,
        "five", 5,
        "six", 6,
        "seven", 7,
        "eight", 8,
        "nine", 9
    );
    private final List<String> lines;
    
    public Day01(String input) {
        lines = input
            .lines()
            .toList();
    }
    
    private static IntStream streamIndices(String string, boolean reverse) {
        var min = 0;
        var max = string.length() - 1;
        if (reverse) {
            return IntStream.iterate(max, i -> i >= min, i -> i - 1);
        }
        else {
            return IntStream.rangeClosed(min, max);
        }
    }
    
    @Override
    public Integer partOne() {
        return lines
            .stream()
            .mapToInt(line -> {
                var firstDigit = findFirstDigit(line, false);
                var lastDigit = findFirstDigit(line, true);
                return firstDigit * 10 + lastDigit;
            })
            .sum();
    }
    
    private static int findFirstDigit(String string, boolean reverse) {
        return streamIndices(string, reverse)
            .map(string::charAt)
            .filter(Character::isDigit)
            .map(Character::getNumericValue)
            .findFirst()
            .orElseThrow(() ->
                new IllegalArgumentException("No digits found in string: " + string)
            );
    }

    @Override
    public Integer partTwo() {
        return lines
            .stream()
            .mapToInt(line -> {
                var firstDigit = findFirstDigitWithStrings(line, false);
                var lastDigit = findFirstDigitWithStrings(line, true);
                return firstDigit * 10 + lastDigit;
            })
            .sum();
    }
    
    private static Integer findFirstDigitWithStrings(String string, boolean reverse) {
        return streamIndices(string, reverse)
            .map(i -> {
                var character = string.charAt(i);
                if (Character.isDigit(character)) {
                    return Character.getNumericValue(character);
                }
                for (var entry : digitMap.entrySet()) {
                    if (string.startsWith(entry.getKey(), i)) {
                        return entry.getValue();
                    }
                }
                return 0;
            })
            .filter(value -> value != 0)
            .findFirst()
            .orElseThrow(() ->
                new IllegalArgumentException("No digits found in string: " + string)
            );
    }
}