package aoc.event.year2018.day01.chronalCalibration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests {

    @Test
    void example1() {
        final var input = """
            +1
            -2
            +3
            +1
            """;
        final var solution = new Solution(input);
        assertEquals(3, solution.partOne());
        assertEquals(2, solution.partTwo());
    }
    
    @Test
    void example2() {
        final var input = """
            +1
            +1
            +1
            """;
        final var solution = new Solution(input);
        assertEquals(3, solution.partOne());
    }
    
    @Test
    void example3() {
        final var input = """
            +1
            +1
            -2
            """;
        final var solution = new Solution(input);
        assertEquals(0, solution.partOne());
    }
    
    @Test
    void example4() {
        final var input = """
            -1
            -2
            -3
            """;
        final var solution = new Solution(input);
        assertEquals(-6, solution.partOne());
    }
    
    @Test
    void example5() {
        final var input = """
            +1
            -1
            """;
        final var solution = new Solution(input);
        assertEquals(0, solution.partTwo());
    }
    
    @Test
    void example6() {
        final var input = """
            +3
            +3
            +4
            -2
            -4
            """;
        final var solution = new Solution(input);
        assertEquals(10, solution.partTwo());
    }
    
    @Test
    void example7() {
        final var input = """
            -6
            +3
            +8
            +5
            -6
            """;
        final var solution = new Solution(input);
        assertEquals(5, solution.partTwo());
    }
    
    @Test
    void example8() {
        final var input = """
            +7
            +7
            -2
            -7
            -4
            """;
        final var solution = new Solution(input);
        assertEquals(14, solution.partTwo());
    }
}
