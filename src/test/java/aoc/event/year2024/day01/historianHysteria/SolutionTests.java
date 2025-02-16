package aoc.event.year2024.day01.historianHysteria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    private static final Solution solution = new Solution();

    @Test
    void exampleList()
    {
        final var input = """
                3   4
                4   3
                2   5
                1   3
                3   9
                3   3
                """;

        solution.parse(input);
        assertEquals(11, solution.partOne());
        assertEquals(31, solution.partTwo());
    }
}
