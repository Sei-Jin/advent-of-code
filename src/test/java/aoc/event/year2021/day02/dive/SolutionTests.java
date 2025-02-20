package aoc.event.year2021.day02.dive;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void example() {
        final var input = """
                forward 5
                down 5
                forward 8
                up 3
                down 8
                forward 2
                """;
        final var solution = new Solution(input);
        assertEquals(150, solution.partOne());
        assertEquals(900, solution.partTwo());
    }
}
