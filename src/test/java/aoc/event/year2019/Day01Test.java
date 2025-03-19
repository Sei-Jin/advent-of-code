package aoc.event.year2019;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {
    
    @Test
    void example1() {
        final var input = "12";
        final var solution = new Day01(input);
        assertEquals(2, solution.partOne());
        assertEquals(2, solution.partTwo());
    }
    
    @Test
    void example2() {
        final var input = "14";
        final var solution = new Day01(input);
        assertEquals(2, solution.partOne());
        assertEquals(2, solution.partTwo());
    }
    
    @Test
    void example3() {
        final var input = "1969";
        final var solution = new Day01(input);
        assertEquals(654, solution.partOne());
        assertEquals(966, solution.partTwo());
    }
    
    @Test
    void example4() {
        final var input = "100756";
        final var solution = new Day01(input);
        assertEquals(33583, solution.partOne());
        assertEquals(50346, solution.partTwo());
    }
}
