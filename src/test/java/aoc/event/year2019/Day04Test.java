package aoc.event.year2019;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test {
    
    @Nested
    class Part1 {
        
        @Test
        void repeatingDigits() {
            var input = "111111-111111";
            var solution = new Day04(input);
            assertEquals(1, solution.partOne());
        }
        
        @Test
        void decreasingPairOfDigits() {
            var input = "223450-223450";
            var solution = new Day04(input);
            assertEquals(0, solution.partOne());
        }
        
        @Test
        void increasingDigits() {
            var input = "123789-123789";
            var solution = new Day04(input);
            assertEquals(0, solution.partOne());
        }
        
        @Test
        void smallRange() {
            var input = "111110-111120";
            var solution = new Day04(input);
            assertEquals(9, solution.partOne());
        }
    }
    
    @Nested
    class Part2 {
        
        @Test
        void example1() {
            var input = "112233-112233";
            var solution = new Day04(input);
            assertEquals(1, solution.partTwo());
        }
        
        @Test
        void example2() {
            var input = "123444-123444";
            var solution = new Day04(input);
            assertEquals(0, solution.partTwo());
        }
        
        @Test
        void example3() {
            var input = "111122-111122";
            var solution = new Day04(input);
            assertEquals(1, solution.partTwo());
        }
    }
}
