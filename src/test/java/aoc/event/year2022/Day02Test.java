package aoc.event.year2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {
    
    @Test
    void example() {
        final var input = """
                A Y
                B X
                C Z
                """;
        final var solution = new Day02(input);
        assertEquals(15, solution.partOne());
        assertEquals(12, solution.partTwo());
    }
}
