package aoc.event.year2017.day01.inverseCaptcha;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTests {
    @Nested
    class PartOne {
        @Test
        void twoDigitsRepeatNext() {
            final var input = "1122";
            final var solution = new Solution(input);
            assertEquals(3, solution.partOne());
        }
        
        @Test
        void allDigitsRepeatNext() {
            final var input = "1111";
            final var solution = new Solution(input);
            assertEquals(4, solution.partOne());
        }
        
        @Test
        void noDigitsRepeatNext() {
            final var input = "1234";
            final var solution = new Solution(input);
            assertEquals(0, solution.partOne());
        }
        
        @Test
        void endDigitRepeatsNext() {
            final var input = "91212129";
            final var solution = new Solution(input);
            assertEquals(9, solution.partOne());
        }
    }
    
    
    @Nested
    class PartTwo {
        @Test
        void allDigitsRepeatHalfway() {
            final var input = "1212";
            final var solution = new Solution(input);
            assertEquals(6, solution.partTwo());
        }
        
        @Test
        void noDigitsRepeatHalfway() {
            final var input = "1221";
            final var solution = new Solution(input);
            assertEquals(0, solution.partTwo());
        }
        
        @Test
        void oneDigitRepeatsHalfway() {
            final var input = "123425";
            final var solution = new Solution(input);
            assertEquals(4, solution.partTwo());
        }
        
        @Test
        void allDigitsRepeatHalfway2() {
            final var input = "123123";
            final var solution = new Solution(input);
            assertEquals(12, solution.partTwo());
        }
        
        @Test
        void twoDigitsRepeatHalfway() {
            final var input = "12131415";
            final var solution = new Solution(input);
            assertEquals(4, solution.partTwo());
        }
    }
}
