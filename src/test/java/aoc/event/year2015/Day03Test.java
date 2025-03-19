package aoc.event.year2015;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {
    
    @Test
    void headEast() {
        final var input = ">";
        final var solution = new Day03(input);
        assertEquals(2, solution.partOne());
        assertEquals(2, solution.partTwo());
    }
    
    @Test
    void cycleThroughDirections() {
        final var input = "^>v<";
        final var solution = new Day03(input);
        assertEquals(4, solution.partOne());
        assertEquals(3, solution.partTwo());
    }
    
    @Test
    void northAndSouthAlternating() {
        final var input = "^v^v^v^v^v";
        final var solution = new Day03(input);
        assertEquals(2, solution.partOne());
        assertEquals(11, solution.partTwo());
    }
    
    @Test
    void northAndSouth() {
        final var input = "^v";
        final var solution = new Day03(input);
        assertEquals(2, solution.partOne());
        assertEquals(3, solution.partTwo());
    }
}
