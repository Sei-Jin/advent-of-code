package aoc.event.year2015;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {
    
    @Test
    void increasingDimensions() {
        var input = "2x3x4";
        var solution = new Day02(input);
        assertEquals(58, solution.partOne());
        assertEquals(34, solution.partTwo());
    }
    
    @Test
    void smallSmallestSide() {
        var input = "1x1x10";
        var solution = new Day02(input);
        assertEquals(43, solution.partOne());
        assertEquals(14, solution.partTwo());
    }
    
    @Test
    void evenDimensions() {
        var input = "5x5x5";
        var solution = new Day02(input);
        assertEquals(175, solution.partOne());
        assertEquals(145, solution.partTwo());
    }
}
