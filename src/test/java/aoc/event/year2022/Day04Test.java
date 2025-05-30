package aoc.event.year2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {
    
    @Test
    void example() {
        var input = """
            2-4,6-8
            2-3,4-5
            5-7,7-9
            2-8,3-7
            6-6,4-6
            2-6,4-8
            """;
        var solution = new Day04(input);
        assertEquals(2, solution.partOne());
        assertEquals(4, solution.partTwo());
    }
    
    @Test
    void checkOverlapping() {
        var input = """
            5-5,4-6
            4-6,5-5
            """;
        var solution = new Day04(input);
        assertEquals(2, solution.partTwo());
    }
}
