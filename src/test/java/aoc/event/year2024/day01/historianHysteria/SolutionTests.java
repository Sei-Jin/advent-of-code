package aoc.event.year2024.day01.historianHysteria;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    Solution solution = new Solution();

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
        BufferedReader reader = new BufferedReader(new StringReader(input));

        solution.parse(reader);
        assertEquals(11, new Solution().partOne());
        assertEquals(31, new Solution().partTwo());
    }
}
