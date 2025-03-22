package aoc.event.year2016;

import aoc.Solver;

import java.util.Arrays;
import java.util.regex.Pattern;

public class Day09 implements Solver<Integer> {
    
    private static final Pattern SEQUENCE = Pattern.compile("\\((\\d)x(\\d+)\\)");
    
    private static String line;
    
    public Day09(String input) {
        line = input;
    }
    
    @Override
    public Integer partOne() {
        final var counts = new int[line.length()];
        Arrays.fill(counts, 1);
        
        final var markers = SEQUENCE.matcher(line)
            .results()
            .map(result -> {
                final var length = Integer.parseInt(result.group(1));
                final var repetitions = Integer.parseInt(result.group(2));
                final var start = result.start();
                final var end = result.end() - 1;
                
                return new Marker(length, repetitions, start, end);
            })
            .toList();
        
        var current = 0;
        
        for (final var marker : markers) {
            if (marker.start >= current) {
                for (int i = marker.start(); i <= marker.end(); i++) {
                    counts[i] = 0;
                }
                
                current = marker.end() + 1;
                final var maxIndex = current + marker.length();
                
                while (current < maxIndex && current < counts.length) {
                    counts[current] *= marker.repetitions();
                    current++;
                }
            }
        }
        
        var sum = 0;
        
        for (final var count : counts) {
            sum += count;
        }
        
        return sum;
    }
    
    @Override
    public Integer partTwo() {
        return 0;
    }
    
    private record Marker(int length, int repetitions, int start, int end) {}
}
