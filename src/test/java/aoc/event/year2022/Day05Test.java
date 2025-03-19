package aoc.event.year2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test {
    
    @Test
    void example() {
        final var input = """
                   [D]   \s
               [N] [C]   \s
               [Z] [M] [P]
                1   2   3\s
               
               move 1 from 2 to 1
               move 3 from 1 to 3
               move 2 from 2 to 1
               move 1 from 1 to 2
               """;
        final var solution = new Day05(input);
        assertEquals("CMZ", solution.partOne());
        assertEquals("MCD", solution.partTwo());
    }
}
