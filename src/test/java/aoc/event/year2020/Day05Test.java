package aoc.event.year2020;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {
    
    @Test
    void exampleDefault() {
        var input = "FBFBBFFRLR";
        var solution = new Day05(input);
        assertEquals(357, solution.partOne());
    }
    
    @Test
    void example1() {
        var input = "BFFFBBFRRR";
        var solution = new Day05(input);
        assertEquals(567, solution.partOne());
    }
    
    @Test
    void example2() {
        var input = "FFFBBBFRRR";
        var solution = new Day05(input);
        assertEquals(119, solution.partOne());
    }
    
    @Test
    void example3() {
        var input = "BBFFBBFRLL";
        var solution = new Day05(input);
        assertEquals(820, solution.partOne());
    }
}
