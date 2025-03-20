package aoc.event.year2017;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {
    
    @Test
    void example() {
        final var input = """
            0
            3
            0
            1
            -3
            """;
        final var solution = new Day05(input);
        assertEquals(5, solution.partOne());
        assertEquals(10, solution.partTwo());
    }
}
