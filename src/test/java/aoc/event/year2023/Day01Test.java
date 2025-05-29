package aoc.event.year2023;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {
    
    @Nested
    class PartOne {
        
        @Test
        void exampleLine1() {
            var input = "1abc2";
            var solution = new Day01(input);
            assertEquals(12, solution.partOne());
        }
        
        @Test
        void exampleLine2() {
            var input = "pqr3stu8vwx";
            var solution = new Day01(input);
            assertEquals(38, solution.partOne());
        }
        
        @Test
        void exampleLine3() {
            var input = "a1b2c3d4e5f";
            var solution = new Day01(input);
            assertEquals(15, solution.partOne());
        }
        
        @Test
        void exampleLine4() {
            var input = "treb7uchet";
            var solution = new Day01(input);
            assertEquals(77, solution.partOne());
        }
    }
    
    @Nested
    class PartTwo {
        
        @Test
        void exampleLine1() {
            var input = "two1nine";
            var solution = new Day01(input);
            assertEquals(29, solution.partTwo());
        }
        
        @Test
        void exampleLine2() {
            var input = "eightwothree";
            var solution = new Day01(input);
            assertEquals(83, solution.partTwo());
        }
        
        @Test
        void exampleLine3() {
            var input = "abcone2threexyz";
            var solution = new Day01(input);
            assertEquals(13, solution.partTwo());
        }
        
        @Test
        void exampleLine4() {
            var input = "xtwone3four";
            var solution = new Day01(input);
            assertEquals(24, solution.partTwo());
        }
        
        @Test
        void exampleLine5() {
            var input = "4nineeightseven2";
            var solution = new Day01(input);
            assertEquals(42, solution.partTwo());
        }
        
        @Test
        void exampleLine6() {
            var input = "zoneight234";
            var solution = new Day01(input);
            assertEquals(14, solution.partTwo());
        }
        
        @Test
        void exampleLine7() {
            var input = "7pqrstsixteen";
            var solution = new Day01(input);
            assertEquals(76, solution.partTwo());
        }
    }
}
