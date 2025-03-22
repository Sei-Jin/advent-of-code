package aoc.event.year2016;

import aoc.Solver;

public class Day09 implements Solver<Integer> {
    
    private static String line;
    
    public Day09(String input) {
        line = input;
    }
    
    @Override
    public Integer partOne() {
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
        
        return total;
    }
    
    @Override
    public Integer partTwo() {
        return 0;
    }
}
