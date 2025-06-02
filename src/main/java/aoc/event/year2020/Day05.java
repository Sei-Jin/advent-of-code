package aoc.event.year2020;

import aoc.Solver;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/// # [2020-05: Binary Boarding](https://adventofcode.com/2020/day/5)
public class Day05 implements Solver<Integer, Integer> {
    
    private static final int ROW_MAX = 128;
    private static final int COLUMN_MAX = 8;
    private static final Pattern SEQUENCE_PATTERN = Pattern.compile("^([BF]+)([LR]+)$");
    private final List<BoardingSequence> sequences;
    
    public Day05(String input) {
        sequences = parse(input);
    }
    
    private static List<BoardingSequence> parse(String input) {
        return input
            .lines()
            .map(SEQUENCE_PATTERN::matcher)
            .flatMap(Matcher::results)
            .map(result -> {
                var rows = result.group(1)
                    .chars()
                    .mapToObj(i -> (char) i)
                    .toList();
                var columns = result.group(2)
                    .chars()
                    .mapToObj(i -> (char) i)
                    .toList();
                return new BoardingSequence(rows, columns);
            })
            .toList();
    }
    
    private static int binarySearch(List<Character> sequence, char lower, char higher) {
        var n = (int) Math.pow(2, sequence.size());
        
        var left = 0;
        var right = n - 1;
        for (var character : sequence) {
            var middle = (left + right) / 2;
            if (character == lower) {
                right = middle;
            }
            else if (character == higher) {
                left = middle + 1;
            }
        }
        
        var last = sequence.getLast();
        if (last == lower) {
            return Math.min(left, right);
        }
        else if (last == higher) {
            return Math.max(left, right);
        }
        else {
            return 0;
        }
    }
    
    /// Calculates the highest seat id on the boarding pass.
    @Override
    public Integer partOne() {
        return sequences
            .stream()
            .mapToInt(sequence -> {
                var row = binarySearch(sequence.rows, 'F', 'B');
                var column = binarySearch(sequence.columns, 'L', 'R');
                return row * 8 + column;
            })
            .max()
            .orElse(0);
    }
    
    /// Finds the only empty seat in the middle of the plane and calculates it's seat id.
    ///
    /// There may be empty seats on either end of the plane, so we can start searching after the
    /// first 30 rows from the front and the back.
    @Override
    public Integer partTwo() {
        var seatingPlan = new int[ROW_MAX][COLUMN_MAX];
        for (var sequence : sequences) {
            var row = binarySearch(sequence.rows, 'F', 'B');
            var column = binarySearch(sequence.columns, 'L', 'R');
            seatingPlan[row][column] = 1;
        }
        for (int column = 0; column < COLUMN_MAX; column++) {
            for (int row = 30; row < ROW_MAX - 30; row++) {
                if (seatingPlan[row][column] == 0) {
                    return row * 8 + column;
                }
            }
        }
        throw new IllegalArgumentException(
            "There was not an empty seat in the middle of the plane."
        );
    }
    
    private record BoardingSequence(List<Character> rows, List<Character> columns) {}
}
