package aoc.event.year2020.day01.reportRepair;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void example() {
        final var input = """
                1721
                979
                366
                299
                675
                1456
                """;
        final var solution = new Solution(input);
        assertEquals(514579, solution.partOne());
        assertEquals(241861950, solution.partTwo());
    }
}
