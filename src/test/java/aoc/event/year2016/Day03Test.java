package aoc.event.year2016;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {
    
    @Nested
    class PartOne {
        
        @Test
        void possibleTriangle1() {
            final var input = "5 10 10";
            final var solution = new Day03(input);
            assertEquals(1, solution.partOne());
        }
        
        @Test
        void possibleTriangle2() {
            final var input = "5 10 14";
            final var solution = new Day03(input);
            assertEquals(1, solution.partOne());
        }
        
        @Test
        void impossibleTriangle1() {
            final var input = "5 10 25";
            final var solution = new Day03(input);
            assertEquals(0, solution.partOne());
        }
        
        @Test
        void impossibleTriangle2() {
            final var input = "15 10 5";
            final var solution = new Day03(input);
            assertEquals(0, solution.partOne());
        }
    }
    
    @Nested
    class PartTwo {
        
        @Test
        void noPossibleTriangles() {
            final var input = """
                101 301 1500
                102 700 501
                300 302 502
                """;
            final var solution = new Day03(input);
            assertEquals(0, solution.partTwo());
        }
        
        @Test
        void onePossibleTriangle() {
            final var input = """
                101 301 501
                102 302 502
                300 700 503
                """;
            final var solution = new Day03(input);
            assertEquals(1, solution.partTwo());
        }
        
        @Test
        void twoPossibleTriangles() {
            final var input = """
                101 301 501
                102 302 502
                300 303 503
                """;
            final var solution = new Day03(input);
            assertEquals(2, solution.partTwo());
        }
        
        @Test
        void threePossibleTriangles() {
            final var input = """
                101 301 501
                102 302 502
                103 303 503
                """;
            final var solution = new Day03(input);
            assertEquals(3, solution.partTwo());
        }
    }
}
