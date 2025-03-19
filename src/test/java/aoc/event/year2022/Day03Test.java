package aoc.event.year2022;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {
    
    @Test
    void example() {
        final var input = """
                vJrwpWtwJgWrhcsFMMfFFhFp
                jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
                PmmdzqPrVvPwwTWBwg
                wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
                ttgJtRGJQctTZtZT
                CrZsJsPPZsGzwwsLwLmpwMDw
                """;
        final var solution = new Day03(input);
        assertEquals(157, solution.partOne());
        assertEquals(70, solution.partTwo());
    }
}
