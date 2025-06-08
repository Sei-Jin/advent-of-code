package aoc.event.year2016;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {
    
    @Test
    void exampleLine1() {
        var input = "aaaaa-bbb-z-y-x-123[abxyz]";
        var solution = new Day04(input);
        assertEquals(123, solution.partOne());
    }
    
    @Test
    void exampleLine2() {
        var input = "a-b-c-d-e-f-g-h-987[abcde]";
        var solution = new Day04(input);
        assertEquals(987, solution.partOne());
    }
    
    @Test
    void exampleLine3() {
        var input = "not-a-real-room-404[oarel]";
        var solution = new Day04(input);
        assertEquals(404, solution.partOne());
    }
    
    @Test
    void exampleLine4() {
        var input = "totally-real-room-200[decoy]";
        var solution = new Day04(input);
        assertEquals(0, solution.partOne());
    }
    
    @Test
    void completeExample() {
        var input = """
            aaaaa-bbb-z-y-x-123[abxyz]
            a-b-c-d-e-f-g-h-987[abcde]
            not-a-real-room-404[oarel]
            totally-real-room-200[decoy]
            """;
        var solution = new Day04(input);
        assertEquals(1514, solution.partOne());
    }
}
