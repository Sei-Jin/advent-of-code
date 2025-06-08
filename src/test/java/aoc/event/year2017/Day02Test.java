package aoc.event.year2017;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test {
    
    @Nested
    class PartOne {
        
        @Test
        void exampleSpreadsheetFirstRow() {
            var input = "5 1 9 5";
            var solution = new Day02(input);
            assertEquals(8, solution.partOne());
        }
        
        @Test
        void exampleSpreadsheetSecondRow() {
            var input = "7 5 3  ";
            var solution = new Day02(input);
            assertEquals(4, solution.partOne());
        }
        
        @Test
        void exampleSpreadsheetThirdRow() {
            var input = "2 4 6 8";
            var solution = new Day02(input);
            assertEquals(6, solution.partOne());
        }
        
        @Test
        void exampleSpreadsheet() {
            var input = """
                5 1 9 5
                7 5 3
                2 4 6 8
                """;
            var solution = new Day02(input);
            assertEquals(18, solution.partOne());
        }
    }
    
    @Nested
    class PartTwo {
        
        @Test
        void exampleSpreadsheetFirstRow() {
            var input = "5 9 2 8";
            var solution = new Day02(input);
            assertEquals(4, solution.partTwo());
        }
        
        @Test
        void exampleSpreadsheetSecondRow() {
            var input = "9 4 7 3";
            var solution = new Day02(input);
            assertEquals(3, solution.partTwo());
        }
        
        @Test
        void exampleSpreadsheetThirdRow() {
            var input = "3 8 6 5";
            var solution = new Day02(input);
            assertEquals(2, solution.partTwo());
        }
        
        @Test
        void exampleSpreadsheet() {
            var input = """
                5 9 2 8
                9 4 7 3
                3 8 6 5
                """;
            var solution = new Day02(input);
            assertEquals(9, solution.partTwo());
        }
    }
}
