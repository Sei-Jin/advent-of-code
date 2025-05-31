package aoc.event.year2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {
    
    @Test
    void example() {
        var input = "3,4,3,1,2";
        var solver = new Day06(input);
        assertEquals(5934L, solver.partOne());
        assertEquals(26984457539L, solver.partTwo());
    }
}