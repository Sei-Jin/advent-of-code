package aoc.event.year2020;

import aoc.DeprecatedSolver2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/// # [2020-05: Binary Boarding](https://adventofcode.com/2020/day/5)
public class Day05 implements DeprecatedSolver2
{
    private static final Pattern BOARDING_SEQUENCE = Pattern.compile("^([BF]+)([LR]+)$");
    
    private final List<BoardingSequence> boardingSequences;
    
    public Day05(String input) {
        boardingSequences = parse(input);
    }
    
    private static List<BoardingSequence> parse(String input) {
        final var boardingSequences = new ArrayList<BoardingSequence>();
        final var lines = input.lines().toList();
        
        for (final var line : lines) {
            final var matcher = BOARDING_SEQUENCE.matcher(line);
            
            if (matcher.find()) {
                final var row = matcher.group(1);
                final var column = matcher.group(2);
                
                final var rowSequence = new ArrayList<Character>();
                final var columnSequence = new ArrayList<Character>();
                
                for (int i = 0; i < row.length(); i++) {
                    rowSequence.add(row.charAt(i));
                }
                
                for (int i = 0; i < column.length(); i++) {
                    columnSequence.add(column.charAt(i));
                }
                
                final var boardingSequence = new BoardingSequence(
                    Collections.unmodifiableList(rowSequence),
                    Collections.unmodifiableList(columnSequence)
                );
                
                boardingSequences.add(boardingSequence);
            }
        }
        
        return Collections.unmodifiableList(boardingSequences);
    }
    
    private static int binarySearch(List<Character> sequence, int n) {
        var left = 0;
        var right = n - 1;
        
        for (final var character : sequence) {
            final var middle = (left + right) / 2;
            
            if (character == 'F' || character == 'L') {
                // Lower half
                right = middle;
            } else if (character == 'B' || character == 'R') {
                // Upper half
                left = middle + 1;
            }
        }
        
        final var lastCharacter = sequence.getLast();
        var value = 0;
        
        if (lastCharacter == 'F' || lastCharacter == 'L') {
            value = Math.min(left, right);
        } else if (lastCharacter == 'B' || lastCharacter == 'R') {
            value = Math.max(left, right);
        }
        
        return value;
    }
    
    @Override
    public Integer partOne() {
        var highestSeatId = 0;
        
        for (final var sequence : boardingSequences) {
            final var row = binarySearch(sequence.rowSequence, 128);
            final var column = binarySearch(sequence.columnSequence, 8);
            
            final var seatId = row * 8 + column;
            highestSeatId = Math.max(highestSeatId, seatId);
        }
        
        return highestSeatId;
    }
    
    @Override
    public Integer partTwo() {
        return 0;
    }
    
    private record BoardingSequence(List<Character> rowSequence, List<Character> columnSequence) {}
}
