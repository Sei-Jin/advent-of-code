package aoc.event.year2023.day01.trebuchet;

import aoc.event.year2023.Day01;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests {
    
    @Nested
    class PartOne {
        @Test
        void exampleLine1() {
            final var input = "1abc2";
            final var solution = new Day01(input);
            assertEquals(12, solution.partOne());
        }
        
        @Test
        void exampleLine2() {
            final var input = "pqr3stu8vwx";
            final var solution = new Day01(input);
            assertEquals(38, solution.partOne());
        }
        
        @Test
        void exampleLine3() {
            final var input = "a1b2c3d4e5f";
            final var solution = new Day01(input);
            assertEquals(15, solution.partOne());
        }
        
        @Test
        void exampleLine4() {
            final var input = "treb7uchet";
            final var solution = new Day01(input);
            assertEquals(77, solution.partOne());
        }
    }
    
    @Nested
    class PartTwo {
        @Test
        void exampleLine1() {
            final var input = "two1nine";
            final var solution = new Day01(input);
            assertEquals(29, solution.partTwo());
        }
        
        @Test
        void exampleLine2() {
            final var input = "eightwothree";
            final var solution = new Day01(input);
            assertEquals(83, solution.partTwo());
        }
        
        @Test
        void exampleLine3() {
            final var input = "abcone2threexyz";
            final var solution = new Day01(input);
            assertEquals(13, solution.partTwo());
        }
        
        @Test
        void exampleLine4() {
            final var input = "xtwone3four";
            final var solution = new Day01(input);
            assertEquals(24, solution.partTwo());
        }
        
        @Test
        void exampleLine5() {
            final var input = "4nineeightseven2";
            final var solution = new Day01(input);
            assertEquals(42, solution.partTwo());
        }
        
        @Test
        void exampleLine6() {
            final var input = "zoneight234";
            final var solution = new Day01(input);
            assertEquals(14, solution.partTwo());
        }
        
        @Test
        void exampleLine7() {
            final var input = "7pqrstsixteen";
            final var solution = new Day01(input);
            assertEquals(76, solution.partTwo());
        }
    }
}
