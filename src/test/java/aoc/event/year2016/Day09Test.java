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
    
    @Test
    void custom1() {
        final var input = "(6x1)(1x3)A(1x3)";
        final var solution = new Day09(input);
        assertEquals(6, solution.partOne());
    }
    
    @Test
    void custom2() {
        final var input = "A(1x5)(2x3)BCD";
        final var solution = new Day09(input);
        assertEquals(13, solution.partOne());
    }
    
    @Test
    void custom3() {
        final var input = "(2x3)A(2x2)BCD";
        final var solution = new Day09(input);
        assertEquals(13, solution.partOne());
    }
    
    @Test
    void custom4() {
        final var input = "(3x4)A(2x2)(2x3)B(1x3)C";
        final var solution = new Day09(input);
        assertEquals(26, solution.partOne());
    }
    
    @Test
    void custom5() {
        final var input = "(3x3)A";
        final var solution = new Day09(input);
        assertEquals(3, solution.partOne());
    }
    
    @Test
    void custom6() {
        final var input = "A(3x3)";
        final var solution = new Day09(input);
        assertEquals(1, solution.partOne());
    }
    
    @Test
    void custom7() {
        final var input = "X(3x3)A(2x2)BC(2x2)EF";
        final var solution = new Day09(input);
        assertEquals(19, solution.partOne());
    }
    
    @Test
    void custom8() {
        final var input = "ABC(2x3)DEFG";
        final var solution = new Day09(input);
        assertEquals(11, solution.partOne());
    }
}