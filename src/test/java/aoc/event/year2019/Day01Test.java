package aoc.event.year2019;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {
    
    @Test
    void example1() {
        var input = "12";
        var solution = new Day01(input);
        assertEquals(2, solution.partOne());
        assertEquals(2, solution.partTwo());
    }
    
    @Test
    void example2() {
        var input = "14";
        var solution = new Day01(input);
        assertEquals(2, solution.partOne());
        assertEquals(2, solution.partTwo());
    }
    
    @Test
    void example3() {
        var input = "1969";
        var solution = new Day01(input);
        assertEquals(654, solution.partOne());
        assertEquals(966, solution.partTwo());
    }
    
    @Test
    void example4() {
        var input = "100756";
        var solution = new Day01(input);
        assertEquals(33583, solution.partOne());
        assertEquals(50346, solution.partTwo());
    }
}
