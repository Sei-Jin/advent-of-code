package aoc.event.year2015;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {
    
    @Test
    void ascendThenDescendSameAmount() {
        final var input = "(())";
        final var solution = new Day01(input);
        Assertions.assertEquals(0, solution.partOne());
        Assertions.assertEquals(-1, solution.partTwo());
    }
    
    @Test
    void loopingBetweenTwoFloors() {
        final var input = "()()()()()";
        final var solution = new Day01(input);
        assertEquals(0, solution.partOne());
        assertEquals(-1, solution.partTwo());
    }
    
    @Test
    void onlyAscending() {
        final var input = "(((";
        final var solution = new Day01(input);
        assertEquals(3, solution.partOne());
        assertEquals(-1, solution.partTwo());
    }
    
    @Test
    void onlyDescending() {
        final var input = ")))";
        final var solution = new Day01(input);
        assertEquals(-3, solution.partOne());
        assertEquals(1, solution.partTwo());
    }
    
    @Test
    void ascendingWithLoops() {
        final var input = "(()(()(";
        final var solution = new Day01(input);
        assertEquals(3, solution.partOne());
        assertEquals(-1, solution.partTwo());
    }
    
    @Test
    void descendingWithLoops() {
        final var input = ")())())";
        final var solution = new Day01(input);
        assertEquals(-3, solution.partOne());
        assertEquals(1, solution.partTwo());
    }
    
    @Test
    void descendThenSharpAscend() {
        final var input = "))(((((";
        final var solution = new Day01(input);
        assertEquals(3, solution.partOne());
        assertEquals(1, solution.partTwo());
    }
    
    @Test
    void ascendThenSlightDescend() {
        final var input = "())";
        final var solution = new Day01(input);
        assertEquals(-1, solution.partOne());
        assertEquals(3, solution.partTwo());
    }
    
    @Test
    void descendThenSlightAscend() {
        final var input = "))(";
        final var solution = new Day01(input);
        assertEquals(-1, solution.partOne());
        assertEquals(1, solution.partTwo());
    }
    
    @Test
    void immediateDescent() {
        final var input = ")";
        final var solution = new Day01(input);
        assertEquals(-1, solution.partOne());
        assertEquals(1, solution.partTwo());
    }
    
    @Test
    void loopingThenDescend() {
        final var input = "()())";
        final var solution = new Day01(input);
        assertEquals(-1, solution.partOne());
        assertEquals(5, solution.partTwo());
    }
}
