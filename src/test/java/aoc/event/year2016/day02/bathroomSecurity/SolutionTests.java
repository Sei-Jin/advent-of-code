package aoc.event.year2016.day02.bathroomSecurity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests {
    
    @Test
    void example() {
        final var input = """
            ULL
            RRDDD
            LURDL
            UUUUD
            """;
        final var solution = new Solution(input);
        assertEquals("1985", solution.partOne());
        assertEquals("5DB3", solution.partTwo());
    }
}
