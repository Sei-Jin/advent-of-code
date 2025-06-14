package aoc.event.year2015;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day04Test {
    
    @Test
    void example1() {
        var input = "abcdef";
        var solution = new Day04(input);
        assertEquals(609043, solution.partOne());
    }
    
    @Test
    void example2() {
        var input = "pqrstuv";
        var solution = new Day04(input);
        assertEquals(1048970, solution.partOne());
    }
}