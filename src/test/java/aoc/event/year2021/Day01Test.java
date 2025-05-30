package aoc.event.year2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {
    
    @Test
    void example() {
        var input = """
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
        var solution = new Day01(input);
        assertEquals(7, solution.partOne());
        assertEquals(5, solution.partTwo());
    }
}
