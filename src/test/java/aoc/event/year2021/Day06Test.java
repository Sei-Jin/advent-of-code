package aoc.event.year2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day06Test {
    
    @Test
    void example() {
        final var input = "3,4,3,1,2";
        final var solver = new Day06(input);
        assertEquals(26L, solver.partOne());
    }
}