package aoc.event.year2015;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test {
    
    @Test
    void turnOnEverything() {
        var input = "turn on 0,0 through 999,999";
        var solution = new Day06(input);
        assertEquals(1000000, solution.partOne());
        assertEquals(1000000, solution.partTwo());
    }
    
    @Test
    void toggleFirstRow() {
        var input = "toggle 0,0 through 999,0";
        var solution = new Day06(input);
        assertEquals(1000, solution.partOne());
        assertEquals(2000, solution.partTwo());
    }
    
    @Test
    void turnOffMiddleFour() {
        var input = "turn off 499,499 through 500,500";
        var solution = new Day06(input);
        assertEquals(0, solution.partOne());
        assertEquals(0, solution.partTwo());
    }
    
    @Test
    void turnOnEverythingThenTurnOffMiddleFour() {
        var input = """
            turn on 0,0 through 999,999
            turn off 499,499 through 500,500
            """;
        var solution = new Day06(input);
        assertEquals(999996, solution.partOne());
        assertEquals(999996, solution.partTwo());
    }
    
    @Test
    void turnOnFirstTwice() {
        var input = """
            turn on 0,0 through 0,0
            turn on 0,0 through 0,0
            """;
        var solution = new Day06(input);
        assertEquals(1, solution.partOne());
        assertEquals(2, solution.partTwo());
    }
    
    @Test
    void toggleEverything() {
        var input = "toggle 0,0 through 999,999";
        var solution = new Day06(input);
        assertEquals(1000000, solution.partOne());
        assertEquals(2000000, solution.partTwo());
    }
}
