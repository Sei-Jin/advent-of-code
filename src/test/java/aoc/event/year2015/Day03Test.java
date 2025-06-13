package aoc.event.year2015;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {
    
    @Test
    void headEast() {
        var input = ">";
        var solution = new Day03(input);
        assertEquals(2, solution.partOne());
        assertEquals(2, solution.partTwo());
    }
    
    @Test
    void cycleThroughDirections() {
        var input = "^>v<";
        var solution = new Day03(input);
        assertEquals(4, solution.partOne());
        assertEquals(3, solution.partTwo());
    }
    
    @Test
    void northAndSouthAlternating() {
        var input = "^v^v^v^v^v";
        var solution = new Day03(input);
        assertEquals(2, solution.partOne());
        assertEquals(11, solution.partTwo());
    }
    
    @Test
    void northAndSouth() {
        var input = "^v";
        var solution = new Day03(input);
        assertEquals(2, solution.partOne());
        assertEquals(3, solution.partTwo());
    }
}
