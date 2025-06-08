package aoc.event.year2016;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {
    
    @Test
    void example() {
        var input = """
            ULL
            RRDDD
            LURDL
            UUUUD
            """;
        var solution = new Day02(input);
        assertEquals("1985", solution.partOne());
        assertEquals("5DB3", solution.partTwo());
    }
}
