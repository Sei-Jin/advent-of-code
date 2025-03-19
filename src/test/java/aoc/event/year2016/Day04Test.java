package aoc.event.year2016;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {
    
    @Test
    void exampleLine1() {
        final var input = "aaaaa-bbb-z-y-x-123[abxyz]";
        final var solution = new Day04(input);
        assertEquals(123, solution.partOne());
    }
    
    @Test
    void exampleLine2() {
        final var input = "a-b-c-d-e-f-g-h-987[abcde]";
        final var solution = new Day04(input);
        assertEquals(987, solution.partOne());
    }
    
    @Test
    void exampleLine3() {
        final var input = "not-a-real-room-404[oarel]";
        final var solution = new Day04(input);
        assertEquals(404, solution.partOne());
    }
    
    @Test
    void exampleLine4() {
        final var input = "totally-real-room-200[decoy]";
        final var solution = new Day04(input);
        assertEquals(0, solution.partOne());
    }
    
    @Test
    void completeExample() {
        final var input = """
            aaaaa-bbb-z-y-x-123[abxyz]
            a-b-c-d-e-f-g-h-987[abcde]
            not-a-real-room-404[oarel]
            totally-real-room-200[decoy]
            """;
        final var solution = new Day04(input);
        assertEquals(1514, solution.partOne());
    }
}
