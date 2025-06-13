package aoc.event.year2016;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Test {
    
    @Test
    void example() {
        var input = """
            rect 3x2
            rotate column x=1 by 1
            rotate row y=0 by 4
            rotate column x=1 by 1
            """;
        var solution = new Day08(input);
        assertEquals(6, solution.partOne());
    }
    
    @Test
    void example2() {
        var input = """
            rect 3x2
            rotate column x=1 by 1
            """;
        var solution = new Day08(input);
        assertEquals(6, solution.partOne());
    }
}