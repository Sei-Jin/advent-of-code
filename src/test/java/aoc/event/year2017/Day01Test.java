package aoc.event.year2017;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test {
    @Nested
    class PartOne {
        @Test
        void twoDigitsRepeatNext() {
            var input = "1122";
            var solution = new Day01(input);
            assertEquals(3, solution.partOne());
        }
        
        @Test
        void allDigitsRepeatNext() {
            var input = "1111";
            var solution = new Day01(input);
            assertEquals(4, solution.partOne());
        }
        
        @Test
        void noDigitsRepeatNext() {
            var input = "1234";
            var solution = new Day01(input);
            assertEquals(0, solution.partOne());
        }
        
        @Test
        void endDigitRepeatsNext() {
            var input = "91212129";
            var solution = new Day01(input);
            assertEquals(9, solution.partOne());
        }
    }
    
    @Nested
    class PartTwo {
        @Test
        void allDigitsRepeatHalfway() {
            var input = "1212";
            var solution = new Day01(input);
            assertEquals(6, solution.partTwo());
        }
        
        @Test
        void noDigitsRepeatHalfway() {
            var input = "1221";
            var solution = new Day01(input);
            assertEquals(0, solution.partTwo());
        }
        
        @Test
        void oneDigitRepeatsHalfway() {
            var input = "123425";
            var solution = new Day01(input);
            assertEquals(4, solution.partTwo());
        }
        
        @Test
        void allDigitsRepeatHalfway2() {
            var input = "123123";
            var solution = new Day01(input);
            assertEquals(12, solution.partTwo());
        }
        
        @Test
        void twoDigitsRepeatHalfway() {
            var input = "12131415";
            var solution = new Day01(input);
            assertEquals(4, solution.partTwo());
        }
    }
}
