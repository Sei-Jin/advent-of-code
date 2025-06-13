package aoc.event.year2015;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Day01Test {
    
    @Test
    void ascendThenDescendSameAmount() {
        var input = "(())";
        var solution = new Day01(input);
        assertEquals(0, solution.partOne());
        assertThrows(IllegalStateException.class, solution::partTwo);
    }
    
    @Test
    void loopingBetweenTwoFloors() {
        var input = "()()()()()";
        var solution = new Day01(input);
        assertEquals(0, solution.partOne());
        assertThrows(IllegalStateException.class, solution::partTwo);
    }
    
    @Test
    void onlyAscending() {
        var input = "(((";
        var solution = new Day01(input);
        assertEquals(3, solution.partOne());
        assertThrows(IllegalStateException.class, solution::partTwo);
    }
    
    @Test
    void onlyDescending() {
        var input = ")))";
        var solution = new Day01(input);
        assertEquals(-3, solution.partOne());
        assertEquals(1, solution.partTwo());
    }
    
    @Test
    void ascendingWithLoops() {
        var input = "(()(()(";
        var solution = new Day01(input);
        assertEquals(3, solution.partOne());
        assertThrows(IllegalStateException.class, solution::partTwo);
    }
    
    @Test
    void descendingWithLoops() {
        var input = ")())())";
        var solution = new Day01(input);
        assertEquals(-3, solution.partOne());
        assertEquals(1, solution.partTwo());
    }
    
    @Test
    void descendThenSharpAscend() {
        var input = "))(((((";
        var solution = new Day01(input);
        assertEquals(3, solution.partOne());
        assertEquals(1, solution.partTwo());
    }
    
    @Test
    void ascendThenSlightDescend() {
        var input = "())";
        var solution = new Day01(input);
        assertEquals(-1, solution.partOne());
        assertEquals(3, solution.partTwo());
    }
    
    @Test
    void descendThenSlightAscend() {
        var input = "))(";
        var solution = new Day01(input);
        assertEquals(-1, solution.partOne());
        assertEquals(1, solution.partTwo());
    }
    
    @Test
    void immediateDescent() {
        var input = ")";
        var solution = new Day01(input);
        assertEquals(-1, solution.partOne());
        assertEquals(1, solution.partTwo());
    }
    
    @Test
    void loopingThenDescend() {
        var input = "()())";
        var solution = new Day01(input);
        assertEquals(-1, solution.partOne());
        assertEquals(5, solution.partTwo());
    }
}
