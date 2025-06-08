package aoc.event.year2016;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test {
    
    @Nested
    class PartOne {
        
        @Test
        void possibleTriangle1() {
            var input = "5 10 10";
            var solution = new Day03(input);
            assertEquals(1, solution.partOne());
        }
        
        @Test
        void possibleTriangle2() {
            var input = "5 10 14";
            var solution = new Day03(input);
            assertEquals(1, solution.partOne());
        }
        
        @Test
        void impossibleTriangle1() {
            var input = "5 10 25";
            var solution = new Day03(input);
            assertEquals(0, solution.partOne());
        }
        
        @Test
        void impossibleTriangle2() {
            var input = "15 10 5";
            var solution = new Day03(input);
            assertEquals(0, solution.partOne());
        }
    }
    
    @Nested
    class PartTwo {
        
        @Test
        void noPossibleTriangles() {
            var input = """
                101 301 1500
                102 700 501
                300 302 502
                """;
            var solution = new Day03(input);
            assertEquals(0, solution.partTwo());
        }
        
        @Test
        void onePossibleTriangle() {
            var input = """
                101 301 501
                102 302 502
                300 700 503
                """;
            var solution = new Day03(input);
            assertEquals(1, solution.partTwo());
        }
        
        @Test
        void twoPossibleTriangles() {
            var input = """
                101 301 501
                102 302 502
                300 303 503
                """;
            var solution = new Day03(input);
            assertEquals(2, solution.partTwo());
        }
        
        @Test
        void threePossibleTriangles() {
            var input = """
                101 301 501
                102 302 502
                103 303 503
                """;
            var solution = new Day03(input);
            assertEquals(3, solution.partTwo());
        }
    }
}
