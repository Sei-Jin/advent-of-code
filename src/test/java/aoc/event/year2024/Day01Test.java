package aoc.event.year2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {
    
    @Test
    void exampleList() {
        var input = """
            3   4
            4   3
            2   5
            1   3
            3   9
            3   3
            """;
        var solution = new Day01(input);
        assertEquals(11, solution.partOne());
        assertEquals(31, solution.partTwo());
    }
}
