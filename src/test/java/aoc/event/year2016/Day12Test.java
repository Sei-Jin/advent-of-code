package aoc.event.year2016;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day12Test {
    
    @Test
    void example() {
        final var input = """
            cpy 41 a
            inc a
            inc a
            dec a
            jnz a 2
            dec a
            """;
        final var solution = new Day12(input);
        assertEquals(42, solution.partOne());
    }
}