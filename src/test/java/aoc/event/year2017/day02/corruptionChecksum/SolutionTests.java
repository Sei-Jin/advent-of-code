package aoc.event.year2017.day02.corruptionChecksum;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests {
    
    @Nested
    class PartOne {
        @Test
        void exampleSpreadsheetFirstRow() {
            final var input = "5 1 9 5";
            final var solution = new Solution(input);
            assertEquals(8, solution.partOne());
        }
        
        @Test
        void exampleSpreadsheetSecondRow() {
            final var input = "7 5 3  ";
            final var solution = new Solution(input);
            assertEquals(4, solution.partOne());
        }
        
        @Test
        void exampleSpreadsheetThirdRow() {
            final var input = "2 4 6 8";
            final var solution = new Solution(input);
            assertEquals(6, solution.partOne());
        }
        
        @Test
        void exampleSpreadsheet() {
            final var input = """
                    5 1 9 5
                    7 5 3
                    2 4 6 8
                    """;
            final var solution = new Solution(input);
            assertEquals(18, solution.partOne());
        }
    }
    
    @Nested
    class PartTwo {
        @Test
        void exampleSpreadsheetFirstRow() {
            final var input = "5 9 2 8";
            final var solution = new Solution(input);
            assertEquals(4, solution.partTwo());
        }
        
        @Test
        void exampleSpreadsheetSecondRow() {
            final var input = "9 4 7 3";
            final var solution = new Solution(input);
            assertEquals(3, solution.partTwo());
        }
        
        @Test
        void exampleSpreadsheetThirdRow() {
            final var input = "3 8 6 5";
            final var solution = new Solution(input);
            assertEquals(2, solution.partTwo());
        }
        
        @Test
        void exampleSpreadsheet() {
            final var input = """
                    5 9 2 8
                    9 4 7 3
                    3 8 6 5
                    """;
            final var solution = new Solution(input);
            assertEquals(9, solution.partTwo());
        }
    }
}
