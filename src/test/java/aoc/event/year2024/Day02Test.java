package aoc.event.year2024;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {
    
    @Test
    void exampleReport1() {
        var input = "7 6 4 2 1";
        var solution = new Day02(input);
        assertEquals(1, solution.partOne());
        assertEquals(1, solution.partTwo());
    }
    
    @Test
    void exampleReport2() {
        var input = "1 2 7 8 9";
        var solution = new Day02(input);
        assertEquals(0, solution.partOne());
        assertEquals(0, solution.partTwo());
    }
    
    @Test
    void exampleReport3() {
        var input = "9 7 6 2 1";
        var solution = new Day02(input);
        assertEquals(0, solution.partOne());
        assertEquals(0, solution.partTwo());
    }
    
    @Test
    void exampleReport4() {
        var input = "1 3 2 4 5";
        var solution = new Day02(input);
        assertEquals(0, solution.partOne());
        assertEquals(1, solution.partTwo());
    }
    
    @Test
    void exampleReport5() {
        var input = "8 6 4 4 1";
        var solution = new Day02(input);
        assertEquals(0, solution.partOne());
        assertEquals(1, solution.partTwo());
    }
    
    @Test
    void exampleReport6() {
        var input = "1 3 6 7 9";
        var solution = new Day02(input);
        assertEquals(1, solution.partOne());
        assertEquals(1, solution.partTwo());
    }
    
    @Test
    void allExampleReports() {
        var input = """
            7 6 4 2 1
            1 2 7 8 9
            9 7 6 2 1
            1 3 2 4 5
            8 6 4 4 1
            1 3 6 7 9
            """;
        var solution = new Day02(input);
        assertEquals(2, solution.partOne());
        assertEquals(4, solution.partTwo());
    }
}
