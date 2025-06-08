package aoc.event.year2018;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {
    
    @Test
    void example() {
        var input = """
            #1 @ 1,3: 4x4
            #2 @ 3,1: 4x4
            #3 @ 5,5: 2x2
            """;
        var solution = new Day03(input);
        assertEquals(4, solution.partOne());
        assertEquals(3, solution.partTwo());
    }
}
