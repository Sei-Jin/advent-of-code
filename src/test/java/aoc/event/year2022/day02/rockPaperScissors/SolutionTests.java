package aoc.event.year2022.day02.rockPaperScissors;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests {
    
    @Test
    void example() {
        final var input = """
                A Y
                B X
                C Z
                """;
        final var solution = new Solution(input);
        assertEquals(15, solution.partOne());
        assertEquals(12, solution.partTwo());
    }
}
