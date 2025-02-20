package aoc.event.year2021.day01.sonarSweep;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests {
    @Test
    void example() {
        final var input = """
                199
                200
                208
                210
                200
                207
                240
                269
                260
                263
                """;
        
        final var solution = new Solution(input);
        assertEquals(7, solution.partOne());
        assertEquals(5, solution.partTwo());
    }
}
