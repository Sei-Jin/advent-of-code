package aoc.event.year2015;

import aoc.Solver;

import java.util.List;
import java.util.regex.Pattern;

/// # [2015-05: Doesn't He Have Intern-Elves For This?](https://adventofcode.com/2015/day/5)
public class Day05 implements Solver<Integer, Integer> {
    
    private static final Pattern HAS_AT_LEAST_THREE_VOWELS =
        Pattern.compile("^([^aeiou]*[aeiou]){3,}[^aeiou]*$");
    private static final Pattern HAS_AT_LEAST_ONE_LETTER_TWICE_IN_A_ROW =
        Pattern.compile("^.*([a-z])\\1.*$");
    private static final Pattern HAS_INVALID_SEQUENCES =
        Pattern.compile("^(?:(?!ab|cd|pq|xy).)*$");
    private static final Pattern HAS_DUPLICATE_PAIRS =
        Pattern.compile("^.*([a-z]{2}).*\\1.*$");
    private static final Pattern HAS_AT_LEAST_ONE_REPEATING_WITH_ONE_IN_BETWEEN =
        Pattern.compile("^.*([a-z]).\\1.*$");
    private final List<String> lines;
    
    public Day05(String input) {
        lines = input.lines().toList();
    }
    
    @Override
    public Integer partOne() {
        return (int) lines
            .stream()
            .filter(line -> HAS_AT_LEAST_THREE_VOWELS.matcher(line).find())
            .filter(line -> HAS_AT_LEAST_ONE_LETTER_TWICE_IN_A_ROW.matcher(line).find())
            .filter(line -> HAS_INVALID_SEQUENCES.matcher(line).find())
            .count();
    }
    
    @Override
    public Integer partTwo() {
        return (int) lines
            .stream()
            .filter(line -> HAS_DUPLICATE_PAIRS.matcher(line).find())
            .filter(line -> HAS_AT_LEAST_ONE_REPEATING_WITH_ONE_IN_BETWEEN.matcher(line).find())
            .count();
    }
}
