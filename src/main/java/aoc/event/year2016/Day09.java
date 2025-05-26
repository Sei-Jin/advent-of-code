package aoc.event.year2016;

import aoc.DeprecatedSolver2;

public class Day09 implements DeprecatedSolver2<Long> {
    
    private static String line;
    
    public Day09(String input) {
        line = input;
    }
    
    @Override
    public Long partOne() {
        var total = 0;
        var current = 0;
        
        while (current < line.length()) {
            if (line.charAt(current) != '(') {
                current++;
                total++;
            } else {
                final var closing = current + line.substring(current).indexOf(')');
                
                final var values = line.substring(current + 1, closing).split("x");
                final var length = Integer.parseInt(values[0]);
                final var repetitions = Integer.parseInt(values[1]);
                
                final var maxLength = Math.min(length, line.length() - closing - 1);
                
                total += maxLength * repetitions;
                current = closing + maxLength + 1;
            }
        }
        
        return (long) total;
    }
    
    @Override
    public Long partTwo() {
        final var counts = new int[line.length()];
        
        var inside = false;
        
        for (int i = 0; i < counts.length; i++) {
            if (line.charAt(i) == '(') {
                inside = true;
            } else if (line.charAt(i) == ')') {
                inside = false;
                counts[i] = 0;
                continue;
            }
            
            if (inside) {
                counts[i] = 0;
            } else {
                counts[i] = 1;
            }
        }
        
        var current = 0;
        
        while (current < line.length()) {
            if (line.charAt(current) != '(') {
                current++;
            } else {
                final var closing = current + line.substring(current).indexOf(')');
                
                final var values = line.substring(current + 1, closing).split("x");
                final var length = Integer.parseInt(values[0]);
                final var repetitions = Integer.parseInt(values[1]);
                
                final var maxLength = Math.min(length, line.length() - closing - 1);
                
                for (int i = closing; i <= closing + maxLength; i++) {
                    counts[i] *= repetitions;
                }
                
                current = closing + 1;
            }
        }
        
        var total = 0L;
        
        for (final var count : counts) {
            total += count;
        }
        
        return total;
    }
}
