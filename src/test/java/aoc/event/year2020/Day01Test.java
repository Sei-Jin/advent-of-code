package aoc.event.year2020;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {
    
    @Test
    void example() {
        var input = """
            1721
            979
            366
            299
            675
            1456
            """;
        var solution = new Day01(input);
        assertEquals(514579, solution.partOne());
        assertEquals(241861950, solution.partTwo());
    }
}
