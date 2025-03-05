package aoc.event.year2016.day01.noTimeForATaxicab;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests {
    
    @Test
    void northEast() {
        final var input = "R2, L3";
        final var solution = new Solution(input);
        assertEquals(5, solution.partOne());
        assertEquals(-1, solution.partTwo());
    }
    
    @Test
    void eastToSouth() {
        final var input = "R2, R2, R2";
        final var solution = new Solution(input);
        assertEquals(2, solution.partOne());
        assertEquals(-1, solution.partTwo());
    }
    
    @Test
    void snakeEastwards() {
        final var input = "R5, L5, R5, R3";
        final var solution = new Solution(input);
        assertEquals(12, solution.partOne());
        assertEquals(-1, solution.partTwo());
    }
    
    @Test
    void crossingPaths() {
        final var input = "R8, R4, R4, R8";
        final var solution = new Solution(input);
        assertEquals(8, solution.partOne());
        assertEquals(4, solution.partTwo());
    }
}
