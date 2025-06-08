package aoc.event.year2016;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Day01Test {
    
    @Test
    void northEast() {
        var input = "R2, L3";
        var solution = new Day01(input);
        assertEquals(5, solution.partOne());
        assertThrows(IllegalStateException.class, solution::partTwo);
    }
    
    @Test
    void eastToSouth() {
        var input = "R2, R2, R2";
        var solution = new Day01(input);
        assertEquals(2, solution.partOne());
        assertThrows(IllegalStateException.class, solution::partTwo);
    }
    
    @Test
    void snakeEastwards() {
        var input = "R5, L5, R5, R3";
        var solution = new Day01(input);
        assertEquals(12, solution.partOne());
        assertThrows(IllegalStateException.class, solution::partTwo);
    }
    
    @Test
    void crossingPaths() {
        var input = "R8, R4, R4, R8";
        var solution = new Day01(input);
        assertEquals(8, solution.partOne());
        assertEquals(4, solution.partTwo());
    }
}
