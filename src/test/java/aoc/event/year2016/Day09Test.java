package aoc.event.year2016;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day09Test {
    
    @Test
    void example1() {
        final var input = "ADVENT";
        final var solution = new Day09(input);
        assertEquals(6, solution.partOne());
    }
    
    @Test
    void example2() {
        final var input = "A(1x5)BC";
        final var solution = new Day09(input);
        assertEquals(7, solution.partOne());
    }
    
    @Test
    void example3() {
        final var input = "(3x3)XYZ";
        final var solution = new Day09(input);
        assertEquals(9, solution.partOne());
    }
    
    @Test
    void example4() {
        final var input = "A(2x2)BCD(2x2)EFG";
        final var solution = new Day09(input);
        assertEquals(11, solution.partOne());
    }
    
    @Test
    void example5() {
        final var input = "(6x1)(1x3)A";
        final var solution = new Day09(input);
        assertEquals(6, solution.partOne());
    }
    
    @Test
    void example6() {
        final var input = "X(8x2)(3x3)ABCY";
        final var solution = new Day09(input);
        assertEquals(18, solution.partOne());
    }
}