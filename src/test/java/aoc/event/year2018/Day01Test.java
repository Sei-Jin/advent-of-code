package aoc.event.year2018;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {

    @Test
    void example1() {
        var input = """
            +1
            -2
            +3
            +1
            """;
        var solution = new Day01(input);
        assertEquals(3, solution.partOne());
        assertEquals(2, solution.partTwo());
    }
    
    @Test
    void example2() {
        var input = """
            +1
            +1
            +1
            """;
        var solution = new Day01(input);
        assertEquals(3, solution.partOne());
    }
    
    @Test
    void example3() {
        var input = """
            +1
            +1
            -2
            """;
        var solution = new Day01(input);
        assertEquals(0, solution.partOne());
    }
    
    @Test
    void example4() {
        var input = """
            -1
            -2
            -3
            """;
        var solution = new Day01(input);
        assertEquals(-6, solution.partOne());
    }
    
    @Test
    void example5() {
        var input = """
            +1
            -1
            """;
        var solution = new Day01(input);
        assertEquals(0, solution.partTwo());
    }
    
    @Test
    void example6() {
        var input = """
            +3
            +3
            +4
            -2
            -4
            """;
        var solution = new Day01(input);
        assertEquals(10, solution.partTwo());
    }
    
    @Test
    void example7() {
        var input = """
            -6
            +3
            +8
            +5
            -6
            """;
        var solution = new Day01(input);
        assertEquals(5, solution.partTwo());
    }
    
    @Test
    void example8() {
        var input = """
            +7
            +7
            -2
            -7
            -4
            """;
        var solution = new Day01(input);
        assertEquals(14, solution.partTwo());
    }
}
