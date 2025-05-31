package aoc.event.year2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {
    
    @Test
    void example() {
        var input = """
            00100
            11110
            10110
            10111
            10101
            01111
            00111
            11100
            10000
            11001
            00010
            01010
            """;
        var solution = new Day03(input);
        assertEquals(198, solution.partOne());
    }
}
