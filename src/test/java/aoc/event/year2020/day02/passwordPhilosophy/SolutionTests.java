package aoc.event.year2020.day02.passwordPhilosophy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests
{
    @Test
    void example() {
        final var input = """
                1-3 a: abcde
                1-3 b: cdefg
                2-9 c: ccccccccc
                """;
        final var solution = new Solution(input);
        assertEquals(2, solution.partOne());
        assertEquals(1, solution.partTwo());
    }
}
