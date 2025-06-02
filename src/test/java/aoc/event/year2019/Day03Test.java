package aoc.event.year2019;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Day03Test {
    
    @Test
    void example1() {
        var input = """
            R8,U5,L5,D3
            U7,R6,D4,L4
            """;
        var solution = new Day03(input);
        assertEquals(6, solution.partOne());
        assertEquals(30, solution.partTwo());
    }
    
    @Test
    void example2() {
        var input = """
            R75,D30,R83,U83,L12,D49,R71,U7,L72
            U62,R66,U55,R34,D71,R55,D58,R83
            """;
        var solution = new Day03(input);
        assertEquals(159, solution.partOne());
        assertEquals(610, solution.partTwo());
    }
    
    @Test
    void example3() {
        var input = """
            R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51
            U98,R91,D20,R16,D67,R40,U7,R15,U6,R7
            """;
        var solution = new Day03(input);
        assertEquals(135, solution.partOne());
        assertEquals(410, solution.partTwo());
    }
    
    @Test
    void tinyWireIntersection() {
        var input = """
            U2,R2
            R2,U2
            """;
        var solution = new Day03(input);
        assertEquals(4, solution.partOne());
        assertEquals(8, solution.partTwo());
    }
    
    @Test
    void noIntersections() {
        var input = """
            U2
            R2
            """;
        var solution = new Day03(input);
        assertThrows(IllegalStateException.class, solution::partOne);
        assertThrows(IllegalStateException.class, solution::partTwo);
    }
}
