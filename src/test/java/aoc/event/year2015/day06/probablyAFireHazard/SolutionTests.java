package aoc.event.year2015.day06.probablyAFireHazard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests {
    
    @Test
    void turnOnEverything() {
        final var input = "turn on 0,0 through 999,999";
        final var solution = new Solution(input);
        assertEquals(1000000, solution.partOne());
        assertEquals(1000000, solution.partTwo());
    }
    
    @Test
    void toggleFirstRow() {
        final var input = "toggle 0,0 through 999,0";
        final var solution = new Solution(input);
        assertEquals(1000, solution.partOne());
        assertEquals(2000, solution.partTwo());
    }
    
    @Test
    void turnOffMiddleFour() {
        final var input = "turn off 499,499 through 500,500";
        final var solution = new Solution(input);
        assertEquals(0, solution.partOne());
        assertEquals(0, solution.partTwo());
    }
    
    @Test
    void turnOnEverythingThenTurnOffMiddleFour() {
        final var input = """
            turn on 0,0 through 999,999
            turn off 499,499 through 500,500
            """;
        final var solution = new Solution(input);
        assertEquals(999996, solution.partOne());
        assertEquals(999996, solution.partTwo());
    }
    
    @Test
    void turnOnFirstTwice() {
        final var input = """
            turn on 0,0 through 0,0
            turn on 0,0 through 0,0
            """;
        final var solution = new Solution(input);
        assertEquals(1, solution.partOne());
        assertEquals(2, solution.partTwo());
    }
    
    @Test
    void toggleEverything() {
        final var input = "toggle 0,0 through 999,999";
        final var solution = new Solution(input);
        assertEquals(1000000, solution.partOne());
        assertEquals(2000000, solution.partTwo());
    }
}
