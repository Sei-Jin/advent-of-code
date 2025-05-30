package aoc.event.year2021;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test
{
    @Test
    void example() {
        var input = """
            forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2
            """;
        var solution = new Day02(input);
        assertEquals(150, solution.partOne());
        assertEquals(900, solution.partTwo());
    }
}
